package com.redhat;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String env = System.getenv("MODE");
        if (env == null) {
            throw new RuntimeException("Environment variable MODE is not set");
        } else if (!env.equals("slim") || !env.equals("standard") || !env.equals("large")) {
            throw new RuntimeException("Environment variable MODE is wrong");
        }
        return "Hello from ACME REST Quarkus with mode " + env;
    }
}