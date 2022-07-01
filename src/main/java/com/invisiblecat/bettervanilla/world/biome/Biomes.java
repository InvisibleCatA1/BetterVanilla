package com.invisiblecat.bettervanilla.world.biome;//package com.invisiblecat.world.biome;
//
//import net.minecraft.data.worldgen.BiomeDefaultFeatures;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.BiomeGenerationSettings;
//import net.minecraft.world.level.biome.MobSpawnSettings;
//import net.minecraft.world.level.levelgen.GenerationStep;
//public class Biomes {
//
//    public static Biome drylands(MobSpawnSettings.Builder mobspawnSettingsBuilder, BiomeGenerationSettings.Builder biomeGenerationSettingsBuilder) {
//        // Mob spawns
//        mobspawnSettingsBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 500, 10, 20));
//        mobspawnSettingsBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 400, 4, 9));
//
//        // decorations
//        BiomeDefaultFeatures.addBadlandGrass(biomeGenerationSettingsBuilder);
//        BiomeDefaultFeatures.addDefaultMushrooms(biomeGenerationSettingsBuilder);
//        BiomeDefaultFeatures.addMossyStoneBlock(biomeGenerationSettingsBuilder);
//        BiomeDefaultFeatures.addDefaultSprings(biomeGenerationSettingsBuilder);
//        BiomeDefaultFeatures.addMountainTrees(biomeGenerationSettingsBuilder);
//
//        return WorldUtils.baseBiome(Biome.BiomeCategory.FOREST,
//                mobspawnSettingsBuilder, biomeGenerationSettingsBuilder,
//                0.8f, 1.0f, 0.7f, 0.6f, 4159204);
//
//    }
//}
