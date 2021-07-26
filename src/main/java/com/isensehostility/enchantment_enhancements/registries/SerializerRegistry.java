package com.isensehostility.enchantment_enhancements.registries;

import com.isensehostility.enchantment_enhancements.EnchantmentEnhancements;
import com.isensehostility.enchantment_enhancements.loot_modifiers.MidasEnchantmentModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = EnchantmentEnhancements.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SerializerRegistry {

	@SubscribeEvent
	public static void registerModifierSerializers(final RegistryEvent.Register< GlobalLootModifierSerializer<?>> event) {
		IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();
		register(registry, new MidasEnchantmentModifier.Serializer(), "midas_modifier");
	}

	private static void register(IForgeRegistry<GlobalLootModifierSerializer<?>> registry, GlobalLootModifierSerializer<?> serializer, String name) {
		registry.register(serializer.setRegistryName(EnchantmentEnhancements.getLocation(name)));
	}
}
