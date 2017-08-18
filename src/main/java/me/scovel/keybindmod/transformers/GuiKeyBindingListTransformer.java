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

package me.scovel.keybindmod.transformers;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import me.scovel.keybindmod.KeybindASMTransformer;

public class GuiKeyBindingListTransformer implements ITransformer {
	
	@Override public void transform(String dee, String yee, ClassNode clazz, boolean obfuscated) {
		final String methodName = obfuscated ? "a" : "drawEntry";
		
		for(MethodNode methodler : clazz.methods){
			if(methodler.name.equals(methodName) && methodler.desc.equals("(IIIIILnet/minecraft/client/renderer/Tessellator;IIZ)V")){
				int line = 0;
				for(AbstractInsnNode instruction : methodler.instructions.toArray()){
					if(instruction instanceof LineNumberNode) {
						line = ((LineNumberNode)instruction).line;
					}
					if(line >= 167 && line <= 170 && !(instruction instanceof LabelNode)){
						methodler.instructions.remove(instruction);
					}
				}
				break;
			}
		}
	}
	
}
