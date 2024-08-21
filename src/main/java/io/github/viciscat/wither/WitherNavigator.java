package io.github.viciscat.wither;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WitherNavigator extends PathNavigateGround {

    private final EntityWither wither;

    public WitherNavigator(EntityWither entityLiving, World world) {
        super(entityLiving, world);
        wither = entityLiving;
    }

    @Override
    protected PathFinder getPathFinder() {
        this.nodeProcessor = new WalkNodeProcessor(){
            @Override
            public PathNodeType getPathNodeType(IBlockAccess blockaccessIn, int x, int y, int z, EntityLiving entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
                if (!WitherNavigator.this.wither.isArmored()) return super.getPathNodeType(blockaccessIn, x, y, z, entitylivingIn, xSize, ySize, zSize, canBreakDoorsIn, canEnterDoorsIn);
                return PathNodeType.WALKABLE;
            }
        };
        nodeProcessor.setCanEnterDoors(true);
        return new PathFinder(nodeProcessor);
    }
}
