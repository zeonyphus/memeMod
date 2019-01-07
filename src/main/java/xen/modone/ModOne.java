package xen.modone;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;
import xen.modone.blocks.tileentity.TileEntityMemeProcessor;
import xen.modone.init.ModArmor;
import xen.modone.init.ModBlocks;
import xen.modone.init.ModItems;
import xen.modone.init.ModRecipes;
import xen.modone.util.GuiHandler;
import xen.modone.worldgen.OreGen;

@Mod(modid = ModOne.MODID, name = ModOne.NAME, version = ModOne.VERSION, acceptedMinecraftVersions = ModOne.ACCEPTED_MINECRAFT_VERSIONS)
public class ModOne {

    public static final String MODID = "modone";
    public static final String NAME = "ModOne";
    public static final String VERSION = "0.7";
    public static final String ACCEPTED_MINECRAFT_VERSIONS = "[1.12]";

    public static final int guiMemeProcessor = 0;

    private static Logger logger;

    @Mod.Instance
    public static ModOne instance;

    /*
    @SidedProxy(clientSide = "xen.modone.proxy.ClientProxy",
            serverSide = "xen.modone.proxy.ServerProxy")
    public static IProxy proxy;
    */

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(ModOne.MODID + ":preInit");
        logger = event.getModLog();
        ModItems.init();
        ModArmor.init();
        ModBlocks.init();
        ModRecipes.init();
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
        GameRegistry.registerTileEntity(TileEntityMemeProcessor.class, "meme_processor");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(ModOne.MODID + ":init");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        System.out.println(ModOne.MODID + ":postInit");
    }
}
