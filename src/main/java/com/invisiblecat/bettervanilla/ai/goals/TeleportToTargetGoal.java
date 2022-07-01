package com.invisiblecat.bettervanilla.ai.goals;

import com.invisiblecat.utils.GoalUtils;
import com.invisiblecat.utils.TimeUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class TeleportToTargetGoal extends Goal {
    private Mob mob;

    private TimeUtils timeUtils = new TimeUtils();

    public TeleportToTargetGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return this.mob.getHealth() < this.mob.getMaxHealth() - 20 && this.mob.getTarget() != null && this.mob.distanceToSqr(this.mob.getTarget()) > 90;
    }

    @Override
    public void start() {
        System.out.println(GoalUtils.getBlockBehindEntity(this.mob.getTarget()));
        timeUtils.reset();
        new Thread(() -> {
            while (!timeUtils.hasTimePassed(2500, true)) {
                this.mob.setDeltaMovement(0, 0, 0);
                for (int i = 0; i < 360; i++) {
                    this.mob.getLevel().addParticle(ParticleTypes.ENCHANT.getType(), this.mob.getX(), this.mob.getY() + 1, this.mob.getZ(), Math.cos(i) + 0.25d, Math.tan(i) + 0.15, Math.sin(i) + 0.25d);
                }
            }
            this.mob.moveTo(new Vec3(GoalUtils.getBlockBehindEntity(this.mob.getTarget()).getX(), GoalUtils.getBlockBehindEntity(this.mob.getTarget()).getY() + 1, GoalUtils.getBlockBehindEntity(this.mob.getTarget()).getZ()));
            this.mob.setDeltaMovement(this.mob.getDeltaMovement());
        }).start();
    }

    @Override
    public void stop() {
        this.mob.setDeltaMovement(this.mob.getDeltaMovement());
    }

}
