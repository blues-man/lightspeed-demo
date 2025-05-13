package com.redhat;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/alive")
public class HealthResource {

        @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Oh, I, oh I'm still alive";
    }

}
