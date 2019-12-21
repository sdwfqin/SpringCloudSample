package com.sdwfqin.serviceauth.controller;

import com.sdwfqin.common.result.Result;
import com.sdwfqin.common.result.ResultEnum;
import com.sdwfqin.common.result.ResultUtils;
import com.sdwfqin.serviceauth.domain.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

/**
 * 自定义认证返回值
 * <p>
 *
 * @author 张钦
 * @date 2019/12/10
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @GetMapping("/token")
    public Result getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @PostMapping("/token")
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    /**
     * 自定义返回格式
     *
     * @param accessToken
     * @return
     */
    private Result custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> data = new LinkedHashMap(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        data.put("tokenType", token.getTokenType());

        OAuth2Authentication oAuth2Authentication = resourceServerTokenServices.loadAuthentication(token.getValue());
        UserDo userDo = (UserDo) oAuth2Authentication.getPrincipal();
        Map<String, Object> user = new LinkedHashMap<>();
        user.put("id", userDo.getId());
        user.put("username", userDo.getUsername());
        user.put("nickName", userDo.getNickName());
        data.put("user", user);

        List<String> authorities = new ArrayList<>();
        userDo.getAuthorities().forEach(o -> {
            authorities.add(o.getAuthority());
        });
        data.put("authorities", authorities);

        return ResultUtils.success(data);
    }

    @ExceptionHandler({InvalidGrantException.class})
    public Result grantExceptionHandler() {
        return ResultUtils.errorData(ResultEnum.LOGIN_ERROR);
    }

    @ExceptionHandler({InvalidScopeException.class, UnsupportedGrantTypeException.class})
    public Result validExceptionHandler() {
        return ResultUtils.errorData(ResultEnum.VALID_ERROR);
    }
}
