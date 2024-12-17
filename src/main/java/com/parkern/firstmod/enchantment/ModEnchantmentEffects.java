package com.parkern.firstmod.enchantment;

import com.mojang.serialization.MapCodec;
import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.enchantment.custom.BoltEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, FirstMod.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BOLT =
            ENTITY_ENCHANTMENT_EFFECTS.register("bolt", () -> BoltEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
