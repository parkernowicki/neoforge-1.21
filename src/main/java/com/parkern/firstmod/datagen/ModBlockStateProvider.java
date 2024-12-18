package com.parkern.firstmod.datagen;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import com.parkern.firstmod.block.custom.BismuthLampBlock;
import com.parkern.firstmod.block.custom.GojiBerryBushBlock;
import com.parkern.firstmod.block.custom.RadishCropBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SHALE_BLOCK);
        blockWithItem(ModBlocks.TITANITE_ORE);
        blockWithItem(ModBlocks.NETHER_TITANITE_ORE);
        blockWithItem(ModBlocks.END_TITANITE_ORE);

        blockWithItem(ModBlocks.WITHERER);

        stairsBlock(ModBlocks.SHALE_STAIRS.get(), blockTexture(ModBlocks.SHALE_BLOCK.get()));
        slabBlock(ModBlocks.SHALE_SLAB.get(), blockTexture(ModBlocks.SHALE_BLOCK.get()), blockTexture(ModBlocks.SHALE_BLOCK.get()));

        buttonBlock(ModBlocks.SHALE_BUTTON.get(), blockTexture(ModBlocks.SHALE_BLOCK.get()));
        pressurePlateBlock(ModBlocks.SHALE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SHALE_BLOCK.get()));

        fenceBlock(ModBlocks.SHALE_FENCE.get(), blockTexture(ModBlocks.SHALE_BLOCK.get()));
        fenceGateBlock(ModBlocks.SHALE_FENCE_GATE.get(), blockTexture(ModBlocks.SHALE_BLOCK.get()));
        wallBlock(ModBlocks.SHALE_WALL.get(), blockTexture(ModBlocks.SHALE_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.SHALE_DOOR.get(), modLoc("block/shale_door_bottom"), modLoc("block/shale_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.SHALE_TRAPDOOR.get(), modLoc("block/shale_trapdoor"), true, "cutout");

        blockItem(ModBlocks.SHALE_STAIRS);
        blockItem(ModBlocks.SHALE_SLAB);
        blockItem(ModBlocks.SHALE_PRESSURE_PLATE);
        blockItem(ModBlocks.SHALE_FENCE_GATE);
        blockItem(ModBlocks.SHALE_TRAPDOOR, "_bottom");

        customLamp();

        makeCrop(((CropBlock) ModBlocks.RADISH_CROP.get()), "radish_crop_stage", "radish_crop_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.GOJI_BERRY_BUSH.get()), "goji_berry_bush_stage", "goji_berry_bush_stage");

        logBlock((RotatedPillarBlock) ModBlocks.BLOODWOOD_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBlocks.BLOODWOOD_WOOD.get()), blockTexture(ModBlocks.BLOODWOOD_LOG.get()), blockTexture(ModBlocks.BLOODWOOD_LOG.get()));
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_WOOD.get()), blockTexture(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()), blockTexture(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()));

        blockItem(ModBlocks.BLOODWOOD_LOG);
        blockItem(ModBlocks.BLOODWOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_LOG);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
        blockWithItem(ModBlocks.BLOODWOOD_PLANKS);
        saplingBlock(ModBlocks.BLOODWOOD_SAPLING);
        leavesBlock(ModBlocks.BLOODWOOD_LEAVES);
    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((RadishCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "block/" + textureName + state.getValue(((RadishCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(GojiBerryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "block/" + textureName + state.getValue(GojiBerryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.BISMUTH_LAMP.get()).forAllStates(state -> {
            if(state.getValue(BismuthLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "block/" + "bismuth_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "block/" + "bismuth_lamp_off")))};
            }
        });

        simpleBlockItem(ModBlocks.BISMUTH_LAMP.get(), models().cubeAll("bismuth_lamp_on",
                ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "block/" + "bismuth_lamp_on")));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("firstmod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("firstmod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
