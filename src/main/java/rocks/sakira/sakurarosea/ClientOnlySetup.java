package rocks.sakira.sakurarosea;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import rocks.sakira.sakurarosea.common.Materials;
import rocks.sakira.sakurarosea.common.block.Blocks;
import rocks.sakira.sakurarosea.common.entities.Entities;
import rocks.sakira.sakurarosea.common.entities.renderers.SakuraBoatEntityRenderer;
import rocks.sakira.sakurarosea.common.particle.Particles;
import rocks.sakira.sakurarosea.common.tileentities.TileEntities;
import rocks.sakira.sakurarosea.common.tileentities.renderers.SakuraChestTileEntityRenderer;
import rocks.sakira.sakurarosea.common.tileentities.renderers.SakuraSignTileEntityRenderer;
import rocks.sakira.sakurarosea.common.wood.FaeWoodType;

import static rocks.sakira.sakurarosea.Constants.MOD_ID;

public class ClientOnlySetup {
    @OnlyIn(Dist.CLIENT)
    public static void setup() {
        Atlases.SIGN_MATERIALS.put(FaeWoodType.SAKURA, new RenderMaterial(Atlases.SIGN_ATLAS, new ResourceLocation(MOD_ID, "entity/signs/sakura")));

        RenderTypeLookup.setRenderLayer(Blocks.PINK_CLAY_DOOR_BLOCK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAKURA_SAPLING_BLOCK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.WHITE_CLAY_DOOR_BLOCK.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(Blocks.SAKURA_LEAF_CARPET.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.ALT_SAKURA_LEAF_CARPET.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.WHITE_SAKURA_LEAF_CARPET.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(Blocks.POTTED_SAKURA_SAPLING_BLOCK.get(), RenderType.getCutout());

        ClientRegistry.bindTileEntityRenderer(
                TileEntities.SAKURA_CHEST_ENTITY.get(),
                SakuraChestTileEntityRenderer::new
        );

        ClientRegistry.bindTileEntityRenderer(
                TileEntities.SAKURA_SIGN_ENTITY.get(),
                SakuraSignTileEntityRenderer::new
        );

        Minecraft.getInstance().getRenderManager().register(
                Entities.SAKURA_BOAT_ENTITY.get(),
                new SakuraBoatEntityRenderer(Minecraft.getInstance().getRenderManager())
        );
    }

    @OnlyIn(Dist.CLIENT)
    public static void textureStitchPre(final TextureStitchEvent.Pre event) {
        if (event.getMap().getTextureLocation().equals(Atlases.CHEST_ATLAS)) {
            event.addSprite(Materials.SAKURA_CHEST_MATERIAL.getTextureLocation());
            event.addSprite(Materials.SAKURA_CHEST_LEFT_MATERIAL.getTextureLocation());
            event.addSprite(Materials.SAKURA_CHEST_RIGHT_MATERIAL.getTextureLocation());
        }

        if (event.getMap().getTextureLocation().equals(Atlases.SIGN_ATLAS)) {
            event.addSprite(Materials.SAKURA_SIGN_MATERIAL.getTextureLocation());
        }

        if (event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
            event.addSprite(Materials.SAKURA_SHIELD_BASE.getTextureLocation());
            event.addSprite(Materials.SAKURA_SHIELD_NO_PATTERN.getTextureLocation());
        }
    }

    public static void registerParticleFactories() {
        Particles.registerFactories();
    }
}
