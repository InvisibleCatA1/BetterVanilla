package com.invisiblecat.bettervanilla.entity.renderer;

import com.invisiblecat.bettervanilla.BetterVanilla;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class TempleGuardRenderer extends ZombieRenderer {
    public TempleGuardRenderer(EntityRendererProvider.Context p_174456_) {
        super(p_174456_);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return new ResourceLocation(BetterVanilla.MODID, "textures/entity/temple_guard.png");
    }
}
