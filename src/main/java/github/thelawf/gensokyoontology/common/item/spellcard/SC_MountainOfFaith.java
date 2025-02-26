package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.spellcard.MountainOfFaithEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.WaveAndParticleEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class SC_MountainOfFaith extends SpellCardItem{
    public SC_MountainOfFaith(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) worldIn;

            // EntityType<MountainOfFaithEntity> entityType = EntityRegistry.MOUNTAIN_OF_FAITH_ENTITY.get();
            // entityType.spawn(serverWorld, playerIn.getHeldItemMainhand(), playerIn, playerIn.getPosition(),
            //         SpawnReason.MOB_SUMMONED,true, true);

           MountainOfFaithEntity spellCard = new MountainOfFaithEntity(worldIn, playerIn);
           worldIn.addEntity(spellCard);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                ".spellcard.mountain_of_faith.intro"));
    }

    @Override
    public void start(PlayerEntity player) {

    }

    @Override
    public Supplier<TransformFunction> update(PlayerEntity player, int tick) {
        return null;
    }

    @Override
    public void end(PlayerEntity player) {

    }
}
