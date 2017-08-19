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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.IntHashMap;

public class ModKeyBindingImpl {
	private static final List<ModKeyBindingImpl> keybindArray = new ArrayList();
	private static final Set<String> keybindSet = new HashSet();
	private final String keyDescription;
	private final int keyCodeDefault;
	private final String keyCategory;
	private int keyCode;
	private boolean pressed;
	private int pressTime;

	public static void onTick(int p_74507_0_) {
		if (p_74507_0_ != 0) {
			Iterator<ModKeyBindingImpl> iterator = keybindArray.iterator();

			while (iterator.hasNext()) {
				ModKeyBindingImpl k = iterator.next();
				if(k.keyCode == p_74507_0_) {
					++k.pressTime;
				}
			}
		}
	}

	public static void setKeyBindState(int p_74510_0_, boolean p_74510_1_) {
		if (p_74510_0_ != 0) {
			Iterator<ModKeyBindingImpl> iterator = keybindArray.iterator();

			while (iterator.hasNext()) {
				ModKeyBindingImpl k = iterator.next();
				if(k.keyCode == p_74510_0_) {
					k.pressed = p_74510_1_;
				}
			}
		}
	}

	public static void unPressAllKeys() {
		Iterator<ModKeyBindingImpl> iterator = keybindArray.iterator();

		while (iterator.hasNext()) {
			iterator.next().unpressKey();
		}
	}

	public static void resetKeyBindingArrayAndHash() {
	}

	public static Set getKeybinds() {
		return keybindSet;
	}

	public ModKeyBindingImpl(String p_i45001_1_, int p_i45001_2_, String p_i45001_3_) {
		this.keyDescription = p_i45001_1_;
		this.keyCode = p_i45001_2_;
		this.keyCodeDefault = p_i45001_2_;
		this.keyCategory = p_i45001_3_;
		keybindArray.add(this);
		keybindSet.add(p_i45001_3_);
	}

	public boolean getIsKeyPressed() {
		return this.pressed;
	}

	public String getKeyCategory() {
		return this.keyCategory;
	}

	public boolean isPressed() {
		if (this.pressTime == 0) {
			return false;
		} else {
			--this.pressTime;
			return true;
		}
	}

	private void unpressKey() {
		this.pressTime = 0;
		this.pressed = false;
	}

	public String getKeyDescription() {
		return this.keyDescription;
	}

	public int getKeyCodeDefault() {
		return this.keyCodeDefault;
	}

	public int getKeyCode() {
		return this.keyCode;
	}

	public void setKeyCode(int p_151462_1_) {
		this.keyCode = p_151462_1_;
	}

	public int compareTo(KeyBinding p_compareTo_1_) {
		try {
			int i = I18n.format(this.keyCategory, new Object[0])
					.compareTo(I18n.format(p_compareTo_1_.getKeyCategory(), new Object[0]));
	
			if (i == 0) {
				i = I18n.format(this.keyDescription, new Object[0])
						.compareTo(I18n.format(p_compareTo_1_.getKeyDescription(), new Object[0]));
			}

			return i;
		}catch(NullPointerException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
