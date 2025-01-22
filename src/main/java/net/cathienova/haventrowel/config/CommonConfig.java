package net.cathienova.haventrowel.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class CommonConfig {
    public final ModConfigSpec.BooleanValue enable_trowel_durability;
    public final ModConfigSpec.BooleanValue enable_inventory_blocks;
    public final ModConfigSpec.BooleanValue enable_trowel_highlight;
    public final ModConfigSpec.ConfigValue<List<String>> trowel_blacklist;
    public final ModConfigSpec.DoubleValue trowel_highlight_red;
    public final ModConfigSpec.DoubleValue trowel_highlight_green;
    public final ModConfigSpec.DoubleValue trowel_highlight_blue;
    public final ModConfigSpec.DoubleValue trowel_highlight_alpha;

    public CommonConfig(ModConfigSpec.Builder builder) {
        enable_trowel_durability = builder.comment("If true, it will have durability when used.").define("enable_trowel_durability", true);
        enable_inventory_blocks = builder.comment("If true, the trowel will use the inventory blocks as well as hotbar blocks, if false it will only use hotbar blocks.").define("enable_inventory_blocks", false);
        enable_trowel_highlight = builder.comment("If true, the trowel will highlight the block that will be placed.").define("enable_trowel_highlight", true);

        trowel_highlight_red = builder.comment("The red value of the highlight color (0.0 - 1.0).")
                .defineInRange("trowel_highlight_red", 1.0f, 0.0f, 1.0f);
        trowel_highlight_green = builder.comment("The green value of the highlight color (0.0 - 1.0).")
                .defineInRange("trowel_highlight_green", 0.0f, 0.0f, 1.0f);
        trowel_highlight_blue = builder.comment("The blue value of the highlight color (0.0 - 1.0).")
                .defineInRange("trowel_highlight_blue", 1.0f, 0.0f, 1.0f);
        trowel_highlight_alpha = builder.comment("The alpha value of the highlight color (0.0 - 1.0).")
                .defineInRange("trowel_highlight_alpha", 0.4f, 0.0f, 1.0f);

        List<String> trowelBlacklist = new ArrayList<>(List.of(
                "#minecraft:saplings", "#minecraft:buttons", "#minecraft:pressure_plates", "minecraft:chain", "#minecraft:doors", "#minecraft:candles",
                "#minecraft:banners", "minecraft:snow", "minecraft:moss_carpet", "minecraft:pointed_dripstone", "#c:mushrooms", "#minecraft:flowers",
                "minecraft:torch", "minecraft:ladder", "minecraft:lever", "minecraft:redstone_torch", "minecraft:redstone", "minecraft:tripwire_hook",
                "minecraft:tripwire", "minecraft:repeater", "minecraft:comparator", "minecraft:observer", "minecraft:daylight_detector", "minecraft:daylight_detector_inverted"));

        trowel_blacklist = builder.comment("The blacklist of blocks that can't be placed with the trowel.").define("trowel_blacklist", trowelBlacklist);
    }
}
