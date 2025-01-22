package net.cathienova.haventrowel.item;

import net.cathienova.haventrowel.HavenTrowel;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HavenTrowel.MOD_ID);

    public static String HavenTrowel_tab_title = "itemgroup.haventrowel.haventrowel_tab";
    public static final Supplier<CreativeModeTab> Haven_TAB = CREATIVE_MODE_TABS.register("haventrowel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.trowel.get()))
                    .title(Component.translatable(HavenTrowel_tab_title))
                    .displayItems((pParameters, add) -> {

                        add.accept(new ItemStack(ModItems.trowel.get()));
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
