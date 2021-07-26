package com.isensehostility.enchantment_enhancements.events;

import com.isensehostility.enchantment_enhancements.EnchantmentEnhancements;
import com.isensehostility.enchantment_enhancements.enchantments.Weighing;
import com.isensehostility.enchantment_enhancements.registries.EnchantmentRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Mod.EventBusSubscriber(modid = EnchantmentEnhancements.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {
    private static int tick;
    private static ItemStack freezeBow;

    @SubscribeEvent
    public static void OnFadingAttack(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        World world = player.getEntityWorld();

        if (!world.isRemote) {
            Entity target = event.getTarget();
            ItemStack heldItem = player.getHeldItem(Hand.MAIN_HAND);
            Map enchant = EnchantmentHelper.getEnchantments(heldItem);

            if (enchant.get(EnchantmentRegistry.FADING) != null) {
                int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.FADING, heldItem);
                if (level == 1) {
                    ((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 40, 0));
                } else if (level == 2) {
                    ((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 80, 0));
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnBerserkerAttack(LivingHurtEvent event) {
        if (event.getSource().getImmediateSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getImmediateSource();
            Entity target = event.getEntity();
            World world = player.getEntityWorld();

            if (!world.isRemote) {
                ItemStack heldItem = player.getHeldItem(Hand.MAIN_HAND);
                Map enchant = EnchantmentHelper.getEnchantments(heldItem);

                if (enchant.get(EnchantmentRegistry.BERSERKER) != null) {
                    int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.BERSERKER, heldItem);
                    if (level == 1) {
                        if (player.getHealth() <= player.getMaxHealth() / 2) {
                            float damage = event.getAmount() * 1.2F;
                            event.setAmount(damage);
                        }
                    } else if (level == 2) {
                        if (player.getHealth() <= player.getMaxHealth() / 2) {
                            float damage = event.getAmount() * 1.5F;
                            event.setAmount(damage);
                        }
                    } else if (level == 3) {
                        if (player.getHealth() <= player.getMaxHealth() / 2) {
                            float damage = event.getAmount() * 1.8F;
                            event.setAmount(damage);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnBeheadingDeath(LivingDeathEvent event) {
        if (event.getSource().getImmediateSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getImmediateSource();
            Entity target = event.getEntity();
            World world = target.getEntityWorld();

            if (!world.isRemote) {
                ItemStack heldItem = player.getHeldItem(Hand.MAIN_HAND);
                Map enchant = EnchantmentHelper.getEnchantments(heldItem);

                if (enchant.get(EnchantmentRegistry.BEHEADING) != null) {
                    int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.BEHEADING, heldItem);
                    Random chance = new Random();
                    if (level == 1) {
                        if (chance.nextInt(100) < 20) {
                            if (target instanceof ZombieEntity) {
                                target.entityDropItem(new ItemStack(Items.ZOMBIE_HEAD));
                            } else if (target instanceof SkeletonEntity) {
                                target.entityDropItem(new ItemStack(Items.SKELETON_SKULL));
                            } else if (target instanceof CreeperEntity) {
                                target.entityDropItem(new ItemStack(Items.CREEPER_HEAD));
                            } else if (target instanceof PlayerEntity) {
                                target.entityDropItem(new ItemStack(Items.PLAYER_HEAD));
                            }
                        }
                        if (chance.nextInt(100) < 3) {
                            if (target instanceof WitherSkeletonEntity) {
                                target.entityDropItem(new ItemStack(Items.WITHER_SKELETON_SKULL));
                            }
                        }
                        if (chance.nextInt(100) < 50) {
                            if (target instanceof EnderDragonEntity) {
                                target.entityDropItem(new ItemStack(Items.DRAGON_HEAD));
                            }
                        }
                    } else if (level == 2) {
                        if (chance.nextInt(100) < 40) {
                            if (target instanceof ZombieEntity) {
                                target.entityDropItem(new ItemStack(Items.ZOMBIE_HEAD));
                            } else if (target instanceof SkeletonEntity) {
                                target.entityDropItem(new ItemStack(Items.SKELETON_SKULL));
                            } else if (target instanceof CreeperEntity) {
                                target.entityDropItem(new ItemStack(Items.CREEPER_HEAD));
                            } else if (target instanceof PlayerEntity) {
                                target.entityDropItem(new ItemStack(Items.PLAYER_HEAD));
                            }
                        }
                        if (chance.nextInt(100) < 6) {
                            if (target instanceof WitherSkeletonEntity) {
                                target.entityDropItem(new ItemStack(Items.WITHER_SKELETON_SKULL));
                            }
                        }
                        if (chance.nextInt(100) < 75) {
                            if (target instanceof EnderDragonEntity) {
                                target.entityDropItem(new ItemStack(Items.DRAGON_HEAD));
                            }
                        }
                    } else if (level == 3) {
                        if (chance.nextInt(100) < 60) {
                            if (target instanceof ZombieEntity) {
                                target.entityDropItem(new ItemStack(Items.ZOMBIE_HEAD));
                            } else if (target instanceof SkeletonEntity) {
                                target.entityDropItem(new ItemStack(Items.SKELETON_SKULL));
                            } else if (target instanceof CreeperEntity) {
                                target.entityDropItem(new ItemStack(Items.CREEPER_HEAD));
                            } else if (target instanceof PlayerEntity) {
                                target.entityDropItem(new ItemStack(Items.PLAYER_HEAD));
                            }
                        }
                        if (chance.nextInt(100) < 9) {
                            if (target instanceof WitherSkeletonEntity) {
                                target.entityDropItem(new ItemStack(Items.WITHER_SKELETON_SKULL));
                            }
                        }
                        if (target instanceof EnderDragonEntity) {
                            target.entityDropItem(new ItemStack(Items.DRAGON_HEAD));
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onReflectionHurt(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            Entity target = event.getSource().getImmediateSource();
            World world = player.getEntityWorld();
            float amount = event.getAmount();

            if (!world.isRemote) {
                Iterable<ItemStack> enchantedItem = player.getEquipmentAndArmor();

                for (ItemStack stack : enchantedItem) {
                    Map enchant = EnchantmentHelper.getEnchantments(stack);
                    int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.REFLECTION, stack);

                    if (enchant.get(EnchantmentRegistry.REFLECTION) != null) {
                        if (level == 1 && target != null) {
                            target.attackEntityFrom(DamageSource.causeThornsDamage(player), 0.25F * amount);
                            player.heal(0.25F * amount);
                        } else if (level == 2 && target != null) {
                            target.attackEntityFrom(DamageSource.causeThornsDamage(player), 0.5F * amount);
                            player.heal(0.5F * amount);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnItemExpiration(ItemExpireEvent event) {
        ItemEntity itemEntity = event.getEntityItem();
        Map enchant = EnchantmentHelper.getEnchantments(itemEntity.getItem());

        if (enchant.get(EnchantmentRegistry.DETONATION) != null) {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.DETONATION, itemEntity.getItem());

            double x = itemEntity.getPosX();
            double y = itemEntity.getPosY();
            double z = itemEntity.getPosZ();

            if (level == 1) {
                World world = itemEntity.getEntityWorld();
                world.createExplosion(itemEntity, x, y, z, 2, Explosion.Mode.BREAK);
            } else if (level == 2) {
                World world = itemEntity.getEntityWorld();
                world.createExplosion(itemEntity, x, y, z, 3, Explosion.Mode.BREAK);
            } else if (level == 3) {
                World world = itemEntity.getEntityWorld();
                world.createExplosion(itemEntity, x, y, z, 4, Explosion.Mode.BREAK);
            }
        }
    }

    @SubscribeEvent
    public static void OnTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (event.phase == TickEvent.Phase.START) {
            if (tick == 200) {

                Iterable<ItemStack> enchantedItem = player.getEquipmentAndArmor();

                for (ItemStack stack : enchantedItem) {
                    Map enchant = EnchantmentHelper.getEnchantments(stack);
                    int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.RUSTING, stack);

                    if (enchant.get(EnchantmentRegistry.RUSTING) != null) {
                        stack.damageItem(1, player, playerEntity -> {
                        });
                    }
                }
                tick = 0;
            } else {
                tick++;
            }
        }
    }

    @SubscribeEvent
    public static void onMagicProtectionHurt(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            Entity target = event.getSource().getImmediateSource();
            World world = player.getEntityWorld();
            float amount = event.getAmount();

            if (!world.isRemote) {
                Iterable<ItemStack> enchantedItem = player.getArmorInventoryList();

                for (ItemStack stack : enchantedItem) {
                    Map enchant = EnchantmentHelper.getEnchantments(stack);
                    int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.MAGIC_PROTECTION, stack);

                    if (enchant.get(EnchantmentRegistry.MAGIC_PROTECTION) != null) {
                        if (event.getSource().isMagicDamage()) {
                            if (level == 1) {
                                event.setAmount(0.9F * amount);
                            }
                            else if (level == 2) {
                                event.setAmount(0.8F * amount);
                            }
                            else if (level == 3) {
                                event.setAmount(0.7F * amount);
                            }
                            else if (level == 4) {
                                event.setAmount(0.6F * amount);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemThrow(ItemTossEvent event) {
        ItemEntity itemEntity = event.getEntityItem();
        Map enchant = EnchantmentHelper.getEnchantments(itemEntity.getItem());

        if (enchant.get(EnchantmentRegistry.ENDURING) != null) {
            itemEntity.setNoDespawn();
        }
    }

    @SubscribeEvent
    public static void onArrowShot(ArrowLooseEvent event) {
        ItemStack bow = event.getBow();
        Map enchant = EnchantmentHelper.getEnchantments(bow);
        if (enchant.get(EnchantmentRegistry.FREEZE) != null) {
            freezeBow = bow;
        }
    }

    @SubscribeEvent
    public static void entityJoinWorld(EntityJoinWorldEvent event) {
        Entity joinedEntity = event.getEntity();
        if (joinedEntity instanceof ArrowEntity) {
            if (freezeBow != null) {
                joinedEntity.addTag("freezeArrow");
                freezeBow = null;
            }
        }
    }

    @SubscribeEvent
    public static void onFreezeHit(LivingHurtEvent event) {
        Entity arrow = event.getSource().getImmediateSource();
        if (arrow instanceof ArrowEntity) {
            if (arrow.getTags().toString().equals("[freezeArrow]")) {
                LivingEntity target = (LivingEntity) event.getEntity();
                target.addPotionEffect(new EffectInstance(Effects.SLOWNESS,100,30));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            for (ItemStack stack : event.player.getEquipmentAndArmor()) {
                if (stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getEquipmentSlot() == EquipmentSlotType.FEET) {
                    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
                    if (enchantments.get(EnchantmentRegistry.WEIGHING) != null) {
                        event.player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 5, 0));
                    }
                }
            }
        }
    }
}
