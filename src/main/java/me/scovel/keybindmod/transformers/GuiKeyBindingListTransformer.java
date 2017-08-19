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

import java.util.ArrayList;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import me.scovel.keybindmod.KeybindASMTransformer;

public class GuiKeyBindingListTransformer implements ITransformer {
	
	@Override public void transform(String dee, String yee, ClassNode clazz, boolean obfuscated) {
		final String methodName = obfuscated ? "a" : "drawEntry";
		
		final String tessellator = obfuscated ? "bmh" : "net/minecraft/client/renderer/Tessellator";
		
		final String redName = obfuscated ? "m" : "RED";
		
		for(MethodNode methodler : clazz.methods){
			if(methodler.name.equals(methodName) && methodler.desc.equals("(IIIIIL"+tessellator+";IIZ)V")){
				AbstractInsnNode inst = null;
				for(AbstractInsnNode node : methodler.instructions.toArray()) {
					if((node instanceof FieldInsnNode) && ((FieldInsnNode)node).getOpcode() == Opcodes.GETSTATIC && ((FieldInsnNode)node).name.equals(redName)) {
						ArrayList<AbstractInsnNode> arr = new ArrayList();
						inst = node.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious();
						while(true) {
							AbstractInsnNode node2 = inst.getNext();
							if(!(inst instanceof LabelNode)) {
								arr.add(inst);
							}
							if((inst instanceof FieldInsnNode) && ((FieldInsnNode)inst).getOpcode() == Opcodes.PUTFIELD) {
								break;
							}
							inst = node2;
						}
						for(AbstractInsnNode n : arr) {
							methodler.instructions.remove(n);
						}
						break;
					}
				}
				break;
			}
		}
	}
	
}
