package github.thelawf.gensokyoontology.client.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.tileentity.SpaceFissureTileEntity;
import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class SpaceFissureRenderer extends TileEntityRenderer<SpaceFissureTileEntity> {
    public static final ResourceLocation SPACE_FISSURE_TEX = new ResourceLocation(
            GensokyoOntology.MODID,"block/space_fissure_block");

    public SpaceFissureRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    private void add(IVertexBuilder builderIn, MatrixStack stack, float x, float y, float z,
                     float u, float v){
        builderIn.pos(stack.getLast().getMatrix(), x, y, z)
                .tex(u,v)
                .lightmap(0,240)
                .normal(1,0,0)
                .endVertex();
    }

    @Override
    public void render(SpaceFissureTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(
                AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(SPACE_FISSURE_TEX);
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getTranslucent());

        matrixStackIn.push();
        add(builder,matrixStackIn,0, 0, 0.5f, sprite.getMinU(), sprite.getMinV());
        add(builder,matrixStackIn,1, 0, 0.5f, sprite.getMaxU(), sprite.getMinV());
        add(builder,matrixStackIn,1, 1, 0.5f, sprite.getMaxU(), sprite.getMaxV());
        add(builder,matrixStackIn,0, 1, 0.5f, sprite.getMinU(), sprite.getMaxV());

        add(builder,matrixStackIn,0, 1, 0.5f, sprite.getMinU(), sprite.getMaxV());
        add(builder,matrixStackIn,1, 1, 0.5f, sprite.getMaxU(), sprite.getMaxV());
        add(builder,matrixStackIn,1, 0, 0.5f, sprite.getMaxU(), sprite.getMinV());
        add(builder,matrixStackIn,0, 0, 0.5f, sprite.getMinU(), sprite.getMinV());
        matrixStackIn.pop();
    }

    public static void register() {
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeRegistry.SPACE_FISSURE_TILE_ENTITY.get(),
                SpaceFissureRenderer::new);
    }

}
