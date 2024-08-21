package io.github.viciscat.wither.mixin;

import io.github.viciscat.wither.DecayPotion;
import io.github.viciscat.wither.WitherNavigator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityWither.class)
public abstract class EntityWitherMixin extends EntityMob {

    @Shadow public abstract int getWatchedTargetId(int head);

    @Shadow public abstract boolean isArmored();

    public EntityWitherMixin(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        int watchedTargetId = getWatchedTargetId(0);
        Entity entity = null;
        boolean ghosting = watchedTargetId > 0 && ( entity = world.getEntityByID(watchedTargetId)) != null && isArmored();
        EntityLivingBase living;
        if (entity instanceof EntityLivingBase && entity.getDistanceSq(this) < 30*30 && (living = (EntityLivingBase) entity).isPotionActive(MobEffects.WITHER)) {
            PotionEffect activePotionEffect = living.getActivePotionEffect(DecayPotion.INSTANCE);
            if (activePotionEffect == null || activePotionEffect.getDuration() <= 20*8 )
                living.addPotionEffect(new PotionEffect(DecayPotion.INSTANCE, 20*10));
        }
        if (ghosting) {
            noClip = true;

        }
        super.onUpdate();
        if (ghosting) noClip = false;
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        return new WitherNavigator((EntityWither) (Object) this, worldIn);
    }
}
