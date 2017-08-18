package me.scovel.keybindmod.transformers;

import org.objectweb.asm.tree.ClassNode;

import me.scovel.keybindmod.KeybindASMTransformer;

public class GuiKeyBindingListTransformer implements ITransformer {
	
	@Override public void transform(ClassNode clazz) {
		KeybindASMTransformer.TransformLogger.info("deev");
	}
	
}
