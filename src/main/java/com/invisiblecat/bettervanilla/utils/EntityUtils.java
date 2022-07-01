package com.invisiblecat.bettervanilla.utils;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class EntityUtils {

    public static Item buildEntitySpawnEgg(EntityType type, int primaryColor, int secondaryColor){
        return new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(type.getRegistryName() + "_egg");
    }


}
