package net.mcreator.havahsadventure.item;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.havahsadventure.HavahsAdventureMod;

public class EyeBannerPatternItem extends BannerPatternItem {
	public static final TagKey<BannerPattern> PROVIDED_PATTERNS = TagKey.create(Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(HavahsAdventureMod.MODID, "pattern_item/eye_banner_pattern"));

	public EyeBannerPatternItem() {
		super(PROVIDED_PATTERNS, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public MutableComponent getDisplayName() {
		return Component.translatable(this.getDescriptionId() + ".patterns");
	}
}