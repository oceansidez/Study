package com.study.spring.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <h1>账户表实体类定义</h1>
 * */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@TableName("t_ecommerce_account")
public class EcommerceAccount implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /** 用于乐观锁的版本控制字段 */
    @Version
    private Integer version;
    @TableLogic(value = "0", delval = "1")
    private Boolean deleted = Boolean.FALSE;

    private Long userId;
    private String username;
    private String password;
    private String extraInfo;
}
