package github.thelawf.gensokyoontology.common.item.danmaku;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DanmakuItem extends Item {
    public DanmakuItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getHeldItem(Hand.MAIN_HAND).getItem() instanceof DanmakuItem) {
            ItemStack stack = playerIn.getHeldItem(Hand.MAIN_HAND);
            stack.shrink(1);
            return ActionResult.resultConsume(stack);
        }
        else if (playerIn.getHeldItem(Hand.OFF_HAND).getItem() instanceof DanmakuItem) {
            ItemStack stack = playerIn.getHeldItem(Hand.OFF_HAND);
            stack.shrink(1);
            return ActionResult.resultConsume(stack);
        }
        else {
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        }
    }
}
