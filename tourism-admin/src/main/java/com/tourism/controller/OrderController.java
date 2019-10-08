package com.tourism.controller;

import com.tourism.service.IOrderService;
import com.tourism.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author
 * @date
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService iOrderService;

    /**
     * 订单数量
     *
     * @return
     */
    @RequestMapping("/orderNum")
    @ResponseBody
    public JsonResult orderNum() {
        int osn = iOrderService.quireOrderNum();
        return new JsonResult(osn);
    }

    /**
     *展示商品信息,用户输入购买数量点击提交并发送请求到后台,
     * 后台根据信息(商品名称=订单名称,用户id,商品id,随机生成订单号)
     *
     * select * from goods where g_Id   点击购买,根据商品id查询商品信息回显
     *
     * 输入数量 合计处会根据数量进行相应计算  价格×数量 = 合计
     * 用户点击支付后,发送请求到后台
     * insert into order(o_id,o_name,o_num,user_id,goods_id)
     *      value(随机生成订单号,商品名称(订单名称),数量,用户id,商品id)
     */

    /**
     * 判断用户是否存在,存在则跳转到订单页面,不存在则跳转到登陆页面,登陆成功后跳转至商品页面
     * @param name
     * @param password
     * @return
     */
    /*@RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String name, String password) {

        currentuser = iUserService.findByUsername(name);
        if (currentuser == null || !password.equals(currentuser.getPassword())) {
            return new JsonResult(new SaslException("用户不存在"));
        } else {
            return new JsonResult("login ok");
        }
    }*/
}
