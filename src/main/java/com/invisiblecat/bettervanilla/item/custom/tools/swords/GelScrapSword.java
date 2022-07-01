package com.invisiblecat.bettervanilla.item.custom.tools.swords;

import com.invisiblecat.bettervanilla.BetterVanilla;
import com.invisiblecat.bettervanilla.item.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class GelScrapSword extends SwordItem {
    private static Tier tier = new ForgeTier(4, 1600, 1f, 0.5F, 10, null, () -> {return Ingredient.of(ItemRegistry.GEL_BAR);});

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_COMBAT);

    public static Item INSTANCE = new GelSword(tier, 4, 0.5f, properties)
            .setRegistryName(BetterVanilla.MODID, "gelscrapsword");

    public GelScrapSword(Tier tier, int dmg, float speed, Properties properties) {
        super(tier, dmg, speed, properties);
    }

}
