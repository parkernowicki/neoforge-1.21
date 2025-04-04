package com.parkern.firstmod;

import com.parkern.firstmod.block.ModBlocks;
import com.parkern.firstmod.block.entity.ModBlockEntities;
import com.parkern.firstmod.block.entity.renderer.PedestalBlockEntityRenderer;
import com.parkern.firstmod.component.ModDataComponents;
import com.parkern.firstmod.effect.ModEffects;
import com.parkern.firstmod.enchantment.ModEnchantmentEffects;
import com.parkern.firstmod.entity.ModEntities;
import com.parkern.firstmod.entity.client.ChairRenderer;
import com.parkern.firstmod.entity.client.GeckoRenderer;
import com.parkern.firstmod.entity.client.GrouseRenderer;
import com.parkern.firstmod.entity.client.TomahawkProjectileRenderer;
import com.parkern.firstmod.entity.client.TuskyRenderer;
import com.parkern.firstmod.item.ModCreativeModeTabs;
import com.parkern.firstmod.item.ModItems;
import com.parkern.firstmod.loot.ModLootModifiers;
import com.parkern.firstmod.particle.ModParticles;
import com.parkern.firstmod.particle.WithererParticles;
import com.parkern.firstmod.potion.ModPotions;
import com.parkern.firstmod.recipe.ModRecipes;
import com.parkern.firstmod.screen.ModMenuTypes;
import com.parkern.firstmod.screen.custom.GrowthChamberScreen;
import com.parkern.firstmod.screen.custom.PedestalScreen;
import com.parkern.firstmod.sound.ModSounds;
import com.parkern.firstmod.util.ModItemProperties;
import com.parkern.firstmod.villager.ModVillagers;
import com.parkern.firstmod.worldgen.biome.ModTerraBlender;
import com.parkern.firstmod.worldgen.biome.surface.ModSurfaceRules;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FirstMod.MOD_ID)
public class FirstMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "firstmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public FirstMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModDataComponents.register(modEventBus);
        ModSounds.register(modEventBus);

        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);
        ModEntities.register(modEventBus);

        ModTerraBlender.registerBiomes();

        ModVillagers.register(modEventBus);
        ModParticles.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RUBBER_DUCK);
            event.accept(ModItems.DUCK_ESSENCE);
            event.accept(ModItems.SHALE_OIL);
            event.accept(ModItems.RAW_TITANITE);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.SHALE_BLOCK);
            event.accept(ModBlocks.TITANITE_ORE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();

            EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);
            EntityRenderers.register(ModEntities.GROUSE.get(), GrouseRenderer::new);
            EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
            EntityRenderers.register(ModEntities.CHAIR_ENTITY.get(), ChairRenderer::new);
            EntityRenderers.register(ModEntities.TUSKY.get(), TuskyRenderer::new);
        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.WITHERER_PARTICLES.get(), WithererParticles.Provider::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
            event.register(ModMenuTypes.GROWTH_CHAMBER_MENU.get(), GrowthChamberScreen::new);
        }
    }
}
