package com.parkern.firstmod.potion;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, FirstMod.MOD_ID);

    public static final Holder<Potion> STICKY_POTION = POTIONS.register("sticky_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.STICKY_EFFECT, 1200, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
