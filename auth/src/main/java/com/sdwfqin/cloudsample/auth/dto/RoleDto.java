package com.sdwfqin.cloudsample.auth.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色表
 * <p>
 *
 * @author 张钦
 * @date 2020/6/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "角色Vo", description = "用户角色信息")
@TableName("sys_role")
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id，只有修改时需要传")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "权限清单")
    private Long[] permissions;


}
