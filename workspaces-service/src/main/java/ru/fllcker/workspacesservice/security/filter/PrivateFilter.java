package ru.fllcker.workspacesservice.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/workspaces/private/**")
public class PrivateFilter implements Filter {
    @Value("${private.secret}")
    private String secret;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String privateSecret = ((HttpServletRequest) request).getHeader("PrivateSecret");

        if (privateSecret != null && privateSecret.equals(secret)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
