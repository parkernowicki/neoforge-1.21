package com.parkern.firstmod.entity;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.entity.custom.GeckoEntity;
import com.parkern.firstmod.entity.custom.GrouseEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FirstMod.MOD_ID);

    public static final Supplier<EntityType<GeckoEntity>> GECKO =
            ENTITY_TYPES.register("gecko", () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.35f).build("gecko"));
    public static final Supplier<EntityType<GrouseEntity>> GROUSE =
            ENTITY_TYPES.register("grouse", () -> EntityType.Builder.of(GrouseEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.6f).build("grouse"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
