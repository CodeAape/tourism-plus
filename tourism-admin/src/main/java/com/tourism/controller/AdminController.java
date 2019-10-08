package com.tourism.controller;

import com.alibaba.fastjson.JSONObject;
import com.tourism.entity.Admin;
import com.tourism.entity.Article;
import com.tourism.entity.Goods;
import com.tourism.entity.User;
import com.tourism.service.IAdminService;
import com.tourism.service.IArticleService;
import com.tourism.service.IGoodsService;
import com.tourism.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private HttpServletRequest request;
    @Resource
    private IAdminService iAdminService;
    @Resource
    private IUserService iUserService;
    @Resource
    private IGoodsService iGoodsService;
    @Resource
    private IArticleService iArticleService;


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(@RequestBody(required = false) Admin admin) {
        String info;
        System.out.println(admin);
        if ("admin".equals(admin.getUsername()) && "admin".equals(admin.getPassword())) {
            info = "1";
        } else {
            info = "0";
        }
        return info;

    }

    //添加管理员
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String register(String username, String nickname, String password) {
        Admin admin = new Admin(username, nickname, password);
        int i = iAdminService.insertSelective(admin);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    //修改管理员密码
    @RequestMapping(value = "/updateKey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateKeyByName(String username, String oldPwd, String Password) {
        int i = 0;
        if (iAdminService.findByUP(username, oldPwd) != 0) {
            //名字找id
            int id = iAdminService.idByName(username);
            Admin admin = new Admin(id, Password);
            i = iAdminService.updateByPrimaryKeySelective(admin);
        }
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    //删除管理员
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void deleteAdminById(int id) {
        int i = iAdminService.deleteByPrimaryKey(id);
    }

    //批量删除管理员
    @RequestMapping(value = "/MuchDeleteAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void MuchDeleteAdmin(int[] a) {
        int i1 = 0;
        for (int i = 0; i < a.length; i++) {
            i1 = iAdminService.deleteByPrimaryKey(a[i]);
        }
    }

    //删除用户
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void deleteUserById(int id) {
        iUserService.deleteByPrimaryKey(id);
    }

    //批量删除用户
    @RequestMapping(value = "/MuchDeleteUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void MuchDeleteUser(int[] a) {
        int i1 = 0;
        for (int i = 0; i < a.length; i++) {
            i1 = iAdminService.deleteByPrimaryKey(a[i]);
        }
    }

    //添加用户
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void addUser
    (String userName, String nickName, String password, String userEmail, String phone, String userSex, String describe) {
        User user = new User(userName, nickName, password, userSex, userEmail, phone, describe);
        int i = iUserService.insertSelective(user);
    }

    //查看用户资料
    @RequestMapping(value = "/showUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String showUser(int id) {
        User user = iUserService.selectByPrimaryKey(id);
        String s = JSONObject.toJSONString(user);
        if (user != null) {
            return s;
        } else {
            return null;
        }
    }
    //商品添加

    @RequestMapping(value = "/addGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody

    public String addGoods(@RequestParam("title") String title, @RequestParam("name") String name,
                           @RequestParam("address") String address, @RequestParam("goAddress") String goAddress,
                           @RequestParam("price") BigDecimal price, @RequestParam("goods") Integer goodsNum,
                           @RequestParam("newPrice") String newPrice, @RequestParam("describe") String describe,
                           @RequestParam("longTime") String longTime, @RequestParam("date") Date date) {
        Goods goods = new Goods(title, name, address, goAddress, price, goodsNum, newPrice, describe, longTime, date);
        int i = iGoodsService.insertSelective(goods);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    //商品删除（假）
    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String deleteGoods(int id) {
        Goods goods = new Goods(id, 0);
        int i = iGoodsService.updateByPrimaryKeySelective(goods);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    //商品修改
    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateGoods(@RequestParam("title") String title, @RequestParam("name") String name,
                              @RequestParam("address") String address, @RequestParam("goAddress") String goAddress,
                              @RequestParam("price") BigDecimal price, @RequestParam("goods") Integer goodsNum,
                              @RequestParam("newPrice") String newPrice, @RequestParam("describe") String describe,
                              @RequestParam("longTime") String longTime, @RequestParam("date") Date date) {
        Goods goods = new Goods(title, name, address, goAddress, price, goodsNum, newPrice, describe, longTime, date);

        int i = iGoodsService.updateByPrimaryKeySelective(goods);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    //单个商品查询
    @RequestMapping(value = "/selectGoodsById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String selectGoodsById(int id) {
        Goods goods = iGoodsService.selectByPrimaryKey(id);
        String s = JSONObject.toJSONString(goods);
        if (goods != null) {
            return s;
        } else {
            return null;
        }
    }

    //全部商品查询
    @RequestMapping(value = "/selectGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String selectGoods() {
        List list = iGoodsService.selectList();
        String s = JSONObject.toJSONString(list);
        if (list != null) {
            return s;
        } else {
            return null;
        }
    }

    //修改个人资料
    @RequestMapping(value = "/changeAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String changeAdmin(String nickname) {
        Admin admin = new Admin(nickname);
        int i = iAdminService.updateByPrimaryKeySelective(admin);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    // 增加文章
    @RequestMapping(value = "/insertArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String insertArticle(Article article) {
        int i = iArticleService.insert(article);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    // 删除文章
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String deleteArticle(int id) {
        int i = iArticleService.deleteByPrimaryKey(id);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    // 修改文章
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateArticle(Article article) {
        int i = iArticleService.updateByPrimaryKey(article);
        if (i > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    // 查询一条文章
    @RequestMapping(value = "/selectArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Article selectArticle(int articleId) {
        Article article = iArticleService.selectByPrimaryKey(articleId);
        return article;
    }

    // 查询所有文章
    @RequestMapping(value = "/selectAllArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List selectAllArticle(int pagestart, int pageend) {
        List<Article> list = iArticleService.selectByList(pagestart, pageend);
        return list;
    }


}
