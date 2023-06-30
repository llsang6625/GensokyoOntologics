package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class HeartShotEntity extends AbstractDanmakuEntity implements IRendersAsItem {

    public static final EntityType<HeartShotEntity> HEART_SHOT = EntityType.Builder.<HeartShotEntity>create(
                    HeartShotEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("heart_shot");

    public HeartShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HeartShotEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(HEART_SHOT, throwerIn, world, spellData);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.spellData != null) {
            if (this.spellData.keyTransforms != null) {
                applyTransform(this.spellData.keyTransforms);
            }
        }
    }

    private void applyTransform(HashMap<Integer, TransformFunction> keyTransforms) {
        for (Map.Entry<Integer, TransformFunction> entry : keyTransforms.entrySet()) {
            Integer keyTick = entry.getKey();
            TransformFunction function = entry.getValue();
            if (function.transformOrders == null) return;

        }
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.HEART_SHOT_PINK.get());
    }
}
