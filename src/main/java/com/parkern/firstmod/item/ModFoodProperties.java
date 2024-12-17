package com.parkern.firstmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties BREAD_PUDDING = new FoodProperties.Builder().nutrition(7).saturationModifier(0.7f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f).build();
    public static final FoodProperties RADISH = new FoodProperties.Builder().nutrition(2).saturationModifier(0.6f).build();
    public static final FoodProperties GOJI_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.15f).fast().build();
}
