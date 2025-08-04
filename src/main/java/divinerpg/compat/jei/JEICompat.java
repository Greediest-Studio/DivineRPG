package divinerpg.compat.jei;

import divinerpg.DivineRPG;
import divinerpg.compat.jei.base.ArcaniumExtractorRecipeWrapper;
import divinerpg.compat.jei.base.SmelterCategory;
import divinerpg.compat.jei.base.TripleRecipeWrapper;
import divinerpg.compat.jei.base.VillagerCategory;
import divinerpg.objects.blocks.tile.container.gui.ArcaniumExtractorGUI;
import divinerpg.objects.blocks.tile.entity.TileEntityArcaniumExtractor;
import divinerpg.objects.entities.container.gui.*;
import divinerpg.registry.ArmorRegistry;
import divinerpg.registry.BlockRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.WeaponRegistry;
import divinerpg.utils.LocalizeUtils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JEIPlugin
public class JEICompat implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiJackOMan.MERCHANT_GUI_TEXTURE,
                JeiReferences.JACK_O_MAN_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.jack_o_man.name"),
                WeaponRegistry.scythe));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiWorkshopMerchant.MERCHANT_GUI_TEXTURE,
                JeiReferences.WORKSHOP_MERCHANT_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.workshop_merchant.name"),
                ArmorRegistry.santaHelmet));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiWorkshopTinkerer.MERCHANT_GUI_TEXTURE,
                JeiReferences.WORKSHOP_TINKERER_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.workshop_tinkerer.name"),
                WeaponRegistry.frossivence));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiCaptainMerik.MERCHANT_GUI_TEXTURE,
                JeiReferences.CAPITAIN_MERIC_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.captain_merik.name"),
                WeaponRegistry.starlight));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiLivestockMerchant.MERCHANT_GUI_TEXTURE,
                JeiReferences.LIVESTOCK_MERCHANT_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.livestock_merchant.name"),
                ItemRegistry.shadowCoins));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiWarGeneral.MERCHANT_GUI_TEXTURE,
                JeiReferences.WAR_GENERAL_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.war_general.name"),
                WeaponRegistry.arcaniteBlaster));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiLeorna.MERCHANT_GUI_TEXTURE,
                JeiReferences.LEORNA_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.leorna.name"),
                ItemRegistry.firestockSeeds));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiDatticon.MERCHANT_GUI_TEXTURE,
                JeiReferences.DATTICON_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.datticon.name"),
                new ItemStack(BlockRegistry.demonFurnace)));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiLordVatticus.MERCHANT_GUI_TEXTURE,
                JeiReferences.LORD_VATTICUS_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.lord_vatticus.name"),
                ItemRegistry.strongArcanaPotion));

        registry.addRecipeCategories(new VillagerCategory(guiHelper,
                GuiZelus.MERCHANT_GUI_TEXTURE,
                JeiReferences.ZELUS_CATEGORY,
                LocalizeUtils.normal("entity.divinerpg.zelus.name"),
                ItemRegistry.golemOfRejuvenationEgg));

        registry.addRecipeCategories(new SmelterCategory(guiHelper,
                ArcaniumExtractorGUI.TEXTURES,
                JeiReferences.ARCANA_EXTRACTOR_CATEGORY,
                I18n.format("jei.gui.arcanium_extractor"),
                new ItemStack(ItemRegistry.collector)));
    }

    @Override
    public void register(IModRegistry registry) {
        DivineRPG.logger.info("Registering JEI compat");
        registry.addDescription(new ItemStack(BlockRegistry.rawArcanium), "jeicompat." + BlockRegistry.rawArcanium.getTranslationKey() + ".information");
    /*
        registerVillagerRecepies(registry, EntityJackOMan.getRecipeList(), JeiReferences.JACK_O_MAN_CATEGORY);
        registerVillagerRecepies(registry, EntityWorkshopMerchant.getAllRecipies(), JeiReferences.WORKSHOP_MERCHANT_CATEGORY);
        registerVillagerRecepies(registry, EntityWorkshopTinkerer.getAllRecipies(), JeiReferences.WORKSHOP_TINKERER_CATEGORY);
        registerVillagerRecepies(registry, EntityCaptainMerik.getAllRecipies(), JeiReferences.CAPITAIN_MERIC_CATEGORY);
        registerVillagerRecepies(registry, EntityLivestockMerchant.getAllRecipies(), JeiReferences.LIVESTOCK_MERCHANT_CATEGORY);
        registerVillagerRecepies(registry, EntityWarGeneral.getAllRecipies(), JeiReferences.WAR_GENERAL_CATEGORY);
        registerVillagerRecepies(registry, EntityLeorna.getAllRecipies(), JeiReferences.LEORNA_CATEGORY);
        registerVillagerRecepies(registry, EntityDatticon, JeiReferences.DATTICON_CATEGORY);
        registerVillagerRecepies(registry, EntityLordVatticus.getAllRecipies(), JeiReferences.LORD_VATTICUS_CATEGORY);
        registerVillagerRecepies(registry, EntityZelus.getAllRecipies(), JeiReferences.ZELUS_CATEGORY);*/
        List<ArcaniumExtractorRecipeWrapper> recipes = new ArrayList<>();

        for (Map.Entry<Item, ItemStack> entry : TileEntityArcaniumExtractor.getSmeltingRecipes().entrySet()) {
            recipes.add(new ArcaniumExtractorRecipeWrapper(
                    new ItemStack(entry.getKey()),
                    entry.getValue().copy()
            ));
        }

        registry.addRecipes(recipes, JeiReferences.ARCANA_EXTRACTOR_CATEGORY);

        for (ItemStack fuel : TileEntityArcaniumExtractor.getFuelItems()) {
            int burnTime = TileEntityArcaniumExtractor.getBurnTime(fuel);
            if (burnTime > 0) {
                registry.addIngredientInfo(fuel, ItemStack.class,
                        I18n.format("jei.arcanium_extractor.description"),
                        I18n.format("jei.arcanium_extractor.fuel") + formatBurnTime(burnTime));
            }
        }

        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.demonFurnace), VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.greenlightFurnace), VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.moltenFurnace), VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.moonlightFurnace), VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.whitefireFurnace), VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.coalstoneFurnace), VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.oceanfireFurnace), VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(BlockRegistry.arcaniumExtractor), JeiReferences.ARCANA_EXTRACTOR_CATEGORY);
    }

    private void registerVillagerRecepies(IModRegistry registry, List<MerchantRecipe> receipes, String name){
        registry.addRecipes(receipes
        .stream()
                .map(TripleRecipeWrapper::new)
        .collect(Collectors.toList()), name);
    }

    private String formatBurnTime(int ticks) {
        int seconds = ticks / 20;
        if (seconds > 0) {
            return seconds + " s";
        }
        return ticks + " t";
    }
}