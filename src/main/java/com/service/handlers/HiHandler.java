package com.service.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

public class HiHandler implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HiHandler.class);

    @Override
    public void handle(HttpExchange ctx) throws IOException {
        LOGGER.info("Processing hi request");
        String response = "Hi.... hello";
        ctx.sendResponseHeaders(200, response.length());
        OutputStream os = ctx.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
