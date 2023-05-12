package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.SpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.Muzzle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SC_WaveAndParticle extends SpellCardItem {

    public static final int LIFE_SPAN = 200;

    public SC_WaveAndParticle(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    public void start(PlayerEntity player) {
        World world = player.world;

        // 获取玩家位置
        Vector3d playerPos = player.getPositionVec();
        Vector3d lookVec = player.getLookVec();

        List<Muzzle<?>> muzzles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            muzzles.add(new Muzzle<>(new TransformFunction()
                    .setPlayer(player).setLifeSpan(100)
                    .setInitLocation(playerPos)
                    .setShootVector(lookVec)
                    .setLifeSpan(200)
                    .setIncrement(Math.PI / 180),
                    new DanmakuShotEntity(player, world, DanmakuType.LARGE_SHOT)
            ));
        }

        SpellCardEntity spellEntity = new SpellCardEntity(
                SpellCardEntity.SPELL_CARD_ENTITY, world, muzzles);
        world.addEntity(spellEntity);



    }


    @Override
    public Supplier<TransformFunction> update(PlayerEntity player, int tick) {
        return () -> new TransformFunction().setPlayer(player);
    }

    @Override
    public void end(PlayerEntity player) {

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        start(playerIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
