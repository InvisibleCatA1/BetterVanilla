package com.invisiblecat.bettervanilla.utils;

import com.invisiblecat.bettervanilla.BetterVanilla;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.Tag;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;


public class ItemUtils {
    // yoinked from https://github.com/Charlotteec/ForgeResourcePackage1.18.1/blob/master/src/main/java/com/idtech/item/ItemUtils.java

    public static Item buildBasicItem(String name, CreativeModeTab group){
        return new Item(new Item.Properties().tab(group)).setRegistryName(BetterVanilla.MODID, name);
    }

    public static Item buildFoodItem(String name, FoodProperties food){
        return new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(food)).setRegistryName(BetterVanilla.MODID, name);
    }


    public static ArmorMaterial buildArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent eqiupSoundIn,
                                                    float toughnessIn, float knockbackResistanceIn, String repairIngredientName){
        Supplier<Ingredient> ingredientSupplier = ()-> Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation(repairIngredientName)));
        return buildArmorMaterial(nameIn, maxDamageFactorIn, damageReductionAmountArrayIn, enchantabilityIn, eqiupSoundIn, toughnessIn, knockbackResistanceIn, ingredientSupplier);

    }


    public static ArmorMaterial buildArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent eqiupSoundIn,
                                                   float toughnessIn, float knockbackResistanceIn, Tag<Item> itemTag){
        Supplier<Ingredient> ingredientSupplier = () -> Ingredient.of(itemTag);
        return buildArmorMaterial(nameIn, maxDamageFactorIn, damageReductionAmountArrayIn, enchantabilityIn, eqiupSoundIn, toughnessIn, knockbackResistanceIn, ingredientSupplier);

    }

    private static ArmorMaterial buildArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent eqiupSoundIn,
                                                     float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairIngredientIn) {

        final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

        return new ArmorMaterial() {

            final String name = nameIn;
            final int maxDamageFactor = maxDamageFactorIn;
            final int[] damageReductionAmountArray = damageReductionAmountArrayIn;
            final int enchantability = enchantabilityIn;
            final SoundEvent soundEvent = eqiupSoundIn;
            final float toughness = toughnessIn;
            final float knockbackResistance = knockbackResistanceIn;
            final LazyLoadedValue<Ingredient>  repairMaterial = new LazyLoadedValue<>(repairIngredientIn);
            ;


        public int getDurabilityForSlot(EquipmentSlot slotIn){
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }
        public int getDefenseForSlot(EquipmentSlot slotIn) {
                return this.damageReductionAmountArray[slotIn.getIndex()];
        }

        public int getEnchantmentValue () {
            return this.enchantability;
        }

        public SoundEvent getEquipSound() {
            return this.soundEvent;
        }
        @Override
        public Ingredient getRepairIngredient () {
            return this.repairMaterial.get();
        }

        @Override
        public String getName () {
            return this.name;
        }
        @Override
        public float getToughness () {
            return this.toughness;
        }
        @Override
        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    };

    }


}
