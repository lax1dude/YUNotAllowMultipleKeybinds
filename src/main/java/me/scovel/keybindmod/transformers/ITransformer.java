package me.scovel.keybindmod.transformers;

import org.objectweb.asm.tree.ClassNode;

public interface ITransformer {
	abstract void transform(ClassNode clazz, boolean obfuscated);
}
