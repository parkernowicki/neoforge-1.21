package com.parkern.firstmod.item.custom;

import com.parkern.firstmod.block.ModBlocks;
import com.parkern.firstmod.component.ModDataComponents;
import com.parkern.firstmod.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class MagicWandItem extends Item {
    private static final Map<Block, Block> MAGIC_WAND_MAP =
            Map.of(
                    Blocks.COPPER_ORE, Blocks.COPPER_BLOCK,
                    Blocks.COAL_ORE, Blocks.COAL_BLOCK,
                    Blocks.IRON_ORE, Blocks.IRON_BLOCK,
                    Blocks.GOLD_ORE, Blocks.GOLD_BLOCK,
                    Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK,
                    Blocks.REDSTONE_ORE, Blocks.REDSTONE_BLOCK,
                    Blocks.EMERALD_ORE, Blocks.EMERALD_BLOCK,
                    Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK,
                    ModBlocks.TITANITE_ORE.get(), Blocks.DRAGON_HEAD
            );

    public MagicWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(MAGIC_WAND_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), MAGIC_WAND_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), ModSounds.MAGIC_WAND_USE.get(), SoundSource.BLOCKS);

                ((ServerLevel) level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, clickedBlock.defaultBlockState()),
                        context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.0,
                        context.getClickedPos().getZ() + 0.5, 5, 0, 0, 0, 1);

                context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());
            }
        }

        return InteractionResult.SUCCESS;
    }
}
