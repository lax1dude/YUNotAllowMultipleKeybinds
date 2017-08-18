/*
 * MIT License
 * 
 * Copyright (c) 2017 Calder Young (LAX1DUDE)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */

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
