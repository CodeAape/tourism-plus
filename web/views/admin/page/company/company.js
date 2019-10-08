layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    var systemParameter;
    form.on("submit(systemParameter)", function (data) {
        systemParameter = '{"name":"' + $(".name").val() + '",';  //企业名称
        systemParameter = '{"address":"' + $(".address").val() + '",';  //地址
        systemParameter += '"postcode":"' + $(".postcode").val() + '",';	 //邮政编码
        systemParameter += '"contact":"' + $(".contact").val() + '",'; //联系人
        systemParameter += '"mobile":"' + $(".mobile").val() + '",'; //手机
        systemParameter += '"phone":"' + $(".phone").val() + '",'; //办公电话
        systemParameter += '"fax":"' + $(".fax").val() + '",'; //传真
        systemParameter += '"email":"' + $(".email").val() + '",'; //电子邮箱
        systemParameter += '"qq":"' + $(".qq").val() + '",'; //QQ号码
        window.sessionStorage.setItem("systemParameter", systemParameter);
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            layer.msg("企业信息修改成功！");
        }, 2000);
        return false;
    })


    //加载默认数据
    if (window.sessionStorage.getItem("company")) {
        var data = JSON.parse(window.sessionStorage.getItem("company"));
        fillData(data);
    } else {
        $.ajax({
            url: "../../json/company.json",
            type: "get",
            dataType: "json",
            success: function (data) {
                fillData(data);
            }
        })
    }

    //填充数据方法
    function fillData(data) {
        $(".name").val(data.name);  //企业名称
        $(".address").val(data.address);  //地址
        $(".postcode").val(data.postcode);	 //邮政编码
        $(".contact").val(data.contact); //联系人
        $(".mobile").val(data.mobile); //手机
        $(".phone").val(data.phone); //办公电话
        $(".fax").val(data.fax); //传真
        $(".email").val(data.email); //电子邮箱
        $(".qq").val(data.qq); //QQ号码

    }

})
