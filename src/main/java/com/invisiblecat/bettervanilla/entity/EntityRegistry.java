package com.invisiblecat.bettervanilla.entity;

import com.invisiblecat.bettervanilla.entity.entites.TempleGuard;
import com.invisiblecat.bettervanilla.entity.rendererFactory.TempleGuardRendererFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class EntityRegistry {

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event){
        IForgeRegistry<EntityType<?>> reg = event.getRegistry();

        reg.register(TempleGuard.TYPE);

    }
    @SubscribeEvent
    public static void registerEntityEggs(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();

        reg.register(TempleGuard.EGG);
    }
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(TempleGuard.TYPE, TempleGuardRendererFactory.INSTANCE);
    }

    // this is different than in 1.16 but everything else is the same
    // I do think this makes more sense than the other way but alas change is usually hard.
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(TempleGuard.TYPE, TempleGuard.createAttributes().build());
    }

}
