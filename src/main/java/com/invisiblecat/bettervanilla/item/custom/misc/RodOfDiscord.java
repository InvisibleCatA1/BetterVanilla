package com.invisiblecat.bettervanilla.item.custom.misc;

import com.invisiblecat.bettervanilla.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RodOfDiscord extends Item {
    public static Properties properties = new Properties().tab(CreativeModeTab.TAB_COMBAT);
    public static Item INSTANCE = new RodOfDiscord(properties).setRegistryName("tprod");
    public RodOfDiscord(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        BlockPos tpPos = BlockUtils.getBlockAtCursor(player, 50, true);

        if (tpPos != null) {
            player.teleportTo(tpPos.getX(), tpPos.getY() + 1, tpPos.getZ());
            player.fallDistance = 7.0f;

            for (int i = 0; i < 360; i++) {
                if (i % 10 == 0) {
                    level.addParticle(ParticleTypes.ENCHANT.getType(), tpPos.getX(), tpPos.getY() + 1 + Math.tan(i), tpPos.getZ(), Math.cos(i) + 0.25d, 0.15, Math.sin(i) + 0.25d);
                }
            }
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
