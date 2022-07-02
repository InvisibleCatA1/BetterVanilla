package com.invisiblecat.bettervanilla.item;

import com.invisiblecat.bettervanilla.item.custom.armor.TechArmor;
import com.invisiblecat.bettervanilla.item.custom.misc.RodOfDiscord;
import com.invisiblecat.bettervanilla.item.custom.tools.swords.GelScrapSword;
import com.invisiblecat.bettervanilla.item.custom.tools.swords.GelSword;
import com.invisiblecat.bettervanilla.utils.ItemUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ItemRegistry {

    //BASIC ITEMS
    public static final Item SCRAP = ItemUtils.buildBasicItem("tech", CreativeModeTab.TAB_MISC);

    // Ingots
    public static final Item GEL_BAR = ItemUtils.buildBasicItem("gelbar", CreativeModeTab.TAB_MATERIALS);
    public static final Item EATHER_INGOT = ItemUtils.buildBasicItem("eather_ingot", CreativeModeTab.TAB_MATERIALS);

    // Misc
    public static final Item RAW_EATHER = ItemUtils.buildBasicItem("raw_eather", CreativeModeTab.TAB_MATERIALS);

    // Oes
    public static final Item GEL_ORE = ItemUtils.buildBasicItem("gelore", CreativeModeTab.TAB_MATERIALS);
    public static final Item EATHER_SHARD = ItemUtils.buildBasicItem("eather_shard", CreativeModeTab.TAB_MATERIALS);

    //Foods
    public static final FoodProperties arm_properties = new FoodProperties.Builder().nutrition(6).saturationMod(1.7f).effect(new MobEffectInstance(MobEffects.CONFUSION, 500, 1), 1f).alwaysEat().build();
    public static final Item ARM = ItemUtils.buildFoodItem("arm", arm_properties);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();
        //BASIC ITEMS
        reg.register(SCRAP);

        // ORES
        reg.register(GEL_ORE);
        reg.register(EATHER_SHARD);

        // INGOTS
        reg.register(GEL_BAR);
        reg.register(EATHER_INGOT);

        // MISC
        reg.register(RAW_EATHER);

        // TOOLS
        reg.register(RodOfDiscord.INSTANCE);
        reg.register(GelScrapSword.INSTANCE);
        reg.register(GelSword.INSTANCE);

        // FOOD
        reg.register(ARM);

        // ARMOR
        reg.register(TechArmor.HELMET);
        reg.register(TechArmor.CHEST);
        reg.register(TechArmor.LEGS);
        reg.register(TechArmor.BOOTS);

        //COMBAT

        //PROJECTILES

    }
}
