package com.isensehostility.enchantment_enhancements.loot_modifiers;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MidasEnchantmentModifier extends LootModifier {
    private final Item curseItem;

    public MidasEnchantmentModifier(ILootCondition[] conditionsIn, Item nugget) {
        super(conditionsIn);
        this.curseItem = nugget;
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        List<ItemStack> stack = new ArrayList<ItemStack>();
        stack.add(new ItemStack(curseItem));
        return stack;
    }

    public static class Serializer extends GlobalLootModifierSerializer<MidasEnchantmentModifier> {
        @Override
        public MidasEnchantmentModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            Item gold_nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getString(object, "curseItem")));
            return new MidasEnchantmentModifier(conditionsIn, gold_nugget);
        }

        @Override
        public JsonObject write(MidasEnchantmentModifier instance) {
            return null;
        }
    }
}
