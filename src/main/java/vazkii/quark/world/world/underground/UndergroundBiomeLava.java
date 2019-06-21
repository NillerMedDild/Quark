package vazkii.quark.world.world.underground;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.world.feature.UndergroundBiomes;

public class UndergroundBiomeLava extends BasicUndergroundBiome {

	public static double lavaChance, obsidianChance;

	public UndergroundBiomeLava() {
		super(Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), true);
	}
	
	@Override
	public void fillCeiling(World world, BlockPos pos, IBlockState state) {
		if(UndergroundBiomes.firestoneEnabled && world.rand.nextBoolean())
			world.setBlockState(pos, UndergroundBiomes.firestoneState, 2);
		else super.fillCeiling(world, pos, state);
	}
	
	@Override
	public void fillWall(World world, BlockPos pos, IBlockState state) {
		if(UndergroundBiomes.firestoneEnabled)
			world.setBlockState(pos, UndergroundBiomes.firestoneState, 2);
		else super.fillWall(world, pos, state);
	}
	
	@Override
	public void fillFloor(World world, BlockPos pos, IBlockState state) {
		if(!isBorder(world, pos) && world.rand.nextDouble() < lavaChance)
			world.setBlockState(pos, Blocks.LAVA.getDefaultState());
		else if(world.rand.nextDouble() < obsidianChance)
			world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState(), 2);
		else if(UndergroundBiomes.firestoneEnabled)
			world.setBlockState(pos, UndergroundBiomes.firestoneState, 2);
		else super.fillFloor(world, pos, state);
	}
	
	@Override
	public void setupConfig(String category) {
		lavaChance = ModuleLoader.config.get("Lava Chance", category, 0.25, "The chance lava will spawn", 0, 1).getDouble();
		obsidianChance = ModuleLoader.config.get("Obsidian Chance", category, 0.0625, "The chance obsidian will spawn", 0, 1).getDouble();
	}
}
