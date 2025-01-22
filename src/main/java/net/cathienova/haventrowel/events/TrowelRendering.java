package net.cathienova.haventrowel.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.config.HavenConfig;
import net.cathienova.haventrowel.item.TrowelItem;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;

@EventBusSubscriber(modid = HavenTrowel.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class TrowelRendering
{
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void renderBlockHighlight(RenderHighlightEvent.Block event) {
        if (event.getTarget().getType() != HitResult.Type.BLOCK) return;

        BlockHitResult rtr = event.getTarget();
        Entity entity = event.getCamera().getEntity();
        if (!(entity instanceof Player player)) return;
        if (!HavenConfig.enable_trowel_highlight) return;

        ItemStack itemStack = player.getMainHandItem();
        if (!(itemStack.getItem() instanceof TrowelItem)) return;

        BlockPos pos = rtr.getBlockPos().relative(rtr.getDirection());

        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource bufferSource = event.getMultiBufferSource();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.LINES);

        double partialTicks = event.getDeltaTracker().getGameTimeDeltaPartialTick(true);
        double dx = player.xOld + (player.getX() - player.xOld) * partialTicks;
        double dy = player.yOld + player.getEyeHeight() + (player.getY() - player.yOld) * partialTicks;
        double dz = player.zOld + (player.getZ() - player.zOld) * partialTicks;

        AABB aabb = new AABB(pos).move(-dx, -dy, -dz);

        LevelRenderer.renderLineBox(poseStack, vertexConsumer, aabb,
                (float)HavenConfig.trowel_highlight_red, (float)HavenConfig.trowel_highlight_green, (float)HavenConfig.trowel_highlight_blue, (float)HavenConfig.trowel_highlight_alpha);

        event.setCanceled(true);
    }
}
