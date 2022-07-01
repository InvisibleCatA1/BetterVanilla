package com.invisiblecat.bettervanilla.entity.rendererFactory;

import com.invisiblecat.bettervanilla.entity.renderer.TempleGuardRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;

public class TempleGuardRendererFactory implements EntityRendererProvider<Zombie> {

    public static TempleGuardRendererFactory INSTANCE = new TempleGuardRendererFactory();

    @Override
    public EntityRenderer<Zombie> create(Context p_174010_) {
        return new TempleGuardRenderer(p_174010_);
    }
}
