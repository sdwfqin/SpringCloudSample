package com.sdwfqin.cloudsample.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张钦
 * @since 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_user")
@ApiModel(value="AuthUserDO对象", description="")
public class AuthUserDO implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "手机号码")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty(value = "性别，0未知，1男，2女")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "是否启用")
    @TableField("is_enable")
    private Boolean isEnable;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private List<AuthRoleDO> authRoles = new ArrayList<>();

    @TableField(exist = false)
    private List<String> permissions = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleString = getCommaSeparatedRoleString();
        //返回用户角色即可
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roleString);
    }

    /**
     * 获取逗号分割的permission
     */
    private String getCommaSeparatedRoleString() {
        StringBuffer stringBuffer = new StringBuffer();
        permissions.forEach(permission -> {
            stringBuffer.append(permission).append(",");
        });
        if (StringUtils.isNotBlank(stringBuffer)) {
            return stringBuffer.substring(0, stringBuffer.length() - 1);
        } else {
            return "";
        }
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
