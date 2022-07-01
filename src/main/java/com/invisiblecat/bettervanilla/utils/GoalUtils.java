package com.invisiblecat.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.LivingEntity;

public class GoalUtils {

    public static BlockPos getBlockBehindEntity(LivingEntity e) {
        if (e == null) return null;
        Vec3i look = e.getDirection().getNormal().multiply(-1);
        return e.getOnPos().offset(look);
    }
}
