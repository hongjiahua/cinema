package com.gnnu.zuulserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sso-server", fallback = UserFeignFallBack.class)
public interface UserFeign {
    @RequestMapping(value = "/sso/hasKey/{key}", method = RequestMethod.GET)
    Boolean hasKey(@PathVariable("key") String key);
}
