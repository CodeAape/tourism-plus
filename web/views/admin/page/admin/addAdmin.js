var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage;
    $ = layui.jquery;

    var addAdminArray = [], addAdmin;
    form.on("submit(addAdmin)", function (data) {
        //是否添加过信息
        if (window.sessionStorage.getItem("addAdmin")) {
            addAdminArray = JSON.parse(window.sessionStorage.getItem("addAdmin"));
        }

        addAdmin = '{"adminId":"' + new Date().getTime() + '",';//id
        addAdmin += '"adminName":"' + $(".adminName").val() + '",';  //登录名
        addAdmin += '"nickName":"' + $(".nickName").val() + '",';  //昵称
        addAdmin += '"password":"' + $(".password").val() + '"}';  //登录密码
        console.log(addAdmin);
        addAdminArray.unshift(JSON.parse(addAdmin));
        window.sessionStorage.setItem("addAdmin", JSON.stringify(addAdminArray));
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("成功添加管理员");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 2000);
        return false;
    })

})

//格式化时间 函数
function formatTime(_time) {
    var year = _time.getFullYear();
    var month = _time.getMonth() + 1 < 10 ? "0" + (_time.getMonth() + 1) : _time.getMonth() + 1;
    var day = _time.getDate() < 10 ? "0" + _time.getDate() : _time.getDate();
    var hour = _time.getHours() < 10 ? "0" + _time.getHours() : _time.getHours();
    var minute = _time.getMinutes() < 10 ? "0" + _time.getMinutes() : _time.getMinutes();
    return year + "-" + month + "-" + day + " " + hour + ":" + minute;
}
