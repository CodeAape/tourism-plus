layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var adminData = '';
    $.get("../../json/adminList.json", function (data) {
        adminData = data;
        if (window.sessionStorage.getItem("addAdmin")) {
            var addAdmin = window.sessionStorage.getItem("addAdmin");
            adminData = JSON.parse(addAdmin).concat(adminData);
        }
        //执行加载数据的方法
        adminList();
    })

    //添加管理员
    $(".adminAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加用户",
            type: 2,
            content: "addAdmin.html",
            success: function (layero, index) {
                layui.layer.tips('点击此处返回管理员列表', '.layui-layer-setwin .layui-layer-close', {
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
    $("body").on("click", ".admin_edit", function () {  //编辑
        layer.alert('更改用户资料功能为完善，敬请期待～', {icon: 6, title: '文章编辑'});
    })

    $("body").on("click", ".admin_del", function () {  //删除
        var _this = $(this);
        layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
            //_this.parents("tr").remove();
            for (var i = 0; i < adminData.length; i++) {
                if (adminData[i].adminId == _this.attr("data-id")) {
                    adminData.splice(i, 1);
                    adminList(adminData);
                }
            }
            layer.close(index);
        });
    })

    function adminList() {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            currData = adminData.concat().splice(curr * nums - nums, nums);
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                        + '<td>' + currData[i].adminId + '</td>'
                        + '<td>' + currData[i].adminName + '</td>'
                        + '<td>' + currData[i].nickName + '</td>'
                        + '<td>' + currData[i].lastLogin + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini admin_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini admin_del" data-id="' + data[i].adminId + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
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
            pages: Math.ceil(adminData.length / nums),
            jump: function (obj) {
                $(".admin_content").html(renderDate(adminData, obj.curr));
                $('.admin_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }

})