package net.mcreator.havahsadventure.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class LightweightMobEffect extends MobEffect {
	public LightweightMobEffect() {
		super(MobEffectCategory.NEUTRAL, -256);
	}
}