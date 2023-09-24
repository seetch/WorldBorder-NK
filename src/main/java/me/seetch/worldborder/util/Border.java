package me.seetch.worldborder.util;

import cn.nukkit.math.Vector3;

public record Border(int minX, int minZ, int maxX, int maxZ) {

    public boolean isVectorInside(Vector3 vector) {
        return vector.getFloorX() >= minX
                && vector.getFloorX() <= maxX
                && vector.getFloorZ() >= minZ
                && vector.getFloorZ() <= maxZ;
    }
}
