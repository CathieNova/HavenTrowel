package net.cathienova.haventrowel.config;

import net.cathienova.haventrowel.HavenTrowel;
import net.neoforged.fml.config.ModConfig;

import java.util.List;

public class HavenConfig
{
    public static boolean enable_trowel_durability;
    public static boolean enable_inventory_blocks;
    public static boolean enable_trowel_highlight;
    public static double trowel_highlight_red;
    public static double trowel_highlight_green;
    public static double trowel_highlight_blue;
    public static double trowel_highlight_alpha;
    public static List<String> trowel_blacklist;

    public static void bake(ModConfig config)
    {
        enable_trowel_durability = HavenTrowel.c_config.enable_trowel_durability.get();
        enable_inventory_blocks = HavenTrowel.c_config.enable_inventory_blocks.get();
        enable_trowel_highlight = HavenTrowel.c_config.enable_trowel_highlight.get();
        trowel_highlight_red = HavenTrowel.c_config.trowel_highlight_red.get();
        trowel_highlight_green = HavenTrowel.c_config.trowel_highlight_green.get();
        trowel_highlight_blue = HavenTrowel.c_config.trowel_highlight_blue.get();
        trowel_highlight_alpha = HavenTrowel.c_config.trowel_highlight_alpha.get();
        trowel_blacklist = HavenTrowel.c_config.trowel_blacklist.get();
    }
}
