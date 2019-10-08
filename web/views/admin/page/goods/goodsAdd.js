var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage;
    $ = layui.jquery;

    var addGoodsArray = [], addGoods;
    form.on("submit(addGoods)", function (data) {
        //是否添加过信息
        if (window.sessionStorage.getItem("addGoods")) {
            addGoodsArray = JSON.parse(window.sessionStorage.getItem("addGoods"));
        }
        addGoods += '{"title":"' + $(".title").val() + '",';
        // addGoods += '"pic":"' + $(".pic").val() + '",';  //
        addGoods += '"name":"' + $(".name").val() + '",';  //
        addGoods += '"goodNum":"' + $(".goodNum").val() + '",';
        addGoods += '"address":"' + $(".address").val() + '",';  //
        addGoods += '"goAddress":"' + $(".goAddress").val() + '",';  //
        addGoods += '"longTime":"' + $(".longTime").val() + '",';  //
        addGoods += '"price":"' + $(".price").val() + '",';  //
        addGoods += '"newPrice":"' + $(".newPrice").val() + '",';  //
        addGoods += '"describe":"' + $(".describe").val() + '",';  //
        addGoods += '"date":"' + formatTime(new Date()) + '"}';  //
        console.log(addGoods);
        addGoodsArray.unshift(JSON.parse(addGoods));
        window.sessionStorage.setItem("addGoods", JSON.stringify(addGoodsArray));
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("成功添加商品！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 2000);
        return false;
    })

})

//格式化时间
function formatTime(_time) {
    var year = _time.getFullYear();
    var month = _time.getMonth() + 1 < 10 ? "0" + (_time.getMonth() + 1) : _time.getMonth() + 1;
    var day = _time.getDate() < 10 ? "0" + _time.getDate() : _time.getDate();
    var hour = _time.getHours() < 10 ? "0" + _time.getHours() : _time.getHours();
    var minute = _time.getMinutes() < 10 ? "0" + _time.getMinutes() : _time.getMinutes();
    return year + "-" + month + "-" + day + " " + hour + ":" + minute;
}

//加载省数据
function loadProvince() {
    var proHtml = '';
    for (var i = 0; i < areaData.length; i++) {
        proHtml += '<option value="' + areaData[i].provinceCode + '_' + areaData[i].mallCityList.length + '_' + i + '">' + areaData[i].provinceName + '</option>';
    }
    //初始化省数据
    $form.find('select[name=province]').append(proHtml);
    form.render();
    form.on('select(province)', function (data) {
        $form.find('select[name=area]').html('<option value="">请选择县/区</option>');
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadCity(areaData[index].mallCityList);
        } else {
            $form.find('select[name=city]').attr("disabled", "disabled");
        }
    });
}

//加载市数据
function loadCity(citys) {
    var cityHtml = '<option value="">请选择市</option>';
    for (var i = 0; i < citys.length; i++) {
        cityHtml += '<option value="' + citys[i].cityCode + '_' + citys[i].mallAreaList.length + '_' + i + '">' + citys[i].cityName + '</option>';
    }
    $form.find('select[name=city]').html(cityHtml).removeAttr("disabled");
    form.render();
    form.on('select(city)', function (data) {
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadArea(citys[index].mallAreaList);
        } else {
            $form.find('select[name=area]').attr("disabled", "disabled");
        }
    });
}

//加载县/区数据
function loadArea(areas) {
    var areaHtml = '<option value="">请选择县/区</option>';
    for (var i = 0; i < areas.length; i++) {
        areaHtml += '<option value="' + areas[i].areaCode + '">' + areas[i].areaName + '</option>';
    }
    $form.find('select[name=area]').html(areaHtml).removeAttr("disabled");
    form.render();
    form.on('select(area)', function (data) {
        //console.log(data);
    });
}