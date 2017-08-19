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
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import me.scovel.keybindmod.KeybindASMTransformer;
import me.scovel.keybindmod.ModKeyBindingImpl;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindingClassReplacerTransformer implements ITransformer {
	
	@Override public void transform(String yee, String ler, ClassNode clazz, boolean obfuscated) {
		final String reImpl = "me/scovel/keybindmod/ModKeyBindingImpl";
		
		final String onTick          = obfuscated ? "a" : "onTick"; //static
		final String setKeyBindState = obfuscated ? "a" : "setKeyBindState"; //static
		final String unPressAllKeys  = obfuscated ? "a" : "unPressAllKeys"; //static
		final String resetKeyBindingArrayAndHash = obfuscated ? "b" : "resetKeyBindingArrayAndHash"; //static
		final String getIsKeyPressed = obfuscated ? "d" : "getIsKeyPressed";
		final String getKeyCategory  = obfuscated ? "e" : "getKeyCategory";
		final String isPressed  = obfuscated ? "f" : "isPressed";
		final String unpressKey = obfuscated ? "j" : "unpressKey";
		final String getKeyDescription  = obfuscated ? "g" : "getKeyDescription";
		final String getKeyCodeDefault  = obfuscated ? "h" : "getKeyCodeDefault";
		final String getKeyCode         = obfuscated ? "i" : "getKeyCode";
		final String setKeyCode         = obfuscated ? "b" : "setKeyCode";
		final String compareTo          = obfuscated ? "a" : "compareTo";
		final String getKeybinds        = obfuscated ? "c" : "getKeybinds"; //static
		
		clazz.fields.add(new FieldNode(Opcodes.ASM5, Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL, "deevile", "L"+reImpl+";", null, null));

		final String iYee = yee.replace('.', '/').replace('$', '/');
		
		for(MethodNode methodler : clazz.methods){
			if(methodler.name.equals(onTick) && methodler.desc.equals("(I)V")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ILOAD,        0));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, reImpl, "onTick", "(I)V"));
				methodler.instructions.add(new InsnNode      (Opcodes.RETURN));
			}else if(methodler.name.equals(setKeyBindState) && methodler.desc.equals("(IZ)V")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ILOAD,        0));
				methodler.instructions.add(new VarInsnNode   (Opcodes.ILOAD,        1));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, reImpl, "setKeyBindState", "(IZ)V"));
				methodler.instructions.add(new InsnNode      (Opcodes.RETURN));
			}else if(methodler.name.equals(unPressAllKeys) && methodler.desc.equals("()V")){
				methodler.instructions.clear();
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, reImpl, "unPressAllKeys", "()V"));
				methodler.instructions.add(new InsnNode      (Opcodes.RETURN));
			}else if(methodler.name.equals(resetKeyBindingArrayAndHash) && methodler.desc.equals("()V")){
				methodler.instructions.clear();
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, reImpl, "resetKeyBindingArrayAndHash", "()V"));
				methodler.instructions.add(new InsnNode      (Opcodes.RETURN));
			}else if(methodler.name.equals(getIsKeyPressed) && methodler.desc.equals("()Z")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "getIsKeyPressed", "()Z"));
				methodler.instructions.add(new InsnNode      (Opcodes.IRETURN));
			}else if(methodler.name.equals(getKeyCategory) && methodler.desc.equals("()Ljava/lang/String;")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "getKeyCategory", "()Ljava/lang/String;"));
				methodler.instructions.add(new InsnNode      (Opcodes.ARETURN));
			}else if(methodler.name.equals(isPressed) && methodler.desc.equals("()Z")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "isPressed", "()Z"));
				methodler.instructions.add(new InsnNode      (Opcodes.IRETURN));
			}else if(methodler.name.equals(unpressKey) && methodler.desc.equals("()V")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "unpressKey", "()Z"));
				methodler.instructions.add(new InsnNode      (Opcodes.RETURN));
			}else if(methodler.name.equals(getKeyDescription) && methodler.desc.equals("()Ljava/lang/String;")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "getKeyDescription", "()Ljava/lang/String;"));
				methodler.instructions.add(new InsnNode      (Opcodes.ARETURN));
			}else if(methodler.name.equals(getKeyCodeDefault) && methodler.desc.equals("()I")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "getKeyCodeDefault", "()I"));
				methodler.instructions.add(new InsnNode      (Opcodes.IRETURN));
			}else if(methodler.name.equals(getKeyCode) && methodler.desc.equals("()I")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "getKeyCode", "()I"));
				methodler.instructions.add(new InsnNode      (Opcodes.IRETURN));
			}else if(methodler.name.equals(setKeyCode) && methodler.desc.equals("(I)V")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new VarInsnNode   (Opcodes.ILOAD,         1));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "setKeyCode", "(I)V"));
				methodler.instructions.add(new InsnNode      (Opcodes.RETURN));
			}else if(methodler.name.equals(compareTo) && methodler.desc.equals("(L"+iYee+";)I")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new FieldInsnNode (Opcodes.GETFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         1));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, reImpl, "compareTo", "(L"+iYee+";)I"));
				methodler.instructions.add(new InsnNode      (Opcodes.IRETURN));
			}else if(methodler.name.equals(getKeybinds) && methodler.desc.equals("()Ljava/util/Set;")){
				methodler.instructions.clear();
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, reImpl, "getKeybinds", "()Ljava/util/Set;"));
				methodler.instructions.add(new InsnNode      (Opcodes.ARETURN));
			}else if(methodler.name.equals("<init>") && methodler.desc.equals("(Ljava/lang/String;ILjava/lang/String;)V")){
				methodler.instructions.clear();
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V"));
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         0));
				methodler.instructions.add(new TypeInsnNode  (Opcodes.NEW,           reImpl));
				methodler.instructions.add(new InsnNode      (Opcodes.DUP));
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         1));
				methodler.instructions.add(new VarInsnNode   (Opcodes.ILOAD,         2));
				methodler.instructions.add(new VarInsnNode   (Opcodes.ALOAD,         3));
				methodler.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, reImpl, "<init>",  "(Ljava/lang/String;ILjava/lang/String;)V"));
				methodler.instructions.add(new FieldInsnNode (Opcodes.PUTFIELD,      iYee, "deevile", "L"+reImpl+";"));
				methodler.instructions.add(new InsnNode      (Opcodes.RETURN));
			}
		}
	}
}
