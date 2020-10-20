package com.gnnu.zuulserver.filter;

import com.gnnu.zuulserver.feign.UserFeign;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter extends ZuulFilter {
    @Autowired
    private UserFeign userFeign;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String url = request.getRequestURL().toString();
        HttpServletResponse response = requestContext.getResponse();
        String token = request.getParameter("token");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (url.contains("/sso/login") || url.contains("/loginPage") || (!StringUtils.isEmpty(token) && userFeign.hasKey(token))) {
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(200);

        } else {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                response.sendRedirect("http://127.0.0.1:8080/loginPage?url=" + url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }
}
