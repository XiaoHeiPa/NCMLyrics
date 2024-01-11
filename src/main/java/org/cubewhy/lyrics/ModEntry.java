package org.cubewhy.lyrics;

import net.minecraft.client.gui.ScaledResolution;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.event.EventBus;
import net.weavemc.loader.api.event.RenderGameOverlayEvent;
import net.weavemc.loader.api.event.SubscribeEvent;
import org.cubewhy.lyrics.server.LyricsHandler;
import org.cubewhy.lyrics.server.Server;

import java.awt.*;

import static org.cubewhy.lyrics.utils.MinecraftInstance.mc;

public class ModEntry implements ModInitializer {
    @Override
    public void preInit() {
        EventBus.subscribe(this);
        new Thread(Server::getInstance).start();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        ScaledResolution resolution = new ScaledResolution(mc);
        int x = resolution.getScaledWidth() / 2 - Math.max(mc.fontRendererObj.getStringWidth(LyricsHandler.basic), mc.fontRendererObj.getStringWidth(LyricsHandler.extra));
        int y = resolution.getScaledHeight() / 5 * 4;
        mc.fontRendererObj.drawString(LyricsHandler.basic, x, y, new Color(255, 255, 255).getRGB(), true);
        mc.fontRendererObj.drawString(LyricsHandler.extra, x, y + mc.fontRendererObj.FONT_HEIGHT + 2, new Color(255, 255, 255).getRGB(), true);
    }
}
