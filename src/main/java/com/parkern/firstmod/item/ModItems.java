package com.parkern.firstmod.item;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.component.ModDataComponents;
import com.parkern.firstmod.item.custom.MagicWandItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
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

    public static final DeferredItem<SwordItem> TITANITE_SWORD = ITEMS.register("titanite_sword",
            () -> new SwordItem(ModToolTiers.TITANITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.TITANITE, 5, 3f))));
    public static final DeferredItem<PickaxeItem> TITANITE_PICKAXE = ITEMS.register("titanite_pickaxe",
                () -> new PickaxeItem(ModToolTiers.TITANITE, new Item.Properties()
                        .attributes(PickaxeItem.createAttributes(ModToolTiers.TITANITE, 1.0f, -2.8f))));
    public static final DeferredItem<ShovelItem> TITANITE_SHOVEL = ITEMS.register("titanite_shovel",
                () -> new ShovelItem(ModToolTiers.TITANITE, new Item.Properties()
                        .attributes(ShovelItem.createAttributes(ModToolTiers.TITANITE, 1.5f, -3.0f))));
    public static final DeferredItem<AxeItem> TITANITE_AXE = ITEMS.register("titanite_axe",
                () -> new AxeItem(ModToolTiers.TITANITE, new Item.Properties()
                        .attributes(AxeItem.createAttributes(ModToolTiers.TITANITE, 6.0f, -3.2f))));
    public static final DeferredItem<HoeItem> TITANITE_HOE = ITEMS.register("titanite_hoe",
                () -> new HoeItem(ModToolTiers.TITANITE, new Item.Properties()
                        .attributes(HoeItem.createAttributes(ModToolTiers.TITANITE, 0f, -3.0f))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
