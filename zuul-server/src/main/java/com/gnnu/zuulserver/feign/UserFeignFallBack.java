package com.gnnu.zuulserver.feign;

import org.springframework.stereotype.Component;

@Component
public class UserFeignFallBack implements UserFeign {
    @Override
    public Boolean hasKey(String key) {
        System.out.println("sso远程服务调用失败！");
        return false;
    }
}
