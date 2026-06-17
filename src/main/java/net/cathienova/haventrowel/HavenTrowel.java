package net.cathienova.haventrowel;

import com.mojang.logging.LogUtils;
import net.cathienova.haventrowel.config.CommonConfig;
import net.cathienova.haventrowel.config.ConfigSubscriber;
import net.cathienova.haventrowel.datagen.DataGenerators;
import net.cathienova.haventrowel.item.ModCreativeModTabs;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

@Mod(HavenTrowel.MOD_ID)
public class HavenTrowel
{
    public static final String MOD_ID = "haventrowel";
    public static final String MOD_NAME = "Haven Trowel";
    static final ModConfigSpec commonSpec;
    public static final CommonConfig c_config;

    static
    {
        final Pair<CommonConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        commonSpec = specPair.getRight();
        c_config = specPair.getLeft();
    }

    public HavenTrowel(IEventBus bus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, commonSpec);
        ModItems.register(bus);
        ModCreativeModTabs.register(bus);
        bus.addListener(ConfigSubscriber::onModConfigEvent);
        bus.addListener(DataGenerators::gatherData);
    }

    public static void Log(String message)
    {
        LogUtils.getLogger().info("[" + MOD_NAME + "] " + message);
    }

    public static Identifier loc(String name)
    {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}