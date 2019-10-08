package com.tourism.controller;

import com.tourism.service.IUserService;
import com.tourism.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    @RequestMapping("/userNum")
    @ResponseBody
    public JsonResult userNum() {
        int usn = iUserService.userNum();
        return new JsonResult(usn);
    }
}
