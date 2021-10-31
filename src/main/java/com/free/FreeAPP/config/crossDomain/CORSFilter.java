package com.free.FreeAPP.config.crossDomain;

/**
 * @Date 2021/10/17   22:25
 */

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {
    public CORSFilter() {
    }

    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", containerRequestContext.getHeaderString("Origin"));
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "*");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "*");
        containerResponseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
