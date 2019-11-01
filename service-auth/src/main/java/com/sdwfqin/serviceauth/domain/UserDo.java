package com.sdwfqin.serviceauth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class UserDo implements UserDetails, Serializable {

    private Long id;

    private String username;

    private String password;

    private List<RoleDo> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 过期性 :true:没过期 false:过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 锁定性 :true:未锁定 false:已锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 有效性 :true:凭证有效 false:凭证无效
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 可用性 :true:可用 false:不可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
