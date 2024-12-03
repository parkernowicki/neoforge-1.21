package com.parkern.firstmod.datagen;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SHALE_BLOCK);
        blockWithItem(ModBlocks.TITANITE_ORE);

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
