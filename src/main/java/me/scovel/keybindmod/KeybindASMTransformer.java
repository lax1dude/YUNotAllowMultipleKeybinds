package me.scovel.keybindmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import me.scovel.keybindmod.transformers.GuiKeyBindingListTransformer;
import me.scovel.keybindmod.transformers.ITransformer;
import net.minecraft.launchwrapper.IClassTransformer;

public class KeybindASMTransformer implements IClassTransformer {

	public static final Logger TransformLogger = LogManager.getLogger("KeybindMod");
	
	@Override public byte[] transform(String name, String transformedName, byte[] basicClass) {
		ITransformer t = null;
		
		if("net.minecraft.client.gui.GuiKeyBindingList$KeyEntry".equals(transformedName)) {
			t = new GuiKeyBindingListTransformer();
		} //lol
		
		if(t != null) {
			ClassNode nodeler = new ClassNode();
			ClassReader readler = new ClassReader(basicClass);
			readler.accept(nodeler, 0);
			try {
				t.transform(nodeler, !name.equals(transformedName));
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
