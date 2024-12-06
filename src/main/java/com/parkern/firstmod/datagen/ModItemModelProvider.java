package com.parkern.firstmod.datagen;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import com.parkern.firstmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RUBBER_DUCK.get());
        basicItem(ModItems.DUCK_ESSENCE.get());
        basicItem(ModItems.SHALE_OIL.get());
        basicItem(ModItems.RAW_TITANITE.get());
        basicItem(ModItems.MAGIC_WAND.get());
        basicItem(ModItems.BREAD_PUDDING.get());
        basicItem(ModItems.PROPANE.get());

        buttonItem(ModBlocks.SHALE_BUTTON, ModBlocks.SHALE_BLOCK);
        fenceItem(ModBlocks.SHALE_FENCE, ModBlocks.SHALE_BLOCK);
        wallItem(ModBlocks.SHALE_WALL, ModBlocks.SHALE_BLOCK);

        basicItem(ModBlocks.SHALE_DOOR.asItem());

        handheldItem(ModItems.TITANITE_SWORD);
        handheldItem(ModItems.TITANITE_PICKAXE);
        handheldItem(ModItems.TITANITE_SHOVEL);
        handheldItem(ModItems.TITANITE_AXE);
        handheldItem(ModItems.TITANITE_HOE);
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
