package io.github.viciscat.wither;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class DecayPotion extends Potion {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WitherThingsMod.MOD_ID, "textures/gui/decay.png");

    public static final DecayPotion INSTANCE = new DecayPotion();

    protected DecayPotion() {
        super(true, 0);
        setRegistryName(WitherThingsMod.MOD_ID, "decay");
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 40 == 0;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.attackEntityFrom(DamageSource.OUT_OF_WORLD, 2);
    }

    @Override
    public String getName() {
        return "Decay";
    }

    @Override
    public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha) {
        super.renderHUDEffect(effect, gui, x, y, z, alpha);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        gui.drawTexturedModalRect(x + 3, y + 3, 0, 0, 16, 16);
    }

    @Override
    public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z) {
        super.renderInventoryEffect(effect, gui, x, y, z);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        gui.drawTexturedModalRect(x + 6, y + 7, 0, 0, 16, 16);
    }
}
