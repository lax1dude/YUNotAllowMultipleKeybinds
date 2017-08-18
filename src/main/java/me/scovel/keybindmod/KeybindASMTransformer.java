package me.scovel.keybindmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

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

import me.scovel.keybindmod.transformers.GuiKeyBindingListTransformer;
import me.scovel.keybindmod.transformers.ITransformer;
import me.scovel.keybindmod.transformers.KeyBindingClassReplacerTransformer;
import net.minecraft.launchwrapper.IClassTransformer;

public class KeybindASMTransformer implements IClassTransformer {

	public static final Logger TransformLogger = LogManager.getLogger("KeybindMod");
	
	@Override public byte[] transform(String name, String transformedName, byte[] basicClass) {
		ITransformer t = null;

		if("net.minecraft.client.gui.GuiKeyBindingList$KeyEntry".equals(transformedName)) {
			t = new GuiKeyBindingListTransformer();
		}else if("net.minecraft.client.settings.KeyBinding".equals(transformedName)) {
			t = new KeyBindingClassReplacerTransformer();
		}
		
		if(t != null) {
			ClassNode nodeler = new ClassNode();
			ClassReader readler = new ClassReader(basicClass);
			readler.accept(nodeler, 0);
			try {
				t.transform(name, transformedName, nodeler, !name.equals(transformedName));
			}catch(Throwable t2) {
				TransformLogger.warn("Could not inject bytecode: "+transformedName);
				TransformLogger.info("Deal with it. (the game might be broken tho :\\ )");
			}
			ClassWriter writeler = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
			nodeler.accept(writeler);
			TransformLogger.info("Injected bytecode: "+transformedName);
			return writeler.toByteArray();
		}
		
		return basicClass;
	}

}
