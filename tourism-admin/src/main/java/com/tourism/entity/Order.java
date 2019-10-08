package com.tourism.entity;

import lombok.Data;

@Data
public class Order {
    /**
     * 订单ID
     */
    private Integer oId;

    /**
     * 订单名称
     */
    private String oName;

    /**
     * 订单数量
     */
    private Integer oNum;

    /**
     * 关联用户ID
     */
    private Integer userId;

    /**
     * 关联商品ID
     */
    private Integer goodsId;

    /**
     * 1存在 ，0删除
     */
    private Integer isDelete;
}