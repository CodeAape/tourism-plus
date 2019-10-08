layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var goodsData = '';
    $.get("../../json/goodsList.json", function (data) {
        var newArray = [];
        //单击首页“待审核文章”加载的信息
        if ($(".top_tab li.layui-this cite", parent.document).text() == "待审核文章") {
            if (window.sessionStorage.getItem("addNews")) {
                var addNews = window.sessionStorage.getItem("addNews");
                goodsData = JSON.parse(addNews).concat(data);
            } else {
                goodsData = data;
            }
            for (var i = 0; i < goodsData.length; i++) {
                if (goodsData[i].goodsStatus == "待审核") {
                    newArray.push(goodsData[i]);
                }
            }
            goodsData = newArray;
            goodsList(goodsData);
        } else {    //正常加载信息
            goodsData = data;
            if (window.sessionStorage.getItem("addNews")) {
                var addNews = window.sessionStorage.getItem("addNews");
                goodsData = JSON.parse(addNews).concat(goodsData);
            }
            //执行加载数据的方法
            goodsList();
        }
    })
    //添加文章
    $(".goodsAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加文章",
            type: 2,
            content: "goodsAdd.html",
            success: function (layero, index) {
                layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        })
        layui.layer.full(index);
    })


    //批量删除
    $(".batchDel").click(function () {
        var $checkbox = $('.goods_list tbody input[type="checkbox"][name="checked"]');
        var $checked = $('.goods_list tbody input[type="checkbox"][name="checked"]:checked');
        if ($checkbox.is(":checked")) {
            layer.confirm('确定删除选中的信息？', {icon: 3, title: '提示信息'}, function (index) {
                var index = layer.msg('删除中，请稍候', {icon: 16, time: false, shade: 0.8});
                setTimeout(function () {
                    //删除数据
                    for (var j = 0; j < $checked.length; j++) {
                        for (var i = 0; i < goodsData.length; i++) {
                            if (goodsData[i].goodsId == $checked.eq(j).parents("tr").find(".goods_del").attr("data-id")) {
                                goodsData.splice(i, 1);
                                goodsList(goodsData);
                            }
                        }
                    }
                    $('.goods_list thead input[type="checkbox"]').prop("checked", false);
                    form.render();
                    layer.close(index);
                    layer.msg("删除成功");
                }, 2000);
            })
        } else {
            layer.msg("请选择需要删除的文章");
        }
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

    //是否展示
    form.on('switch(isShow)', function (data) {
        var index = layer.msg('修改中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            layer.msg("展示状态修改成功！");
        }, 2000);
    })

    //操作
    $("body").on("click", ".goods_edit", function () {  //编辑
        layer.alert('功能正开发中～', {icon: 6, title: '文章编辑'});
    })


    $("body").on("click", ".goods_del", function () {  //删除
        var _this = $(this);
        layer.confirm('确定删除此信息？', {icon: 3, title: '提示信息'}, function (index) {
            //_this.parents("tr").remove();
            for (var i = 0; i < goodsData.length; i++) {
                if (goodsData[i].goodsId == _this.attr("data-id")) {
                    goodsData.splice(i, 1);
                    goodsList(goodsData);
                }
            }
            layer.close(index);
        });
    })

    function goodsList(that) {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            if (!that) {
                currData = goodsData.concat().splice(curr * nums - nums, nums);
            } else {
                currData = that.concat().splice(curr * nums - nums, nums);
            }
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                        + '<td align="left">' + currData[i].title + '</td>'
                        + '<td>' + currData[i].new_price + '</td>';
                    dataHtml += '<td>' + currData[i].goodNum + '</td>'
                        + '<td>' + currData[i].go_address + '</td>'
                        + '<td>' + currData[i].describe + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini goods_edit"><i class="iconfont icon-edit"></i> 编辑</a>'

                        + '<a class="layui-btn layui-btn-danger layui-btn-mini goods_del" data-id="' + data[i].goodsId + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
                        + '</td>'
                        + '</tr>';
                }
            } else {
                dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
            }
            return dataHtml;
        }

        //分页
        var nums = 13; //每页出现的数据量
        if (that) {
            goodsData = that;
        }
        laypage({
            cont: "page",
            pages: Math.ceil(goodsData.length / nums),
            jump: function (obj) {
                $(".goods_content").html(renderDate(goodsData, obj.curr));
                $('.goods_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }
})
