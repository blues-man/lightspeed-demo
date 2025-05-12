package com.redhat;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.NotFoundException;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String env = System.getenv("MODE");
        if (env == null) {
            throw new RuntimeException("Environment variable MODE is not set");
        }
        return "Hello from ACME REST Quarkus with mode " + env;
    }
}