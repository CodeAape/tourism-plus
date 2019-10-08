package com.tourism.controller;

import com.tourism.entity.SiteInfo;
import com.tourism.service.ISiteInfoService;
import com.tourism.utils.JsonResult;
import com.tourism.utils.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author
 * @date
 */
@Controller()
@RequestMapping("/siteInfo")
@Slf4j
public class SiteInfoController {

    @Resource
    private ISiteInfoService iSiteInfoService;

    @Resource
    private UploadFile uploadFile;

    @RequestMapping("/toInfo")
    public String toInfo() {
        return "siteInfo";
    }


    @RequestMapping("/quireInfo")
    @ResponseBody
    public JsonResult quireInfo() {
        SiteInfo siteInfo = iSiteInfoService.quireSiteInfo();
        return new JsonResult(siteInfo);
    }

    @RequestMapping("/submitInfo")
    public String submitInfo(@ModelAttribute("siteInfo") SiteInfo siteInfo, MultipartFile file, HttpServletRequest request) throws IOException {
        SiteInfo site = uploadFile.uploadFile(siteInfo, file, request);
        iSiteInfoService.updateByPrimaryKey(site);
        return "siteInfo";
    }

}
