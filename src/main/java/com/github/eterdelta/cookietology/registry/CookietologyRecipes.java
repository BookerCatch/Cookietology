package com.github.eterdelta.cookietology.registry;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.item.crafting.IBakingRecipe;
import com.github.eterdelta.cookietology.item.crafting.IMixingRecipe;
import com.github.eterdelta.cookietology.item.crafting.MixingRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.CookieBakingRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.DoughBrillianceRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.DoughCompressingRecipe;
import com.github.eterdelta.cookietology.item.crafting.special.DoughMoldingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CookietologyRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cookietology.MODID);

    public static final RegistryObject<RecipeSerializer<DoughCompressingRecipe>> DOUGH_COMPRESSING_SERIALIZER = RECIPE_SERIALIZERS.register("crafting_special_doughcompressing", () -> new SimpleRecipeSerializer<>(DoughCompressingRecipe::new));
    public static final RegistryObject<RecipeSerializer<DoughMoldingRecipe>> DOUGH_MOLDING_SERIALIZER = RECIPE_SERIALIZERS.register("crafting_special_doughmolding", () -> new SimpleRecipeSerializer<>(DoughMoldingRecipe::new));
    public static final RegistryObject<RecipeSerializer<DoughBrillianceRecipe>> DOUGH_BRILLIANCE_SERIALIZER = RECIPE_SERIALIZERS.register("mixing_special_doughbrilliance", () -> new SimpleRecipeSerializer<>(DoughBrillianceRecipe::new));
    public static final RegistryObject<RecipeSerializer<CookieBakingRecipe>> COOKIE_BAKING_SERIALIZER = RECIPE_SERIALIZERS.register("baking_special_cookie", () -> new SimpleRecipeSerializer<>(CookieBakingRecipe::new));

}
