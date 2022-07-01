package com.invisiblecat.bettervanilla.utils;

import com.invisiblecat.bettervanilla.BetterVanilla;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Objects;

public class BlockUtils {
    // easy to add simple blocks
    public static Block createBasicBlock(String name, Material material){
        return new Block(Block.Properties.of(material)).setRegistryName(BetterVanilla.MODID, name);
    }

    public static Block createBasicBlock(String name, Material material, float strength){
        return new Block(Block.Properties.of(material).strength(strength)).setRegistryName(BetterVanilla.MODID, name);
    }

    public static Item createBlockItem(Block block, CreativeModeTab group){
        return new BlockItem(block, new Item.Properties().tab(group)).setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

    // idk
    public static BlockPos getBlockAtCursor(Player player, double distance, boolean ignoreFluids) {
        ClipContext.Fluid fluidMode = ignoreFluids ? ClipContext.Fluid.NONE : ClipContext.Fluid.ANY;

        ClipContext rayTraceContext = new ClipContext(player.getEyePosition(1), player.getEyePosition(1).add(player.getLookAngle().scale(distance)), ClipContext.Block.COLLIDER, fluidMode, player);
        BlockHitResult blockHit = player.level.clip(rayTraceContext);

        if(blockHit.getType() == BlockHitResult.Type.MISS){
            return null;
        }
        else {
            return blockHit.getBlockPos();
        }

    }
}
