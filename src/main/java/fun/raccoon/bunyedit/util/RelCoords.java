package fun.raccoon.bunyedit.util;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.chunk.ChunkPosition;

public class RelCoords {
    public static ChunkPosition playerPos(EntityPlayer player, boolean fromHead) {
        return new ChunkPosition( 
            (int)Math.floor(player.x),
            ((int)Math.floor(player.y)) - (fromHead ? 2 : 1),
            (int)Math.floor(player.z));
    }

    public static ChunkPosition from(ChunkPosition origin, LookDirection lookDir, String triple) {
        int[] origin_ = {origin.x, origin.y, origin.z};
        String[] triple_ = triple.split(",");

        int[] res = new int[3];

        for (int i = 0; i < triple_.length; ++i) {
            if (!triple_[i].startsWith("^"))
                return from(origin, triple);
            
            int axis = lookDir.getGlobalAxis(i);
            boolean inv = lookDir.getGlobalInv(i);

            res[axis] = origin_[axis];

            if (triple_[i].length() > 1) {
                try {
                    res[axis] += (inv ? -1 : 1) * Integer.parseInt(triple_[i].substring(1));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }

        return PosMath.fromArray(res);
    }
    
    public static ChunkPosition from(ChunkPosition origin, String triple) {
        int[] origin_ = {origin.x, origin.y, origin.z};
        String[] triple_ = triple.split(",");

        int[] res = {0, 0, 0};

        for (int i = 0; i < triple_.length; ++i) {
            if (triple_[i].startsWith("~")) {
                res[i] = origin_[i];
                if (triple_[i].length() > 1) {
                    try {
                        res[i] += Integer.parseInt(triple_[i].substring(1));
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            } else {
                try {
                    res[i] += Integer.parseInt(triple_[i]);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return PosMath.fromArray(res);
    }


    public static ChunkPosition from(EntityPlayer player, String triple) {
        return from(playerPos(player, false), new LookDirection(player), triple);
    }
}
