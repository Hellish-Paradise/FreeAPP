package com.free.FreeAPP;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @Date 2021/10/18   9:13
 */
@Path("/hello-world")
public class HelloResource {
    public HelloResource() {
    }

    @GET
    @Produces({"text/plain"})
    public String hello() {
        return "Hello, World!";
    }
}
