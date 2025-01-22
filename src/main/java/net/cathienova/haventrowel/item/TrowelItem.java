package net.cathienova.haventrowel.item;

import net.cathienova.haventrowel.config.HavenConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrowelItem extends Item
{
    public TrowelItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        if (context.getLevel().isClientSide())
        {
            return InteractionResult.SUCCESS;
        }

        ServerPlayer player = (ServerPlayer) context.getPlayer();
        if (player == null)
        {
            return InteractionResult.FAIL;
        }

        ArrayList<ItemStack> blockItems = new ArrayList<>();

        getRandomBlock(player, blockItems);

        if (blockItems.isEmpty())
        {
            return InteractionResult.FAIL;
        }

        ItemStack randomBlockItem = blockItems.get(new Random().nextInt(blockItems.size()));
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        AABB targetBlockArea = new AABB(Vec3.atCenterOf(pos), Vec3.atCenterOf(pos.offset(1, 1, 1)));
        if (!context.getLevel().getEntities(null, targetBlockArea).isEmpty() || !context.getLevel().getBlockState(pos).isAir())
        {
            return InteractionResult.FAIL;
        }

        BlockPlaceContext placeContext = new BlockPlaceContext(context);
        BlockState blockState = ((BlockItem) randomBlockItem.getItem()).getBlock().getStateForPlacement(placeContext);

        if (blockState == null)
        {
            return InteractionResult.FAIL;
        }

        if (!context.getLevel().setBlock(pos, blockState, 3))
        {
            return InteractionResult.FAIL;
        }

        context.getLevel().gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, blockState));
        context.getLevel().playSound(player, pos, blockState.getSoundType().getPlaceSound(), net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!player.isCreative())
        {
            randomBlockItem.shrink(1);

            /*if (HavenConfig.enable_trowel_durability)
                context.getItemInHand().hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));*/
        }

        return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
    }

    private static void getRandomBlock(ServerPlayer player, ArrayList<ItemStack> blockItems)
    {
        for (int i = 0; i < 9; i++)
        {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack.getItem() instanceof BlockItem)
            {
                if (!isBlacklisted(((BlockItem) itemStack.getItem()).getBlock().defaultBlockState()))
                    blockItems.add(itemStack);
            }
        }

        if (HavenConfig.enable_inventory_blocks)
        {
            for (int i = 9; i < 36; i++)
            {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack.getItem() instanceof BlockItem)
                {
                    if (!isBlacklisted(((BlockItem) itemStack.getItem()).getBlock().defaultBlockState()))
                        blockItems.add(itemStack);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag)
    {
        super.appendHoverText(stack, context, tooltip, tooltipFlag);

        if (HavenConfig.enable_inventory_blocks)
            tooltip.add(Component.translatable("item.haventrowel.trowel.tooltip.inventory").withStyle(ChatFormatting.GOLD));
        else
            tooltip.add(Component.translatable("item.haventrowel.trowel.tooltip.hotbar").withStyle(ChatFormatting.GOLD));
    }

    private static boolean isBlacklisted(BlockState state) {
        String blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();
        if (HavenConfig.trowel_blacklist == null || HavenConfig.trowel_blacklist.isEmpty()) return false;
        for (String id : HavenConfig.trowel_blacklist) {
            if (id.startsWith("#")) {
                if (state.is(TagKey.create(Registries.BLOCK, ResourceLocation.parse(id.substring(1))))) {
                    return true;
                }
            } else if (id.equals(blockId)) {
                return true;
            }
        }
        return false;
    }
}
