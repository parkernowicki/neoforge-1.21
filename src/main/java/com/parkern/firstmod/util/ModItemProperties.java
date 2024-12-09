package com.parkern.firstmod.util;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.component.ModDataComponents;
import com.parkern.firstmod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.MAGIC_WAND.get(), ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "used"),
                (stack, level, entity, seed) -> stack.get(ModDataComponents.COORDINATES) != null ? 1f : 0f);
    }
}
