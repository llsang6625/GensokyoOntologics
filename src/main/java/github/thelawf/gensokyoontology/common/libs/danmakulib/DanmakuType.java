package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

import javax.annotation.Nullable;

public enum DanmakuType {

    DANMAKU_SHOT("danmaku_shot", 0.5f, 0.1f),
    // 心弹
    HEART_SHOT("heart_shot", 1.5f, 2.5f),

    // 蝶弹
    BUTTERFLY_SHOT("butterfly_shot",1.5f,2.5f),

    // 小玉
    SMALL_SHOT("small_shot", 0.5f,0.5f),

    // 中玉
    ORB_SHOT("orb_shot",1.5f,1.5f),

    // 环玉
    CIRCLE_SHOT("circle_shot",1.0f, 1.0f),

    // 大玉
    LARGE_SHOT("large_shot",2.5f, 2.5f),

    // 小星弹
    STAR_SHOT_SMALL("small_star_shot", 0.5f, 0.5f),

    // 星弹
    STAR_SHOT_LARGE("large_star_shot", 1.5f, 2.0f),

    // 光弹
    LIGHT_SHOT("light_shot",1.0f,1.0f),

    // 大光弹
    BIG_LIGHT_SHOT("big_light_shot",1.5f,2.5f),

    // 米弹
    RICE_SHOT("rice_shot", 0.5f,0.5f),

    // PEARL_SHOT("pearl_shot",1.0f,1.2f,(Effect) null),

    // 鳞弹
    SCALE_SHOT("scale_shot", 0.5f, 0.5f),

    // 苦无弹
    KUNAI_SHOT("kunai_shot", 0.5f, 0.5f),

    // 晶弹
    CRYSTAL_SHOT("crystal_shot", 0.5f,0.5f, Effects.SLOWNESS),

    // 矢弹
    ARROW_SHOT("arrow_shot", 1.0f, 1.0f),

    // 刀弹
    KNIFE_SHOT("knife_shot", 1.0f, 1.0f),

    // 札弹
    TALISMAN_SHOT("talisman_shot", 1.0f,1.5f),

    // 椭圆弹
    OVAL_SHOT("oval_shot", 1.0f,1.5f),

    // 阴阳玉弹幕
    INYO_JADE_SHOT("inyo_jade", 2.0f, 2.5f),

    FAKE_LUNAR("fake_lunar", 5.0f, 10.0f);


    public final String name;
    public final float damage;
    public final float size;
    public final Effect[] effect;
    // charm, amulet, medallion, talisman, vignettes
    DanmakuType(String name, float size, float damage, @Nullable Effect... effect){
        this.name = name;
        this.damage = damage;
        this.size = size;
        this.effect = effect;
    }
}
