package com.isensehostility.enchantment_enhancements;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EnchantmentEnhancements.MODID)
public class EnchantmentEnhancements
{
    public static final String MODID = "enchantment_enhancements";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "Enchantment Enhancements";
    private static final Logger LOGGER = LogManager.getLogger();

    public EnchantmentEnhancements() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation getLocation(String name) {
        return new ResourceLocation(MODID, name);
    }
}