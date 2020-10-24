package com.gnnu.zuulserver.filter;

import com.gnnu.zuulserver.feign.UserFeign;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AccessFilter extends ZuulFilter {
    @Autowired
    private UserFeign userFeign;

    @Autowired
    private RedisTemplate redisTemplate;

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
        StringBuilder url = new StringBuilder(request.getRequestURL().toString());
        HttpServletResponse response = requestContext.getResponse();
        String token = request.getParameter("token");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    System.out.println(token);
                }
            }
        }
        System.out.println(token);
        if (url.toString().contains("/sso/login") || url.toString().contains("/loginPage") || url.toString().contains("/registerPage") || url.toString().contains("/user/register") || url.toString().contains("/sso/hasKey") || (!StringUtils.isEmpty(token) && userFeign.hasKey(token))) {
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(200);


        } else {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                System.out.println("重定向");
                response.sendRedirect("http://127.0.0.1:8080/loginPage?url=" + url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }
}
