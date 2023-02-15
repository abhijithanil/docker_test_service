package com.service;

import com.service.handlers.HiHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Set;

public class APIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(APIController.class);

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(9191), 0);
        server.createContext("/hi", new HiHandler());
        server.createContext("/visits", new VisitHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        LOGGER.info("Started server..");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Interrupting threads......");
            Set<Thread> runningThreads = Thread.getAllStackTraces().keySet();
            for (Thread th : runningThreads) {
                try {
                    if (th != Thread.currentThread()
                            && !th.isDaemon()
                            && th.isInterrupted()) {
                        LOGGER.info("Waiting '" + th.getName() + "' termination");
                        th.join();
                    }
                } catch (InterruptedException ex) {
                    LOGGER.info("Shutdown interrupted");
                }
            }
            LOGGER.info("Shutdown finished");
        }));
    }

}