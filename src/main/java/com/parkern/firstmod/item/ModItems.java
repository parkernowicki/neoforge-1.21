package com.parkern.firstmod.item;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.item.custom.MagicWandItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

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
            () -> new MagicWandItem(new Item.Properties().durability(128)));
    public static final DeferredItem<Item> BREAD_PUDDING = ITEMS.register("bread_pudding",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BREAD_PUDDING)));
    public static final DeferredItem<Item> PROPANE = ITEMS.register("propane",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
