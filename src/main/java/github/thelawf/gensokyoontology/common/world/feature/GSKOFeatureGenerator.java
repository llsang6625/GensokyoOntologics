package github.thelawf.gensokyoontology.common.world.feature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.GSKOOreType;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.core.init.FeatureRegistry;
import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class GSKOFeatureGenerator {
    public static void generateOverworldTrees(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (event.getName().equals(GSKOBiomes.SAKURA_FOREST.getRegistryName())) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> GSKOFeatures.SAKURA_TREE
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.65f, 2))));

            base.add(() -> Features.OAK
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1,0.05f, 2))));

            base.add(() -> GSKOFeatures.MAGIC_TREE
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square()
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.25f, 2))));
        }

    }

    @SuppressWarnings("all")
    public static void generateGensokyoTrees(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if (types.contains(GSKOBiomes.GSKO_FOREST_KEY)) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> GSKOFeatures.SAKURA_TREE
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.15f, 2))));

            base.add(() -> Features.FANCY_OAK
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1,0.15f, 2))));
        }

        if (event.getName().equals(GSKOBiomes.BAMBOO_FOREST_LOST_KEY.getRegistryName())) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> Features.BAMBOO_VEGETATION
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.9f, 2))));
        }
    }

    public static void generateFlowers(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (types.contains(BiomeDictionary.Type.WET) || types.contains(BiomeDictionary.Type.FOREST) ||
        types.contains(BiomeDictionary.Type.SWAMP)) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> GSKOFeatures.LYCORIS);
        }

    }

    public static void generateHerbPlants(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (types.contains(BiomeDictionary.Type.FOREST)) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION);
            base.add(() -> GSKOFeatures.WASABI);
        }
    }

    public static void generateOres(final BiomeLoadingEvent event) {
        for (GSKOOreType ore :  GSKOOreType.values()) {
            OreFeatureConfig config = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                    ore.getLazyBlock().get().getDefaultState(), ore.getMaxVeinSize());
        }
    }

    public static void generateGSKOStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        ResourceLocation bambooForestOfLost = new ResourceLocation(GensokyoOntology.MODID, "bamboo_forest_of_lost");

        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (biomeKey.getRegistryName().equals(bambooForestOfLost)) {
            List<Supplier<ConfiguredFeature<?,?>>> features = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.SURFACE_STRUCTURES);
            features.add(() -> GSKOFeatures.WATERFALL);

        }
    }

    public static void generateOverworldStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        // if (types.contains(BiomeDictionary.Type.PLAINS)) {
        //     List<Supplier<StructureFeature<?,?>>> structures = event.getGeneration().getStructures();
        //     structures.add(() -> StructureRegistry.HAKUREI_SHRINE.get().withConfiguration(
        //             IFeatureConfig.NO_FEATURE_CONFIG));
        // }
    }

    public static void addWaterfall (final BiomeLoadingEvent event) {
        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        ResourceLocation bambooForestOfLost = new ResourceLocation(GensokyoOntology.MODID, "bamboo_forest_of_lost");

        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (types.contains(BiomeDictionary.Type.MOUNTAIN)) {
            List<Supplier<ConfiguredFeature<?,?>>> features = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION);
            features.add(() -> GSKOFeatures.WATERFALL);
        }
    }
}
