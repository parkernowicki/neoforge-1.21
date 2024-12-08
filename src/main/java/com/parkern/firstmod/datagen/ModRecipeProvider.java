package com.parkern.firstmod.datagen;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import com.parkern.firstmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> SHALE_SMELTABLES = List.of(ModBlocks.SHALE_BLOCK);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBBER_DUCK.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', ModItems.DUCK_ESSENCE.get())
                .unlockedBy("has_duck_essence", has(ModItems.DUCK_ESSENCE)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DUCK_ESSENCE.get(), 9)
                .requires(ModItems.RUBBER_DUCK)
                .unlockedBy("has_rubber_duck", has(ModItems.RUBBER_DUCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DUCK_ESSENCE.get(), 18)
                .requires(ModBlocks.WITHERER)
                .unlockedBy("has_witherer", has(ModBlocks.WITHERER))
                .save(recipeOutput, "firstmod:duck_essence_from_witherer");

        oreSmelting(recipeOutput, SHALE_SMELTABLES, RecipeCategory.MISC, ModItems.SHALE_OIL.get(), 0.1f, 200, "shale");
        oreBlasting(recipeOutput, SHALE_SMELTABLES, RecipeCategory.MISC, ModItems.SHALE_OIL.get(), 0.1f, 100, "shale");

        stairBuilder(ModBlocks.SHALE_STAIRS.get(), Ingredient.of(ModBlocks.SHALE_BLOCK)).group("shale")
                .unlockedBy("has_shale_block", has(ModBlocks.SHALE_BLOCK)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHALE_SLAB.get(), ModBlocks.SHALE_BLOCK.get());

        buttonBuilder(ModBlocks.SHALE_BUTTON.get(), Ingredient.of(ModBlocks.SHALE_BLOCK.get())).group("shale")
                .unlockedBy("has_shale_block", has(ModBlocks.SHALE_BLOCK.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.SHALE_PRESSURE_PLATE.get(), ModBlocks.SHALE_BLOCK.get());

        fenceBuilder(ModBlocks.SHALE_FENCE.get(), Ingredient.of(ModBlocks.SHALE_BLOCK.get())).group("shale")
                .unlockedBy("has_shale_block", has(ModBlocks.SHALE_BLOCK.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.SHALE_FENCE_GATE.get(), Ingredient.of(ModBlocks.SHALE_BLOCK.get())).group("shale")
                .unlockedBy("has_shale_block", has(ModBlocks.SHALE_BLOCK.get())).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHALE_WALL.get(), ModBlocks.SHALE_BLOCK.get());

        doorBuilder(ModBlocks.SHALE_DOOR.get(), Ingredient.of(ModBlocks.SHALE_BLOCK.get())).group("shale")
                .unlockedBy("has_shale_block", has(ModBlocks.SHALE_BLOCK.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.SHALE_TRAPDOOR.get(), Ingredient.of(ModBlocks.SHALE_BLOCK.get())).group("shale")
                .unlockedBy("has_shale_block", has(ModBlocks.SHALE_BLOCK.get())).save(recipeOutput);

        trimSmithing(recipeOutput, ModItems.KAUPEN_SMITHING_TEMPLATE.get(), ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "kaupen"));
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, FirstMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
