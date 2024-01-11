/**
 * A server to receive lyrics from NCM
 */
package org.cubewhy.lyrics.server;

import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.InetSocketAddress;

@Log4j2
public class Server {
    private static Server instance;
    private final HttpServer server;

    private Server() throws IOException {
        // create a server at 127.0.0.1:10123
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 10123), 0);

        server.createContext("/", new LyricsHandler());
//        server.createContext("/close", exchange -> stop());
    }

    @SneakyThrows
    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
            log.info("Starting the server at " + instance.server.getAddress().toString().substring(1));
            instance.server.start();
        }
        return instance;
    }

    /**
     * Stop the server
     */
    public void stop() {
        instance.server.stop(0);
        instance = null;
    }
}
