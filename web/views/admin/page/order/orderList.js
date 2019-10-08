layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var orderData = '';
    $.get("../../json/orderList.json", function (data) {
        orderData = data;
        if (window.sessionStorage.getItem("addOrder")) {
            var addOrder = window.sessionStorage.getItem("addOrder");
            orderData = JSON.parse(addOrder).concat(orderData);
        }
        //执行加载数据的方法
        orderList();
    })


    //全选
    form.on('checkbox(allChoose)', function (data) {
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        child.each(function (index, item) {
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });

    //通过判断文章是否全部选中来确定全选按钮是否选中
    form.on("checkbox(choose)", function (data) {
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
        var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
        if (childChecked.length == child.length) {
            $(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
        } else {
            $(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
        }
        form.render('checkbox');
    })

    //操作
    $("body").on("click", ".order_edit", function () {  //编辑
        layer.alert('打印功能开发中，敬请期待～', {icon: 3, title: '打印'});
    })

    $("body").on("click", ".order_del", function () {  //删除
        var _this = $(this);
        layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
            //_this.parents("tr").remove();
            for (var i = 0; i < orderData.length; i++) {
                if (orderData[i].orderId == _this.attr("data-id")) {
                    orderData.splice(i, 1);
                    orderList(orderData);
                }
            }
            layer.close(index);
        });
    })

    function orderList() {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            currData = orderData.concat().splice(curr * nums - nums, nums);
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                        + '<td>' + currData[i].orderName + '</td>'
                        + '<td>' + currData[i].orderId + '</td>'
                        + '<td>' + currData[i].orderNum + '</td>'
                        + '<td>' + currData[i].orderUser + '</td>'
                        + '<td>' + currData[i].orderGoods + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini order_edit"><i class="iconfont icon-text"></i> 打印</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini order_del" data-id="' + data[i].orderId + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
                        + '</td>'
                        + '</tr>';
                }
            } else {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 5; //每页出现的数据量
        laypage({
            cont: "page",
            pages: Math.ceil(orderData.length / nums),
            jump: function (obj) {
                $(".order_content").html(renderDate(orderData, obj.curr));
                $('.order_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }

})