package com.invisiblecat.bettervanilla.entity.entites;

import com.invisiblecat.bettervanilla.ai.goals.TeleportToTargetGoal;
import com.invisiblecat.bettervanilla.BetterVanilla;
import com.invisiblecat.bettervanilla.utils.EntityUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TempleGuard extends Zombie {
    public static EntityType<TempleGuard> TYPE = (EntityType<TempleGuard>)
            EntityType.Builder.of(TempleGuard::new, MobCategory.MONSTER)
                    .build("temple_guard")
                    .setRegistryName(BetterVanilla.MODID, "temple_guard");

    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0x000000, 0x0552b0);

    @Override
    protected boolean isSunSensitive() {return false;}

    @Override
    public boolean canBreakDoors() {
        return true;
    }

    @Override
    public boolean canHoldItem(ItemStack p_34332_) {
        return true;
    }

    @Override
    public boolean wantsToPickUp(ItemStack p_182400_) {
        return true;
    }

    @Override
    public boolean isBaby() {
        return false;
    }
    //SOUNDS
    @Override
    protected SoundEvent getHurtSound(DamageSource p_34327_) {
        return SoundEvents.ANVIL_HIT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHAIN_STEP;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RESPAWN_ANCHOR_DEPLETE;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.BASALT_STEP;
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2f, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1.3, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, TempleGuard.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, false));
        this.targetSelector.addGoal(5, new LeapAtTargetGoal(this, 0.31F));
        this.targetSelector.addGoal(11, new TeleportToTargetGoal(this));
        this.targetSelector.addGoal(6, new RandomStrollGoal(this, 1));


    }

    public TempleGuard(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.21D)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.ATTACK_DAMAGE, 9)
                .add(Attributes.ATTACK_SPEED, 5)
                .add(Attributes.ATTACK_KNOCKBACK, 1.2D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2)
                .add(Attributes.FOLLOW_RANGE, 100)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 12);
    }
}
