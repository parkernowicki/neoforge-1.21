package com.parkern.firstmod.sound;

import com.parkern.firstmod.FirstMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, FirstMod.MOD_ID);

    public static final Supplier<SoundEvent> MAGIC_WAND_USE = registerSoundEvent("magic_wand_use");

    public static final Supplier<SoundEvent> WITHERER_BREAK = registerSoundEvent("witherer_break");
    public static final Supplier<SoundEvent> WITHERER_STEP = registerSoundEvent("witherer_step");
    public static final Supplier<SoundEvent> WITHERER_PLACE = registerSoundEvent("witherer_place");
    public static final Supplier<SoundEvent> WITHERER_HIT = registerSoundEvent("witherer_hit");
    public static final Supplier<SoundEvent> WITHERER_FALL = registerSoundEvent("witherer_fall");

    public static final DeferredSoundType WITHERER_SOUNDS = new DeferredSoundType(1f, 1f,
            ModSounds.WITHERER_BREAK, ModSounds.WITHERER_STEP, ModSounds.WITHERER_PLACE,
            ModSounds.WITHERER_HIT, ModSounds.WITHERER_FALL);

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
