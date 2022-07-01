package com.invisiblecat.bettervanilla.world.ore;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class Ore {
    private String name;
    private PlacedFeature feature;
    private ConfiguredFeature<?, ?> configuredFeature;

    public Ore(String name, PlacedFeature feature, ConfiguredFeature<?, ?> configuredFeature) {
        this.name = name;
        this.feature = feature;
        this.configuredFeature = configuredFeature;
    }

    public String getName() {
        return name;
    }

    public PlacedFeature getFeature() {
        return feature;
    }

    public ConfiguredFeature<?, ?> getConfiguredFeature() {
        return configuredFeature;
    }
}
