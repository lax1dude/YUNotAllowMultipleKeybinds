package me.scovel.keybindmod.transformers;

import org.objectweb.asm.tree.ClassNode;

public interface ITransformer {
	public abstract void transform(ClassNode clazz);
}
