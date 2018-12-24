package xen.modone.blocks.machines;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import net.minecraft.item.ItemStack;
import xen.modone.init.ModBlocks;
import xen.modone.init.ModItems;

public class MemeProcessorRecipes {

    private static final MemeProcessorRecipes INSTANCE = new MemeProcessorRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static MemeProcessorRecipes getInstance() {
        return INSTANCE;
    }

    private MemeProcessorRecipes() {
        addMemeProcessorRecipe(new ItemStack(ModBlocks.memeBlock), new ItemStack(ModItems.memeDust), new ItemStack(ModBlocks.memeObsidian), 5.0f);
    }

    public void addMemeProcessorRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
        if (getProcessingResult(input1, input2) != ItemStack.EMPTY) return;
        this.smeltingList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getProcessingResult(ItemStack input1, ItemStack input2){
        for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()){
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey())){
                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()){
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey())){
                        return (ItemStack)ent.getValue();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2){
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList(){
        return this.smeltingList;
    }

    public float getProcessingExperience(ItemStack stack){
        for(Entry<ItemStack, Float> entry : this.experienceList.entrySet()){
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey())){
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0f;
    }

}
