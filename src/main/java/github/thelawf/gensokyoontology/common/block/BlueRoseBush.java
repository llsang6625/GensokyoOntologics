package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TallFlowerBlock;

public class BlueRoseBush extends TallFlowerBlock {
    public BlueRoseBush() {
        super(Properties.from(Blocks.ROSE_BUSH).sound(SoundType.PLANT));
    }
}
