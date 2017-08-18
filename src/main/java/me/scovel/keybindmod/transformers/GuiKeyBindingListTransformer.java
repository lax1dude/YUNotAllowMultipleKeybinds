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
	
	@Override public void transform(ClassNode clazz, boolean obfuscated) {
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
