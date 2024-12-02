package com.parkern.firstmod.datagen;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
    }
}
