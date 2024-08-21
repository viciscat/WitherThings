package io.github.viciscat.wither;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WitherEventHandling {

    private boolean shouldCancel(ItemStack stack) {
        if (stack.isEmpty()) return false;
        ResourceLocation registryName = stack.getItem().getRegistryName();
        if (registryName == null) return false;
        return WitherConfig.getImmuneItemIDsList().contains(registryName.toString());
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEntityAttacked(LivingAttackEvent event) {
        if (!(event.getEntityLiving() instanceof EntityWither)) return;
        if (!((EntityWither) event.getEntityLiving()).isArmored()) return;
        DamageSource source = event.getSource();
        //System.out.println("Damage type: " + source.getDamageType());
        if (WitherConfig.getImmuneDamageSourcesList().contains(source.getDamageType())) {
            event.setCanceled(true);
            return;
        }
        if (source.getTrueSource() instanceof EntityLivingBase) {
            for (EnumHand value : EnumHand.values()) {
                ItemStack heldItem = ((EntityLivingBase) source.getTrueSource()).getHeldItem(value);
                //System.out.println("Held item: " + heldItem.toString() + heldItem.getItem().getRegistryName());
                if (shouldCancel(heldItem)) {
                    event.setCanceled(true);
                    return;
                }
            }
        }

        ResourceLocation immediate;
        if (source.getImmediateSource() != null && (immediate = EntityList.getKey(source.getImmediateSource())) != null) {
            if (WitherConfig.getImmuneEntitiesList().contains(immediate.toString())) {
                event.setCanceled(true);
            }
        }

    }

    @SubscribeEvent
    public void onHeal(LivingHealEvent event) {
        if (event.getEntityLiving().isPotionActive(DecayPotion.INSTANCE)) {
            event.setAmount(event.getAmount() / 2.f);
        }
    }
}
