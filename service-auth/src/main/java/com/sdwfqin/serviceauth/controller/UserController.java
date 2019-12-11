package com.sdwfqin.serviceauth.controller;

import com.sdwfqin.common.exception.ServiceException;
import com.sdwfqin.common.result.Result;
import com.sdwfqin.common.result.ResultEnum;
import com.sdwfqin.common.result.ResultUtils;
import com.sdwfqin.serviceauth.domain.UserDo;
import com.sdwfqin.serviceauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(principal.toString());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    @GetMapping("/userInfo")
    public Result getUserInfo() {
        UserDo userDo = (UserDo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", userDo.getId());
        map.put("username", userDo.getUsername());
        map.put("nickName", userDo.getNickName());
        return ResultUtils.success(map);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    public Result exit(HttpServletRequest request) throws Exception {

        consumerTokenServices.revokeToken(request.getHeader("Authorization").substring(7));

        return ResultUtils.success();
    }

    @PostMapping("/register")
    public Result register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("roleIds") Long[] roleIds) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ServiceException(ResultEnum.REGISTER_VALID_USER_ERROR);
        }

        if (roleIds == null || roleIds.length < 1) {
            throw new ServiceException(ResultEnum.REGISTER_VALID_ROLE_ERROR);
        }

        UserDo userDo = new UserDo();
        userDo.setUsername(username);
        userDo.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.save(userDo, roleIds);

        return ResultUtils.success();
    }
}