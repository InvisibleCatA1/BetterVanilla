package com.invisiblecat.bettervanilla.block;


import com.invisiblecat.bettervanilla.utils.BlockUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class BlockRegistry {

    //Basic Block

    //Gel block
    public static final Block GEL_BLOCK = BlockUtils.createBasicBlock("gelblock", Material.STONE, 2);
    public static final Item GEL_BLOCK_ITEM = BlockUtils.createBlockItem(GEL_BLOCK, CreativeModeTab.TAB_BUILDING_BLOCKS);

    private static final Material GEL = (new Material.Builder(MaterialColor.CLAY)).build();

    //eather ore
    public static final Block EATHER_ORE = new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.8F, 9).requiresCorrectToolForDrops()).setRegistryName("eather_ore");
    public static final Item EATHER_ORE_ITEM = BlockUtils.createBlockItem(EATHER_ORE, CreativeModeTab.TAB_BUILDING_BLOCKS);

    // gel ore
    public static final Block GEL_ORE = new Block(BlockBehaviour.Properties.of(GEL).strength(2).requiresCorrectToolForDrops()).setRegistryName("geloreblock");
    public static final Item GEL_ORE_ITEM = BlockUtils.createBlockItem(GEL_ORE, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final Block DEEPSLATE_GEL_ORE = new Block(BlockBehaviour.Properties.of(GEL).strength(2.4F).requiresCorrectToolForDrops()).setRegistryName("deepslategeloreblock");
    public static final Item DEEPSLATE_GEL_ORE_ITEM = BlockUtils.createBlockItem(DEEPSLATE_GEL_ORE, CreativeModeTab.TAB_BUILDING_BLOCKS);

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();
        reg.register(GEL_BLOCK_ITEM);
        reg.register(GEL_ORE_ITEM);
        reg.register(DEEPSLATE_GEL_ORE_ITEM);
        reg.register(EATHER_ORE_ITEM);

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> reg = event.getRegistry();
        reg.register(GEL_BLOCK);
       reg.register(GEL_ORE);
       reg.register(DEEPSLATE_GEL_ORE);
       reg.register(EATHER_ORE);


    }
}





