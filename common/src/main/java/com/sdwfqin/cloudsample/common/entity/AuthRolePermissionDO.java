package com.sdwfqin.cloudsample.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("auth_role_permission")
@ApiModel(value="AuthRolePermissionDO对象", description="")
public class AuthRolePermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("premission_id")
    private Long premissionId;


}
