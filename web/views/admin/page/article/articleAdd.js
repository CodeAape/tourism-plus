layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'layedit', 'laydate'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //创建一个编辑器
    var editIndex = layedit.build('article_content');
    var addarticleArray = [], addarticle;
    form.on("submit(addarticle)", function (data) {
        //是否添加过信息
        if (window.sessionStorage.getItem("addarticle")) {
            addarticleArray = JSON.parse(window.sessionStorage.getItem("addarticle"));
        }
        addarticle = '{"title":"' + $(".title").val() + '",';  //文章名称
        addarticle += '"articleId":"' + new Date().getTime() + '",';	 //文章id
        addarticle += '"time":"' + $(".time").val() + '",'; //发布时间
        addarticle += '"author":"' + $(".author").val() + '",'; //文章作者
        addarticle += '"isDelete":"' + isDelete + '",';  //是否展示
        addarticleArray.unshift(JSON.parse(addarticle));
        window.sessionStorage.setItem("addarticle", JSON.stringify(addarticleArray));
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("文章添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 2000);
        return false;
    })

})
