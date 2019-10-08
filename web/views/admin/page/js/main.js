layui.config({
    base: "js/"
}).use(['form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element(),
        $ = layui.jquery;

    $(".panel a").on("click", function () {
        window.parent.addTab($(this));
    });
    //订单数量
    $.get("../json/orderList.json",
        function (data) {
            $(".orderAll span").text(data.length);
        }
    );

    //用户数量
    $.get("../json/usersList.json",
        function (data) {
            $(".userAll span").text(data.length);
        }
    );
    //商品数量
    $.get("../json/goodsList.json",
        function (data) {
            $(".goodsAll span").text(data.length);
        }
    );
    //动态获取文章总数和待审核文章数量,最新文章
    $.ajax({
        "url": "http://localhost:8080/admin/all",
        "type": "get",
        success: function (list) {
            if (list.data != null) {
                var data = list.data;
                $(".allArticle span").text(data.length);
                //加载最新文章
                var hotArticleHtml = '';
                /*for (var j = 0; j < 5; j++) {*/
                for (var i in data) {
                    hotArticleHtml += '<tr>'
                        + '<td align="left">' + data[i].title + '</td>'
                        + '<td>' + data[i].time + '</td>'
                        + '</tr>';
                }
                $(".hot_article").html(hotArticleHtml);
                /*}*/
            }
        },
        "dataType": "json"
    });


    //数字格式化
    $(".panel span").each(function () {
        $(this).html($(this).text() > 9999 ? ($(this).text() / 10000).toFixed(2) + "<em>万</em>" : $(this).text());
    })

    /*  //系统基本参数
      if (window.sessionStorage.getItem("siteinfo")) {
          var siteinfo = JSON.parse(window.sessionStorage.getItem("siteinfo"));
          fillParameter(siteinfo);
      } else {
          $.ajax({
              url: "../json/siteinfo.json",
              type: "get",
              dataType: "json",
              success: function (data) {
                  fillParameter(data);
              }
          })
      }*/

});