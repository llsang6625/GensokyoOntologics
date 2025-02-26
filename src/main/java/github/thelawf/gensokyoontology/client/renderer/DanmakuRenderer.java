package github.thelawf.gensokyoontology.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Vector3f;

import java.util.HashMap;

@Deprecated
public class DanmakuRenderer extends SpriteRenderer<DanmakuShotEntity> {
    public static final String PATH = "textures/entity";
    private static final ResourceLocation DANMAKU_RED = new ResourceLocation(GensokyoOntology.MODID, PATH);
    // private static final RenderType RENDER_TYPE = RenderType.getItemEntityTranslucentCull(DANMAKU_TEX);

    private final ItemRenderer itemRenderer;

    public DanmakuRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
        this.itemRenderer = itemRendererIn;
    }

    public void render(DanmakuShotEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

        float scale = DanmakuType.LARGE_SHOT.size;
        matrixStackIn.push();
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        matrixStackIn.scale(scale, scale, scale);

        MatrixStack.Entry matrixStackLast = matrixStackIn.getLast();
        Matrix3f normal = matrixStackLast.getNormal();
        this.itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GROUND,
                packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);

        matrixStackIn.pop();
    }

}
