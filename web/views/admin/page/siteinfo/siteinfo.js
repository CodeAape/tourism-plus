layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    //形成
    var siteinfo;
    form.on("submit(siteinfo)", function (data) {
        siteinfo = '{"logo":"' + $(".logo").val() + '",';  //网站logo
        siteinfo = '"title":"' + $(".title").val() + '",';  //网站标题
        siteinfo += '"subTitle":"' + $(".subTitle").val() + '",';	 //副标题
        siteinfo += '"domain":"' + $(".domain").val() + '",'; //网站地址
        siteinfo += '"copyright":"' + $(".copyright").val() + '",'; //版权信息
        siteinfo += '"description":"' + $(".description").val() + '",'; //站点描述
        siteinfo += '"icp":"' + $(".icp").val() + '"},'; //网站备案号
        window.sessionStorage.setItem("siteinfo", siteinfo);
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            layer.msg("站点信息修改成功！");
        }, 2000);
        return false;
    })


    //加载默认数据
    if (window.sessionStorage.getItem("siteinfo")) {
        var data = JSON.parse(window.sessionStorage.getItem("siteinfo"));
        fillData(data);
    } else {
        //后台数据
        $.ajax({
            url: "../../json/siteinfo.json",
            type: "get",
            dataType: "json",
            success: function (data) {
                fillData(data);
            }
        })
    }

    //填充数据方法
    function fillData(data) {
        $(".logo").val(data.logo);      //logo
        $(".title").val(data.title);        //标题
        $(".subTitle").val(data.subTitle);        //副标题
        $(".domain").val(data.domain);    //网站地址
        $(".copyright").val(data.copyright);      //版权信息
        $(".description").val(data.description);//站点描述
        $(".icp").val(data.icp);      //网站备案号

    }

})
