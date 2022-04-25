package com.github.eterdelta.cookietology.item.crafting.special;

import com.github.eterdelta.cookietology.Cookietology;
import com.github.eterdelta.cookietology.item.CookieItem;
import com.github.eterdelta.cookietology.item.DoughItem;
import com.github.eterdelta.cookietology.item.crafting.IBakingRecipe;
import com.github.eterdelta.cookietology.registry.CookietologyItems;
import com.github.eterdelta.cookietology.registry.CookietologyRecipes;
import com.github.eterdelta.cookietology.util.CookieHelper;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class CookieBakingRecipe implements IBakingRecipe {
    public static RecipeType<IBakingRecipe> TYPE = RecipeType.register(Cookietology.MODID + ":baking");
    public static final Serializer SERIALIZER = new Serializer();
    private final ResourceLocation id;

    public CookieBakingRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public boolean matches(Container container, Level level) {
        boolean dough = false;
        boolean cocoa = false;

        for (int i = 0; i < 2; ++i) {
            ItemStack slotStack = container.getItem(i);
            if (!slotStack.isEmpty()) {
                if (slotStack.is(CookietologyItems.COOKIE_SHAPED_DOUGH.get())) {
                    dough = true;
                } else {
                    if (!slotStack.is(Items.COCOA_BEANS)) {
                        return false;
                    }
                    cocoa = true;
                }
            }
        }
        return dough && cocoa;
    }

    @Override
    public ItemStack assemble(Container container) {
        DoughItem.DoughProperties doughProperties = DoughItem.DEFAULT_PROPERTIES;

        for (int i = 0; i < 2; ++i) {
            ItemStack slotStack = container.getItem(i);
            if (slotStack.is(CookietologyItems.COOKIE_SHAPED_DOUGH.get())) {
                doughProperties = CookieHelper.getDoughProperties(slotStack);
                break;
            }
        }

        return CookieItem.create(8,
                Math.abs(doughProperties.thickness() - doughProperties.brilliance()),
                doughProperties.brilliance() * (1.0F / doughProperties.thickness()),
                doughProperties.thickness() / (5 * doughProperties.brilliance()));
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CookieBakingRecipe.SERIALIZER;
    }

    @Override
    public int getCookTime() {
        return 1200;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CookieBakingRecipe> {

        Serializer() {
            this.setRegistryName(new ResourceLocation(Cookietology.MODID, "baking"));
		}

        @Override
        public CookieBakingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            return null;
        }

        @Nullable
        @Override
        public CookieBakingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, CookieBakingRecipe pRecipe) {

        }
    }

}
