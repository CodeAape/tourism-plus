package com.tourism.controller;

import com.tourism.entity.Article;
import com.tourism.service.IArticleService;
import com.tourism.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 未判断当前用户是否登陆,权限未设置,
 *
 * @author
 * @date
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private IArticleService iArticleService;

    @RequestMapping(value = "/art")
    public String art() {
        return "/views/article.html";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "addArticle";
    }

    @RequestMapping(value = "/update")
    public String update() {
        return "updateArticle";
    }

    // 查询所有文章（不分页）
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Article> all(HttpServletRequest request) {
        List<Article> list = iArticleService.list();
        for (Article a : list
        ) {
            System.out.println(a);

        }
        return list;

    }


    /**
     * 根据文章id删除对应文章,局部刷新article.html页面
     * return "article"; 通过视图解析器找到相应的页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteArticle")
    public String deleteArticle(int id) {
        iArticleService.deleteByPrimaryKey(id);
        return "/views/article.html";
    }

    /**
     * 文章的增加 ajax提交数据到后台
     *
     * @param article
     * @return 点击<a href = " add ">添加</a> 跳转到 addArticle.html
     * <p>
     * No converter found for return value of type: class java.lang.Integer
     * 未找到类型为java.lang.Integer的返回值的转换器
     * int 返回数据 习惯性错误
     * <p>
     * int count = iArticleService.insert(article);
     * return String.valueOf(count) ;
     * <p>
     * iArticleService.insert(article);
     * return new JsonResult("OK");
     * <p>
     * 均可使用,二选一
     */
    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    @ResponseBody
    public String insertArticle(Article article) {
        int count = iArticleService.insert(article);
        return String.valueOf(count);
    }

    /**
     * 查询文章总数
     *
     * @return
     */
    @RequestMapping("/selectPage")
    @ResponseBody
    public JsonResult selectPage() {
        Integer page = iArticleService.selectPage();
        return new JsonResult(page);
    }

    @RequestMapping("/unPageList")
    @ResponseBody
    public JsonResult unPageList() {
        List<Article> unPageList = null;
        unPageList = iArticleService.selectUnPage();
        return new JsonResult(unPageList);
    }

    /**
     * 分页查询展示数据
     *
     * @param pageStart
     * @param pageEnd
     * @return 前端判断 if(list.data!=null)
     * <p>
     * return new JsonResult(articleList);  取决于是否为空
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResult articleList(int pageStart, int pageEnd) {
        List<Article> articleList = null;
        // if (currentuser != null){
        articleList = iArticleService.selectByList(pageStart, pageEnd);
        return new JsonResult(articleList);
        //}
        // return new JsonResult(articleList);
    }

    /**
     * 根据文章id查询文章信息并展示到修改页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/selectById")
    @ResponseBody
    public JsonResult selectById(Integer id) {
        Article article = iArticleService.selectByPrimaryKey(id);
        return new JsonResult(article);
    }

    /**
     * 修改页面   修改文章信息,ajax提交数据到后台进行修改
     * "OK" == 200  HTTP状态码 已建立连接  state
     *
     * @param article
     * @return
     */
    @RequestMapping(value = "/updateArticle", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updateArticle(Article article) {
        iArticleService.updateByPrimaryKey(article);
        return new JsonResult("OK");
    }
}
