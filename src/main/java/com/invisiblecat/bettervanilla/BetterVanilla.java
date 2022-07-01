    package com.invisiblecat.bettervanilla;

import com.invisiblecat.bettervanilla.block.BlockRegistry;
import com.invisiblecat.bettervanilla.enchantment.EnchantmentRegistry;
import com.invisiblecat.bettervanilla.entity.EntityRegistry;
import com.invisiblecat.bettervanilla.item.ItemRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bettervanilla")
public class BetterVanilla
{
    // Directly reference a log4j logger.

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "bettervanilla";

    public BetterVanilla() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
    }


    

    // Registering stuff
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            LOGGER.info("Registering Blocks");


            BlockRegistry.registerBlocks(event);

        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            LOGGER.info("Registering Items");
            // Add item registry calls here.
            // event.getRegistry.register(<item variable>)

            ItemRegistry.registerItems(event);
            BlockRegistry.registerBlockItems(event);
            EntityRegistry.registerEntityEggs(event);

        }


        @SubscribeEvent
        public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
            BetterVanilla.LOGGER.info("Registering Entities");
            EntityRegistry.registerEntities(event);

        }

        @SubscribeEvent
        public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
            BetterVanilla.LOGGER.info("Registering Biomes");


        }

        // sentity stuff

        @SubscribeEvent
        public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
            EntityRegistry.entityRenderers(event);
        }
        @SubscribeEvent
        public static void attributeRegister(EntityAttributeCreationEvent event) {
            EntityRegistry.onAttributeCreate(event);
        }
// idk
        @SubscribeEvent
        public static void registerEnchantments(final RegistryEvent.Register<Enchantment> event){
            EnchantmentRegistry.registerEnchantments(event);
        }

    }


}
