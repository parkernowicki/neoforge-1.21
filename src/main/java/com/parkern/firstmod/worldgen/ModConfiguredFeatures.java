package com.parkern.firstmod.worldgen;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TITANITE_ORE_KEY = registerKey("titanite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_TITANITE_ORE_KEY = registerKey("nether_titanite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_TITANITE_ORE_KEY = registerKey("end_titanite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_KEY = registerKey("bloodwood");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GOJI_BERRY_BUSH_KEY = registerKey("goji_berry_bush");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        // RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldTitaniteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TITANITE_ORE.get().defaultBlockState()));
                // OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TITANITE_ORE.get().defaultBlockState());

        register(context, OVERWORLD_TITANITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTitaniteOres, 9));
        register(context, NETHER_TITANITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherReplaceables,
                ModBlocks.NETHER_TITANITE_ORE.get().defaultBlockState(), 9));
        register(context, END_TITANITE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.END_TITANITE_ORE.get().defaultBlockState(), 9));

        register(context, BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),

                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(Blocks.NETHERRACK)).build());

        register(context, GOJI_BERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GOJI_BERRY_BUSH.get()
                                        .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                        ), List.of(Blocks.GRASS_BLOCK)));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
