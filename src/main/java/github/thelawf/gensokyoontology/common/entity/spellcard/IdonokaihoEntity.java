package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.*;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**恋恋的符卡：本我的解放
 *
 */
public class IdonokaihoEntity extends SpellCardEntity {

    protected final int lifespan = 600;

    public static final EntityType<IdonokaihoEntity> IDONOKAIHO_ENTITY =
            EntityType.Builder.<IdonokaihoEntity>create(IdonokaihoEntity::new,
                            EntityClassification.MISC).size(1F,1F).trackingRange(4)
                    .updateInterval(2).build("ido_no_kaiho");

    public IdonokaihoEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, PlayerEntity player) {
        super(IDONOKAIHO_ENTITY, worldIn, player);
    }

    public IdonokaihoEntity(EntityType<IdonokaihoEntity> entityTypeIn, World worldIn) {
        super(IDONOKAIHO_ENTITY, worldIn);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_IDO_NO_KAIHO.get());
    }

    @Override
    public void tick() {
        super.tick();

        PlayerEntity player = this.hasOwner() ? this.world.getPlayerByUuid(
                this.dataManager.get(DATA_OWNER_UUID).get()) : null;

        // HashMap<Integer, TransformFunction> functions = new HashMap<>();
        // ArrayList<VectorOperations> orders = new ArrayList<>();
//
        // orders.add(VectorOperations.ROTATE_YAW);
        // orders.add(VectorOperations.VECTOR_SCALE);
        // orders.add(VectorOperations.VECTOR_ADD);
//
        // functions.put(1, TransformFunction.Builder.create()
        //         .setYaw((float) Math.PI / 2)
        //         .setScaling(0.8F)
        //         .setTransformOrders(orders));
//
        // SpellData spellData = new SpellData(functions);

        for (int i = 0; i < 6; i++) {
            if (ticksExisted % 3 == 0) {
                HeartShotEntity heartShot = new HeartShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.HEART_SHOT, DanmakuColor.PINK);
                Vector3d muzzle = new Vector3d(Vector3f.XP).rotateYaw((float) (Math.PI / 3 * i));
                Vector3d nextMotion = muzzle.rotateYaw((float) (Math.PI / 120) * ticksExisted);

                this.setMotion(nextMotion);
                initDanmaku(heartShot, muzzle);
                heartShot.shoot(nextMotion.getX(), nextMotion.y, nextMotion.getZ(), 0.4F, 0);
                world.addEntity(heartShot);

            }

        }

        for (int i = 0; i < 6; i++) {
            if (ticksExisted % 3 == 0) {

                HeartShotEntity heartShot = new HeartShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.HEART_SHOT, DanmakuColor.PINK);
                Vector3d muzzle = new Vector3d(Vector3f.XP).rotateYaw((float) (Math.PI / 3 * i));
                Vector3d nextMotion = muzzle.rotateYaw((float) (-Math.PI / 120) * ticksExisted);

                this.setMotion(nextMotion);
                initDanmaku(heartShot, muzzle);
                heartShot.shoot(nextMotion.getX(), nextMotion.y, nextMotion.getZ(), 0.4F, 0);
                world.addEntity(heartShot);

            }
        }
    }
}

