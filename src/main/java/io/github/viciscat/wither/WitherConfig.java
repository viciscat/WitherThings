package io.github.viciscat.wither;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.config.Config;

import java.util.List;

@Config(modid = WitherThingsMod.MOD_ID)
public class WitherConfig {
    @Config.Ignore
    private static List<String> immuneItemIDsList = null;
    @Config.Ignore
    private static List<String> immuneDamageSourcesList = null;

    @Config.Ignore
    private static List<String> immuneEntitiesList = null;

    public static String[] immuneItemIDs = {"minecraft:stick"};

    public static String[] immuneDamageSources = {"arrow"};

    public static String[] immuneEntities = {"minecraft:arrow"};

    public static List<String> getImmuneDamageSourcesList() {
        if (immuneDamageSourcesList == null) immuneDamageSourcesList = ImmutableList.copyOf(immuneDamageSources);
        return immuneDamageSourcesList;
    }

    public static List<String> getImmuneItemIDsList() {
        if (immuneItemIDsList == null) immuneItemIDsList = ImmutableList.copyOf(immuneItemIDs);
        return immuneItemIDsList;
    }

    public static List<String> getImmuneEntitiesList() {
        if (immuneEntitiesList == null) immuneEntitiesList = ImmutableList.copyOf(immuneEntities);
        return immuneEntitiesList;
    }
}
