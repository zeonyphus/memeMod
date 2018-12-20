package xen.modone;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;
import xen.modone.init.ModArmor;
import xen.modone.init.ModBlocks;
import xen.modone.init.ModItems;
import xen.modone.init.ModRecipies;
import xen.modone.worldgen.OreGen;

@Mod(modid = ModOne.MODID, name = ModOne.NAME, version = ModOne.VERSION, acceptedMinecraftVersions = ModOne.ACCEPTED_MINECRAFT_VERSIONS)
public class ModOne {

    public static final String MODID = "modone";
    public static final String NAME = "ModOne";
    public static final String VERSION = "0.5";
    public static final String ACCEPTED_MINECRAFT_VERSIONS = "[1.12]";

    private static Logger logger;

    @Mod.Instance
    public static ModOne instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(ModOne.MODID + ":preInit");
        logger = event.getModLog();
        ModItems.init();
        ModArmor.init();
        ModBlocks.init();
        ModRecipies.init();
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(ModOne.MODID + ":init");
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        System.out.println(ModOne.MODID + ":postInit");
    }
}
