package com.parkern.firstmod.compat;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import com.parkern.firstmod.recipe.GrowthChamberRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class GrowthChamberRecipeCategory implements IRecipeCategory<GrowthChamberRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "growth_chamber");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID,
            "textures/gui/growth_chamber/growth_chamber_gui.png");

    public static final RecipeType<GrowthChamberRecipe> GROWTH_CHAMBER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, GrowthChamberRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public GrowthChamberRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GROWTH_CHAMBER));
    }

    @Override
    public RecipeType<GrowthChamberRecipe> getRecipeType() {
        return GROWTH_CHAMBER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.firstmod.growth_chamber");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GrowthChamberRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public int getWidth() {
        return 176;
    }

    @Override
    public int getHeight() {
        return 85;
    }

    @Override
    public void draw(GrowthChamberRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        background.draw(guiGraphics);
    }
}
