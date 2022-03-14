package com.example.demo.filter;


import com.sun.net.httpserver.HttpExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)   //Mean this is the first filet if there are more
public class TestFilter implements Filter {
    Logger LOG = LoggerFactory.getLogger(TestFilter.class);

    //Sample if we want to start a transction in any request

    //If we want this to happen only for particuer URL the we need to deaclre
    // FilterRegistrationBean  see in DemoApplication class
    //
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
{

            HttpServletRequest req = (HttpServletRequest) request;
            LOG.info(
                    "Starting a transaction for req : {}",
                    req.getRequestURI());

            chain.doFilter(request, response);
            LOG.info(
                    "Committing a transaction for req : {}",
                    req.getRequestURI());
        }
    }
}
