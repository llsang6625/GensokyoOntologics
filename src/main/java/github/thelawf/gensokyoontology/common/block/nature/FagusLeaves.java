package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;

public class FagusLeaves extends LeavesBlock {
    public FagusLeaves() {
        super(Properties.from(Blocks.OAK_LEAVES).tickRandomly().sound(SoundType.WOOD));
    }
}
