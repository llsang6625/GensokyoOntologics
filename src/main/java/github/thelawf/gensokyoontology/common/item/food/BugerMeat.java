package github.thelawf.gensokyoontology.common.item.food;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BugerMeat extends Item {
    private static final Food food = new Food.Builder()
            .saturation(6)
            .hunger(8)
            .meat()
            .setAlwaysEdible()
            .build();

    public BugerMeat() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).food(food));
    }
}
