package com.isensehostility.enchantment_enhancements.registries;

import com.isensehostility.enchantment_enhancements.EnchantmentEnhancements;
import com.isensehostility.enchantment_enhancements.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = EnchantmentEnhancements.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnchantmentRegistry {
    public static final Enchantment DROPPER = new Dropper(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.CHEST);
    public static final Enchantment IGNITION = new Ignition(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.CHEST, EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.HEAD);
    public static final Enchantment FADING = new Fading(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND);
    public static final Enchantment BEHEADING = new Beheading(Enchantment.Rarity.COMMON, EquipmentSlotType.MAINHAND);
    public static final Enchantment MIDAS = new Midas(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND);
    public static final Enchantment BERSERKER = new Berserker(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);
    public static final Enchantment REFLECTION = new Reflection(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.CHEST, EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.HEAD);
    public static final Enchantment DETONATION = new Berserker(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND);
    public static final Enchantment RUSTING = new Rusting(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET, EquipmentSlotType.OFFHAND);
    public static final Enchantment PARALYSIS = new Paralysis(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);
    public static final Enchantment MAGIC_PROTECTION = new MagicProtection(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET);
    public static final Enchantment ENDURING = new Enduring(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET, EquipmentSlotType.OFFHAND);
    public static final Enchantment FREEZE = new Freeze(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);
    public static final Enchantment WEIGHING = new Weighing(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.FEET);

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        register(event.getRegistry(), "dropper", DROPPER);
        register(event.getRegistry(), "ignition", IGNITION);
        register(event.getRegistry(), "fading", FADING);
        register(event.getRegistry(), "beheading", BEHEADING);
        register(event.getRegistry(), "midas", MIDAS);
        register(event.getRegistry(), "berserker", BERSERKER);
        register(event.getRegistry(), "reflection", REFLECTION);
        register(event.getRegistry(), "detonation", DETONATION);
        register(event.getRegistry(), "rusting", RUSTING);
        register(event.getRegistry(), "paralysis", PARALYSIS);
        register(event.getRegistry(), "magic_protection", MAGIC_PROTECTION);
        register(event.getRegistry(), "enduring", ENDURING);
        register(event.getRegistry(), "freeze", FREEZE);
        register(event.getRegistry(), "weighing", WEIGHING);
    }

    private static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object) {
        object.setRegistryName(EnchantmentEnhancements.getLocation(name));
        registry.register(object);
    }
}