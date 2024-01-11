package org.cubewhy.lyrics.events;

import net.weavemc.loader.api.event.Event;
import org.cubewhy.lyrics.server.LyricsHandler;

public class LyricsUpdateEvent extends Event {

    public final String basic;
    public final String extra;

    public LyricsUpdateEvent(String basic, String extra) {
        this.basic = basic;
        this.extra = extra;
    }
}
