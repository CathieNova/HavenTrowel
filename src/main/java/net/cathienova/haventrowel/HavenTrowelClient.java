package net.cathienova.haventrowel;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = HavenTrowel.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HavenTrowelClient {
    public HavenTrowelClient() {
        ModLoadingContext.get().getActiveContainer().getEventBus().addListener(this::clientSetup);
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event)
    {
        registerRenderers();
    }
    @OnlyIn(Dist.CLIENT)
    private void registerRenderers()
    {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
    }
}
