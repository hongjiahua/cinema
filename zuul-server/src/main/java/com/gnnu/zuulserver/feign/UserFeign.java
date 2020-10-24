package com.gnnu.zuulserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sso-server", fallback = UserFeignFallBack.class)
public interface UserFeign {
    @RequestMapping(value = "/sso/hasKey", method = RequestMethod.GET)
    Boolean hasKey(@RequestParam("token") String token);

}
