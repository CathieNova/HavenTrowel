package net.cathienova.haventrowel.item;

import net.cathienova.haventrowel.HavenTrowel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HavenTrowel.MOD_ID);

    public static final DeferredItem<Item> trowel = ITEMS.register("trowel",
            () -> new TrowelItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).durability(512)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
