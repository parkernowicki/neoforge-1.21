package com.parkern.firstmod.mixin;

import net.minecraft.world.entity.animal.Sheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Sheep.class)
public class TestMixin {
    @Inject(method = "ate", at = @At("HEAD"))
    private void ate(CallbackInfo info) {
        System.out.println("Yum!!!");
    }
}
