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
                        output.accept(ModBlocks.NETHER_TITANITE_ORE);
                        output.accept(ModBlocks.END_TITANITE_ORE);
                        output.accept(ModItems.RAW_TITANITE);
                        output.accept(ModItems.MAGIC_WAND);
                        output.accept(ModBlocks.WITHERER);
                        output.accept(ModItems.BREAD_PUDDING);
                        output.accept(ModItems.PROPANE);

                        output.accept(ModBlocks.SHALE_STAIRS);
                        output.accept(ModBlocks.SHALE_SLAB);
                        output.accept(ModBlocks.SHALE_PRESSURE_PLATE);
                        output.accept(ModBlocks.SHALE_BUTTON);
                        output.accept(ModBlocks.SHALE_FENCE);
                        output.accept(ModBlocks.SHALE_FENCE_GATE);
                        output.accept(ModBlocks.SHALE_WALL);
                        output.accept(ModBlocks.SHALE_DOOR);
                        output.accept(ModBlocks.SHALE_TRAPDOOR);
                        output.accept(ModBlocks.BISMUTH_LAMP);

                        output.accept(ModBlocks.BLOODWOOD_LOG);
                        output.accept(ModBlocks.BLOODWOOD_WOOD);
                        output.accept(ModBlocks.STRIPPED_BLOODWOOD_LOG);
                        output.accept(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
                        output.accept(ModBlocks.BLOODWOOD_PLANKS);
                        output.accept(ModBlocks.BLOODWOOD_SAPLING);
                        output.accept(ModBlocks.BLOODWOOD_LEAVES);

                        output.accept(ModItems.TITANITE_SWORD);
                        output.accept(ModItems.TITANITE_PICKAXE);
                        output.accept(ModItems.TITANITE_SHOVEL);
                        output.accept(ModItems.TITANITE_AXE);
                        output.accept(ModItems.TITANITE_HOE);

                        output.accept(ModItems.TITANITE_HAMMER);

                        output.accept(ModItems.TITANITE_HELMET);
                        output.accept(ModItems.TITANITE_CHESTPLATE);
                        output.accept(ModItems.TITANITE_LEGGINGS);
                        output.accept(ModItems.TITANITE_BOOTS);
                        output.accept(ModItems.TITANITE_HORSE_ARMOR);

                        output.accept(ModItems.KAUPEN_SMITHING_TEMPLATE);

                        output.accept(ModItems.KAUPEN_BOW);

                        output.accept(ModItems.BAR_BRAWL_MUSIC_DISC);

                        output.accept(ModItems.RADISH);
                        output.accept(ModItems.RADISH_SEEDS);

                        output.accept(ModItems.GOJI_BERRIES);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
