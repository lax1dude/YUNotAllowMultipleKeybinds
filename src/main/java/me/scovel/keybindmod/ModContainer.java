package me.scovel.keybindmod;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class ModContainer extends DummyModContainer {
	public ModContainer() {
		super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId="multikeybindmod";
        meta.name="YUNotAllowMultipleKeybinds";
        meta.version="1.0.0";
        meta.credits="";
        meta.authorList=Arrays.asList("LAX1DUDE");
        meta.description="Allows you to assign multiple things to one key without disabling it. Useful for big modpacks.";
        meta.url="https://github.com/Coding-Eaglers/YUNotAllowMultipleKeybinds/releases";
        meta.screenshots=new String[0];
        meta.logoFile="";
	}
	
	@Override public Object getMod(){
        return this;
    }
	
	@Override public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }
}
