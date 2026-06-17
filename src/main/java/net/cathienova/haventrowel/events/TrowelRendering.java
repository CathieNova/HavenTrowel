package net.cathienova.haventrowel.events;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.config.HavenConfig;
import net.cathienova.haventrowel.item.TrowelItem;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ExtractBlockOutlineRenderStateEvent;

@EventBusSubscriber(modid = HavenTrowel.MOD_ID, value = Dist.CLIENT)
public class TrowelRendering
{
    @SubscribeEvent
    public static void renderBlockHighlight(ExtractBlockOutlineRenderStateEvent event)
    {
        Entity entity = event.getCamera().entity();
        if (!(entity instanceof Player player)) return;
        if (!HavenConfig.enable_trowel_highlight) return;

        ItemStack itemStack = player.getMainHandItem();
        if (!(itemStack.getItem() instanceof TrowelItem)) return;

        BlockPos pos = event.getHitResult().getBlockPos().relative(event.getHitResult().getDirection());
        Vec3 cameraPosition = event.getCamera().position();
        AABB aabb = new AABB(pos).move(-cameraPosition.x(), -cameraPosition.y(), -cameraPosition.z());
        int color = ARGB.colorFromFloat(
                (float) HavenConfig.trowel_highlight_alpha,
                (float) HavenConfig.trowel_highlight_red,
                (float) HavenConfig.trowel_highlight_green,
                (float) HavenConfig.trowel_highlight_blue);

        event.addCustomRenderer((renderState, buffer, poseStack, translucentPass, levelRenderState) ->
        {
            if (renderState.isTranslucent() != translucentPass) return false;

            ShapeRenderer.renderShape(
                    poseStack,
                    buffer.getBuffer(RenderTypes.lines()),
                    Shapes.create(aabb),
                    0.0,
                    0.0,
                    0.0,
                    color,
                    1);
            return true;
        });
    }
}