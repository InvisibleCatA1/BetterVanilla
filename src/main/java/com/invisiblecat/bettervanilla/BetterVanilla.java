    package com.invisiblecat.bettervanilla;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.invisiblecat.bettervanilla.block.BlockRegistry;
import com.invisiblecat.bettervanilla.enchantment.EnchantmentRegistry;
import com.invisiblecat.bettervanilla.entity.EntityRegistry;
import com.invisiblecat.bettervanilla.item.ItemRegistry;
import com.invisiblecat.bettervanilla.world.ore.OreGeneration;
import com.invisiblecat.bettervanilla.world.structure.ConfiguredStructures;
import com.invisiblecat.bettervanilla.world.structure.Structures;
import com.invisiblecat.bettervanilla.world.structure.structures.SmallRuins;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
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

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

//        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
//        forgeBus.addListener(EventPriority.NORMAL, SmallRuins::setupStructureSpawns);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        OreGeneration.registerOres();

        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        event.enqueueWork(() -> {
//            ConfiguredStructures.registerConfiguredStructures();
//            Structures.setupStructures();
        });
    }


//    private static Method GETCODEC_METHOD;
//    public void addDimensionalSpacing(final WorldEvent.Load event) {
//        if(event.getWorld() instanceof ServerLevel serverLevel){
//            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
//
//            // Skip superflat worlds to prevent issues with it. Plus, users don't want structures clogging up their superflat worlds.
//            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
//                return;
//            }
//
//            StructureSettings worldStructureConfig = chunkGenerator.getSettings();
//
//            //////////// BIOME BASED STRUCTURE SPAWNING ////////////
//            /*
//             * NOTE: BiomeLoadingEvent from Forge API does not work with structures anymore.
//             * Instead, we will use the below to add our structure to overworld biomes.
//             * Remember, this is temporary until Forge API finds a better solution for adding structures to biomes.
//             */
//
//            // Create a mutable map we will use for easier adding to biomes
//            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap = new HashMap<>();
//
//            // Add the resourcekey of all biomes that this Configured Structure can spawn in.
//            for(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
//                // Skip all ocean, end, nether, and none category biomes.
//                // You can do checks for other traits that the biome has.
//                Biome.BiomeCategory biomeCategory = biomeEntry.getValue().getBiomeCategory();
//                if(biomeCategory != Biome.BiomeCategory.OCEAN && biomeCategory != Biome.BiomeCategory.THEEND && biomeCategory != Biome.BiomeCategory.NETHER && biomeCategory != Biome.BiomeCategory.NONE) {
//                    associateBiomeToConfiguredStructure(STStructureToMultiMap, ConfiguredStructures.CONFIGURED_SMALL_RUINS, biomeEntry.getKey());
//                }
//            }
//
//            // Alternative way to add our structures to a fixed set of biomes by creating a set of biome resource keys.
//            // To create a custom resource key that points to your own biome, do this:
//            // ResourceKey.of(Registry.BIOME_REGISTRY, new ResourceLocation("modid", "custom_biome"))
////            ImmutableSet<ResourceKey<Biome>> overworldBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
////                    .add(Biomes.FOREST)
////                    .add(Biomes.MEADOW)
////                    .add(Biomes.PLAINS)
////                    .add(Biomes.SAVANNA)
////                    .add(Biomes.SNOWY_PLAINS)
////                    .add(Biomes.SWAMP)
////                    .add(Biomes.SUNFLOWER_PLAINS)
////                    .add(Biomes.TAIGA)
////                    .build();
////            overworldBiomes.forEach(biomeKey -> associateBiomeToConfiguredStructure(STStructureToMultiMap, STConfiguredStructures.CONFIGURED_RUN_DOWN_HOUSE, biomeKey));
//
//            // Grab the map that holds what ConfigureStructures a structure has and what biomes it can spawn in.
//            // Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
//            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
//            worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !STStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);
//
//            // Add our structures to the structure map/multimap and set the world to use this combined map/multimap.
//            STStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));
//
//            // Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
//            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();
//
//
//            //////////// DIMENSION BASED STRUCTURE SPAWNING ////////////
//
//            /*
//             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
//             * They will handle your structure spacing for your if you add to Registry.NOISE_GENERATOR_SETTINGS_REGISTRY in your structure's registration.
//             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
//             * If you are using mixins, you can call the codec method with an invoker mixin instead of using reflection.
//             */
//            try {
//                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
//                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
//                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
//            }
//            catch(Exception e){
//                BetterVanilla.LOGGER.error("Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
//            }
//
//            /*
//             * Makes sure this chunkgenerator and datapack dimensions can spawn our structure.
//             *
//             * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
//             * Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
//             */
//            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureConfig.structureConfig());
//            tempMap.putIfAbsent(Structures.SMALL_RUINS.get(), StructureSettings.DEFAULTS.get(Structures.SMALL_RUINS.get()));
//            worldStructureConfig.structureConfig = tempMap;
//
//            /*
//             * The above three lines can be changed to do dimension blacklists/whitelist for your structure.
//             * (Don't forget to attempt to remove your structure too from the map if you are blacklisting that dimension in case it already has the structure)
//             *
//             * If you do start whitelisting/blacklisting by dimensions instead of the default adding the spacing to all dimensions,
//             * you may want to deep copy the structureConfig field first due to the fact that two dimensions using minecraft:overworld noise setting shares the same instance.
//             * The deep copying would let you only add to one dimension and not the other instead of your changes applying to both dimensions together at same time.
//             * https://github.com/TelepathicGrunt/RepurposedStructures/blob/latest-released/src/main/java/com/telepathicgrunt/repurposedstructures/misc/NoiseSettingsDeepCopier.java
//             */
//        }
//    }

    /**
     * Helper method that handles setting up the map to multimap relationship to help prevent issues.
     */
    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
        STStructureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = STStructureToMultiMap.get(configuredStructureFeature.feature);
        if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
            BetterVanilla.LOGGER.error("""
                    Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
                    This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
                    The two conflicting ConfiguredStructures are: {}, {}
                    The biome that is attempting to be shared: {}
                """,
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
                    biomeRegistryKey
            );
        }
        else{
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
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
