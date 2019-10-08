layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'laypage'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    //加载页面数据
    var usersData = '';
    $.get("../../json/usersList.json", function (data) {
        usersData = data;
        if (window.sessionStorage.getItem("addUser")) {
            var addUsers = window.sessionStorage.getItem("addUser");
            usersData = JSON.parse(addUsers).concat(usersData);
        }
        //执行加载数据的方法
        usersList();
    })
    //添加会员
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加用户",
            type: 2,
            content: "addUser.html",
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
    $("body").on("click", ".users_edit", function () {  //编辑
        layer.alert('更改用户资料功能为完善，敬请期待～', {icon: 6, title: '文章编辑'});
    })

    $("body").on("click", ".users_del", function () {  //删除
        var _this = $(this);
        layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
            //_this.parents("tr").remove();
            for (var i = 0; i < usersData.length; i++) {
                if (usersData[i].usersId == _this.attr("data-id")) {
                    usersData.splice(i, 1);
                    usersList(usersData);
                }
            }
            layer.close(index);
        });
    })

    function usersList() {
        //渲染数据
        function renderDate(data, curr) {
            var dataHtml = '';
            currData = usersData.concat().splice(curr * nums - nums, nums);
            if (currData.length != 0) {
                for (var i = 0; i < currData.length; i++) {
                    dataHtml += '<tr>'
                        + '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
                        + '<td>' + currData[i].userName + '</td>'
                        + '<td>' + currData[i].nickName + '</td>'
                        + '<td>' + currData[i].userEmail + '</td>'
                        + '<td>' + currData[i].userSex + '</td>'
                        + '<td>' + currData[i].address + '</td>'
                        + '<td>'
                        + '<a class="layui-btn layui-btn-mini layui-btn-normal msg_collect"><i class="layui-icon">&#xe62a;</i> 查看</a>'
                        + '<a class="layui-btn layui-btn-mini users_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
                        + '<a class="layui-btn layui-btn-danger layui-btn-mini users_del" data-id="' + data[i].usersId + '"><i class="layui-icon">&#xe640;</i> 删除</a>'
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
        laypage({
            cont: "page",
            pages: Math.ceil(usersData.length / nums),
            jump: function (obj) {
                $(".users_content").html(renderDate(usersData, obj.curr));
                $('.users_list thead input[type="checkbox"]').prop("checked", false);
                form.render();
            }
        })
    }

})