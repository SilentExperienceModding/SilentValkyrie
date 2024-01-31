package com.darkwolfie.silentvalkyrie.datagen;

import com.darkwolfie.silentvalkyrie.Main;
import com.darkwolfie.silentvalkyrie.block.ModBlocks;
import com.darkwolfie.silentvalkyrie.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ORE_PLUTOSTEEL = List.of(ModItems.MOLTEN_PLUTOSTEEL.get(),
            ModBlocks.plutosteel_ORE.get(), ModBlocks.Deepslate_plutosteel_ORE.get());
    private static final List<ItemLike> ORE_VAL = List.of(ModItems.MOLTEN_valkyrium.get(),
            ModBlocks.valkyrium_ORE.get(), ModBlocks.Deepslate_valkyrium_ORE.get());



    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, ORE_PLUTOSTEEL, RecipeCategory.MISC, ModItems.HARDENED_PLUTOSTEEL.get(), 0.25f, 100, "ores_molten");
        oreSmelting(pWriter, ORE_PLUTOSTEEL, RecipeCategory.MISC, ModItems.HARDENED_PLUTOSTEEL.get(), 0.25f, 100, "ores_molten");
        oreBlasting(pWriter, ORE_VAL, RecipeCategory.MISC, ModItems.HARDENED_valkyrium.get(), 0.25f, 100, "ores_molten");
        oreSmelting(pWriter, ORE_VAL, RecipeCategory.MISC, ModItems.HARDENED_valkyrium.get(), 0.25f, 100, "ores_molten");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.valkyrium_block.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.valkyrium.get())
                .unlockedBy(getHasName(ModItems.valkyrium.get()), has(ModItems.valkyrium.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.valkyrium.get(), 9)
                .requires(ModBlocks.valkyrium_block.get())
                .unlockedBy(getHasName(ModBlocks.valkyrium_block.get()), has(ModBlocks.valkyrium_block.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike),
                    pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer,
                            Main.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));

        }

    }
}
