package org.cubewhy.lyrics.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.log4j.Log4j2;
import net.weavemc.loader.api.event.EventBus;
import org.cubewhy.lyrics.events.LyricsUpdateEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Log4j2
public class LyricsHandler implements HttpHandler {
    public static String basic;
    public static String extra;

    public LyricsHandler() {
        EventBus.subscribe(this);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        if (uri.getPath().equals("/lyrics/lyrics")) {
            JsonObject json = new JsonParser().parse(body).getAsJsonObject();
            basic = json.get("basic").getAsString();
            extra = json.get("extra").getAsString();
            EventBus.callEvent(new LyricsUpdateEvent(basic, extra));
        }

        // default response
        String s = "200, ok";
        exchange.sendResponseHeaders(200, s.length());
        OutputStream out = exchange.getResponseBody();
        out.write(s.getBytes());
        out.flush();
        out.close();
    }
}
