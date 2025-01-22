package net.cathienova.haventrowel;

import com.mojang.logging.LogUtils;
import net.cathienova.haventrowel.config.CommonConfig;
import net.cathienova.haventrowel.item.ModCreativeModTabs;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

@Mod(HavenTrowel.MOD_ID)
public class HavenTrowel
{
    // Define mod id in a common place for everything to reference
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
        bus.addListener(this::setup);
        modContainer.registerConfig(ModConfig.Type.COMMON, commonSpec);
        ModItems.register(bus);
        ModCreativeModTabs.register(bus);
        bus.addListener(this::setup);
    }

    public static void Log(String message)
    {
        LogUtils.getLogger().info("[" + MOD_NAME + "] " + message);
    }

    private void setup(FMLCommonSetupEvent event)
    {
    }
    public static ResourceLocation loc(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
