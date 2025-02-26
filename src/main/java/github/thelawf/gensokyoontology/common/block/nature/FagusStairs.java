package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItemUseContext;
import org.jetbrains.annotations.NotNull;

public class FagusStairs extends StairsBlock {
    public FagusStairs() {
        super(Blocks.OAK_STAIRS::getDefaultState, Properties.from(Blocks.OAK_STAIRS));
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }
}
