package net.cathienova.haventrowel.config;

import net.neoforged.fml.event.config.ModConfigEvent;

public class ConfigSubscriber
{
    public static void onModConfigEvent(final ModConfigEvent event)
    {
        HavenConfig.bake(event.getConfig());
    }
}