package com.invisiblecat.bettervanilla.world.ore;

import com.invisiblecat.bettervanilla.BetterVanilla;
import com.invisiblecat.bettervanilla.block.BlockRegistry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BetterVanilla.MODID)
public class OreGeneration {
    public static final ArrayList<Ore> OVERWORLD_ORES = new ArrayList<>();
    public static final ArrayList<Ore> NETHER_ORES = new ArrayList<>();
    public static final ArrayList<Ore> END_ORES = new ArrayList<>();
    public static final ArrayList<Ore> SWAMP_ORES = new ArrayList<>();


    public static void registerOres() {
        ConfiguredFeature<?, ?> GEL_ORE = Feature.ORE.configured(new OreConfiguration(List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                        BlockRegistry.GEL_ORE.defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                        BlockRegistry.DEEPSLATE_GEL_ORE.defaultBlockState())),
                7));

        PlacedFeature GEL_ORE_FEATURE = GEL_ORE.placed(List.of(
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-30), VerticalAnchor.absolute(8)),
                BiomeFilter.biome()));

        SWAMP_ORES.add(new Ore("gel_ore_feature", GEL_ORE_FEATURE, GEL_ORE));
    }

//        SWAMP_ORES.add(GEL_ORE_FEATURE);
    @SubscribeEvent
    public static void biomeLoading(BiomeLoadingEvent event) {
             List<Supplier<PlacedFeature>> features = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

             switch (event.getCategory()) {
                 case NETHER -> OreGeneration.NETHER_ORES.forEach(ore -> {
                     FeatureUtils.register(ore.getName(), ore.getConfiguredFeature());
                     PlacementUtils.register(ore.getName(), ore.getFeature());
                     event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore.getFeature());

                 });
                 case THEEND -> OreGeneration.END_ORES.forEach(ore -> {
                     FeatureUtils.register(ore.getName(), ore.getConfiguredFeature());
                     PlacementUtils.register(ore.getName(), ore.getFeature());
                     event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore.getFeature());

                 });
                 case SWAMP -> OreGeneration.SWAMP_ORES.forEach(ore -> {
                     FeatureUtils.register(ore.getName(), ore.getConfiguredFeature());
                     PlacementUtils.register(ore.getName(), ore.getFeature());
                     event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore.getFeature());

                 });
                 default -> OreGeneration.OVERWORLD_ORES.forEach(ore -> {
                     FeatureUtils.register(ore.getName(), ore.getConfiguredFeature());
                     PlacementUtils.register(ore.getName(), ore.getFeature());
                     event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ore.getFeature());
                 });
             }

//        FeatureUtils.register("gel_ore_feature", GEL_ORE);
//        PlacementUtils.register("gel_ore_feature", GEL_ORE_FEATURE);
//
//
//
    }//        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GEL_ORE_FEATURE);

}


