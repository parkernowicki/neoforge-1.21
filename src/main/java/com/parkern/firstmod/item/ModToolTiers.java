package com.parkern.firstmod.item;

import com.parkern.firstmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier TITANITE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_TITANITE_TOOL,
            750, 6f, 3f, 15, () -> Ingredient.of(ModItems.RAW_TITANITE));
}
