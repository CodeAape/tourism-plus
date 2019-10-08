package com.tourism.entity;

import lombok.Data;

@Data
public class SiteInfo {
    /**
     * 站点编号
     */
    private Integer id;

    /**
     * 站点标题
     */
    private String title;

    /**
     * 站点副标题
     */
    private String subtitle;

    /**
     * 站点地址
     */
    private String domain;

    /**
     * 站点LOGO地址
     */
    private String logo;

    /**
     * 站点描述
     */
    private String description;

    /**
     * 站点备案
     */
    private String icp;

    /**
     * 版权信息
     */
    private String copyright;
}