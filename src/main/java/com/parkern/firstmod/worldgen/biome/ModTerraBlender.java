package com.parkern.firstmod.worldgen.biome;

import com.parkern.firstmod.FirstMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "overworld"), 5));
    }
}
