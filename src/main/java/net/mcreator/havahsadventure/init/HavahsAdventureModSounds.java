/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.havahsadventure.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.havahsadventure.HavahsAdventureMod;

public class HavahsAdventureModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, HavahsAdventureMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> ABOVE_PORTEL_AMBIENT = REGISTRY.register("above_portel.ambient",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("havahs_adventure", "above_portel.ambient")));
}