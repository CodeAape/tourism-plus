package com.tourism.utils;

import com.tourism.entity.SiteInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Demo class
 *
 * @author
 * @date
 */
@Component
public class UploadFile {
    /**
     * 上传文章,将markdown解析成html
     */
    public SiteInfo uploadFile(@ModelAttribute("siteInfo") SiteInfo siteInfo, MultipartFile file, HttpServletRequest request) throws IOException {
        //限制上传类型
        if ("image/png".equals(file.getContentType()) || "image/jpg".equals(file.getContentType())) {
            //判断用户是否上传文件
            if (!file.isEmpty()) {
                //上传位置
                String path = request.getSession().getServletContext().getRealPath("/uploads");
                //判断路径是否存在
                File files = new File(path);
                if (!files.exists()) {
                    //创建文件夹
                    files.mkdirs();
                }
                //获取上传文件的名称
                String filename = file.getOriginalFilename();
                //把文件的名称设置唯一值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                siteInfo.setLogo(filename);
                //完成
                file.transferTo(new File(path, filename));
                return siteInfo;
            }
        }
        return siteInfo;
    }
}
