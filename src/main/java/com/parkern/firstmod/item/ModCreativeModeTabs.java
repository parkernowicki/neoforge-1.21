package com.parkern.firstmod.item;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    public static final Supplier<CreativeModeTab> FIRSTMOD_ITEMS_TAB = CREATIVE_MODE_TAB.register("firstmod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUBBER_DUCK.get()))
                    .title(Component.translatable("creativetab.firstmod.firstmod_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RUBBER_DUCK);
                        output.accept(ModItems.DUCK_ESSENCE);
                        output.accept(ModBlocks.SHALE_BLOCK);
                        output.accept(ModItems.SHALE_OIL);
                        output.accept(ModBlocks.TITANITE_ORE);
                        output.accept(ModItems.RAW_TITANITE);
                        output.accept(ModItems.MAGIC_WAND);
                        output.accept(ModBlocks.WITHERER);

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
