package rocks.sakira.sakurarosea;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rocks.sakira.sakurarosea.common.block.Blocks;
import rocks.sakira.sakurarosea.common.entities.Entities;
import rocks.sakira.sakurarosea.common.event.BlockClickedEventHandler;
import rocks.sakira.sakurarosea.common.feature.Features;
import rocks.sakira.sakurarosea.common.item.Items;
import rocks.sakira.sakurarosea.common.particle.Particles;
import rocks.sakira.sakurarosea.common.tileentities.TileEntities;
import rocks.sakira.sakurarosea.common.world.biome.Biomes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

@Mod(MOD_ID)
public class SakuraRosea {
    private static final Logger LOGGER = LogManager.getLogger();

    public SakuraRosea() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.register(this);
        eventBus.register(BlockClickedEventHandler.class);
        eventBus.register(Particles.class);

        Biomes.REGISTER.register(eventBus);
        Blocks.REGISTER.register(eventBus);
        Entities.REGISTER.register(eventBus);
        Features.REGISTER.register(eventBus);
        Items.REGISTER.register(eventBus);
        TileEntities.REGISTER.register(eventBus);
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) throws NoSuchFieldException, IllegalAccessException {
        Biomes.addFeatures();
        Biomes.registerSpawn();
        Biomes.registerTypes();
        Biomes.registerEntries();

        // This allows our signs to render correctly.
        Field f = ObfuscationReflectionHelper.findField(TileEntityType.class, "field_223046_I");

        try {
            // Create a list based on the current allowed blocks set, so we can add ours.
            Set<Block> allowedBlocks = (Set<Block>) f.get(TileEntityType.SIGN);
            ArrayList<Block> blocks = new ArrayList<>(allowedBlocks);

            // Add our blocks to the list.
            blocks.add(Blocks.SAKURA_SIGN_BLOCK.get());
            blocks.add(Blocks.SAKURA_WALL_SIGN_BLOCK.get());

            // Copy our blocks into a set, as required by TileEntityType.
            Set<Block> allAllowedBlocks = ImmutableSet.copyOf(blocks);

            // Finally, update the reference within the SIGN TileEntityType to the set we just made.
            f.set(TileEntityType.SIGN, allAllowedBlocks);
        } catch (IllegalAccessException e) {
            // If it didn't work, we should log the problem and then re-throw the exception.
            // If this fails, signs won't work - it's better to crash the game than allow it
            // to run with broken blocks or tile entities.
            LOGGER.error("Failed to set up allowable sign block types", e);
            throw e;
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(final FMLClientSetupEvent event) {
        ClientOnlySetup.setup();
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void textureStitchPre(final TextureStitchEvent.Pre event) {
        ClientOnlySetup.textureStitchPre(event);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void registerParticleFactory(final ParticleFactoryRegisterEvent event) {
        ClientOnlySetup.registerParticleFactories();
    }
}
