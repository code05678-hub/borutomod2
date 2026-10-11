package com.borutomod;

import net.fabricmc.api.ModInitializer;

public class BorutoMod implements ModInitializer {
    public static final String MOD_ID = "borutomod";

    @Override
    public void onInitialize() {
        ModItems.register();
    }
}
