package com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.models.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class VisitHandler implements HttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitHandler.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange ctx) throws IOException {

        Jedis jedis = new Jedis("redis-server", 6379);
        String response;
        InputStream body = ctx.getRequestBody();
        try {
            final User user = objectMapper.readValue(body, User.class);
            LOGGER.info("Processing for user: " + user.toString());
            if (user.getUserId().equals("1")) {
                // force crashing
                LOGGER.info("Crashing...");
                System.exit(1);
            }
            String visitValue;
            visitValue = jedis.get(user.getUserId());
            int visit = 1;
            if (visitValue != null) {
                visit = visit + Integer.parseInt(visitValue);
            }
            jedis.set(user.getUserId(), String.valueOf(visit));
            response = "You have visited: " + visit + " times";
            ctx.sendResponseHeaders(200, response.length());
        } catch (Exception e) {
            response = e.getMessage();
            ctx.sendResponseHeaders(500, response.length());
        }
        OutputStream os = ctx.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
