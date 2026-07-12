/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.havahsadventure.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.havahsadventure.potion.LightweightMobEffect;
import net.mcreator.havahsadventure.HavahsAdventureMod;

public class HavahsAdventureModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, HavahsAdventureMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> LIGHTWEIGHT = REGISTRY.register("lightweight", () -> new LightweightMobEffect());
}