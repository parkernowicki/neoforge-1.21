package com.parkern.firstmod.item;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.component.ModDataComponents;
import com.parkern.firstmod.item.custom.MagicWandItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FirstMod.MOD_ID);

    public static final DeferredItem<Item> RUBBER_DUCK = ITEMS.register("rubber_duck",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DUCK_ESSENCE = ITEMS.register("duck_essence",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHALE_OIL = ITEMS.register("shale_oil",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_TITANITE = ITEMS.register("raw_titanite",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MAGIC_WAND = ITEMS.register("magic_wand",
            () -> new MagicWandItem(new Item.Properties().durability(128)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if(Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.firstmod.magic_wand.shift_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.firstmod.magic_wand"));
                    }
                    if(stack.get(ModDataComponents.COORDINATES) != null) {
                        tooltipComponents.add(Component.literal("Last block changed at " + stack.get(ModDataComponents.COORDINATES)));
                    }

                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> BREAD_PUDDING = ITEMS.register("bread_pudding",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BREAD_PUDDING)));
    public static final DeferredItem<Item> PROPANE = ITEMS.register("propane",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
