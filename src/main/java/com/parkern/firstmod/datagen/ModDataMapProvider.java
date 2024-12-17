package com.parkern.firstmod.datagen;

import com.parkern.firstmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.PROPANE.getId(), new FurnaceFuel(6000), false);

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.RADISH_SEEDS.getId(), new Compostable(0.25f), false)
                .add(ModItems.RADISH.getId(), new Compostable(0.45f), false);
    }
}
