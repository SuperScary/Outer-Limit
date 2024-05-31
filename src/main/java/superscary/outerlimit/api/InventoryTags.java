package superscary.outerlimit.api;

import superscary.outerlimit.OL;

public class InventoryTags
{

    public static final String INVENTORY = builderOf("inventory");
    public static final String ENERGY = builderOf("energy");
    public static final String FLUID = builderOf("fluid");
    public static final String GAS = builderOf("gas");
    public static final String PROGRESS = builderOf("progress");

    protected static String builderOf (String input)
    {
        return OL.getModid() + "." + input;
    }

}
