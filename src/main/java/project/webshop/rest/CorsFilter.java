package project.webshop.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    // token header
    @Value("${token.header}")
    private String tokenHeader;

    private static final String ALLOWED_METHODS = "GET,POST,PUT, DELETE";
    private static final String ALLOWED_HEADERS = "Origin, X-Requested-With, Content-Type, Accept, Authorization";
    private static final String MAX_AGE = "3600";
    private static final String ALLOWED_ORIGINS = "http://localhost:4200";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " + tokenHeader);// set header with token
        filterChain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }

    private boolean isOptionsRequest(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        return HttpMethod.OPTIONS.name().equalsIgnoreCase(httpRequest.getMethod());
    }

    // set Response header
//    private void setResponseHeaders(HttpServletResponse response) {
//        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ALLOWED_ORIGINS);
//        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
//        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, ALLOWED_HEADERS);
//        response.setHeader(ACCESS_CONTROL_MAX_AGE, MAX_AGE);
//    }


}
