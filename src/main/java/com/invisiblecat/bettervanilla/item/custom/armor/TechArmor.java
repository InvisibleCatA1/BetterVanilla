package com.invisiblecat.bettervanilla.item.custom.armor;

import com.invisiblecat.bettervanilla.BetterVanilla;
import com.invisiblecat.bettervanilla.utils.ItemUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

public class TechArmor extends ArmorItem {
    public static Item.Properties properties = new Item.Properties().tab(CreativeModeTab.TAB_COMBAT);

    private static ArmorMaterial material = ItemUtils.buildArmorMaterial("tech", 22, new int[]{5,9,7,5} ,12,  SoundEvents.ARMOR_EQUIP_ELYTRA, 0f, 0f,"bettervanilla:tech");

    public static final Item HELMET = new TechArmor(material, EquipmentSlot.HEAD, properties).setRegistryName(BetterVanilla.MODID, "techhead");
    public static final Item CHEST = new TechArmor(material, EquipmentSlot.CHEST, properties).setRegistryName(BetterVanilla.MODID, "techchestplate");
    public static final Item LEGS = new TechArmor(material, EquipmentSlot.LEGS, properties).setRegistryName(BetterVanilla.MODID, "techlegs");
    public static final Item BOOTS = new TechArmor(material, EquipmentSlot.FEET, properties).setRegistryName(BetterVanilla.MODID, "techboots");


    public TechArmor(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(slot == EquipmentSlot.LEGS){
            return "bettervanilla:textures/models/armor/tech_armor_2.png";
        }else{
            return "bettervanilla:textures/models/armor/tech_armor_1.png";
        }
    }
}
