package com.isensehostility.enchantment_enhancements.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Dropper extends net.minecraft.enchantment.Enchantment {

    public Dropper(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_CHEST, slots);
    }

    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {
        if (level == 1) {
            if (attacker.getEntity() instanceof ZombieEntity) {
                attacker.entityDropItem(new ItemStack(Items.ROTTEN_FLESH));
            }
            if (attacker.getEntity() instanceof SpiderEntity) {
                attacker.entityDropItem(new ItemStack(Items.STRING));
            }
        }
        else if (level == 2) {
            if (attacker.getEntity() instanceof ZombieEntity) {
                attacker.entityDropItem(new ItemStack(Items.ROTTEN_FLESH));
            }
            if (attacker.getEntity() instanceof ZombifiedPiglinEntity) {
                attacker.entityDropItem(new ItemStack(Items.GOLD_NUGGET));
            }
            if (attacker.getEntity() instanceof SpiderEntity) {
                attacker.entityDropItem(new ItemStack(Items.STRING));
                attacker.entityDropItem(new ItemStack(Items.SPIDER_EYE));
            }
        }
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 10 + 20 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 50;
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench)
                && ench != Enchantments.THORNS;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getEquipmentSlot() == EquipmentSlotType.CHEST;
    }
}