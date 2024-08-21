package io.github.viciscat.wither;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = WitherThingsMod.MOD_ID, useMetadata=true)
public class WitherThingsMod {

    public static final String MOD_ID = "wither-things";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new WitherEventHandling());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ForgeRegistries.POTIONS.register(DecayPotion.INSTANCE);
    }
}
