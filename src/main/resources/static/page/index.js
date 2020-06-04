$(function(){
    layui.use(['element'],function(){
            element = layui.element;
        $.ajax({
            type: "POST",
            url: ctx+"index",
            success: function (data) {
                data = eval(data);
                $("#myname").text(data.extend.user.realName);
                console.log(data.extend.user.realName);
                navBuilder(data.extend.data);
                element.render();
            }
        });
        function navBuilder(data) {
            var $nav = $("<ul id=\"nav\"></ul>");
            $(data).each(function (i,n) {
                var $li1 = $("<li></li>");
                var $a1 = $("<a ></a>");
                var $i1_1 = $("<i class=\"layui-icon\"></i>");
                var $i1_2 = $("<i class=\"iconfont nav_right\"></i>");
                var $city1 = $("<cite></cite>");
                $a1.attr("_href",n.url);
                $i1_1.html(n.icon);
                $i1_2.html('&#xe697;');
                $city1.html(n.name);
                $a1.append($i1_1);
                $a1.append($city1);
                $a1.append($i1_2);
                $a1.appendTo($li1);

                if(n.children.length >  0){
                    var $nav2 = $("<ul class=\"sub-menu\"></ul>");
                    // navBuilder(n.children);

                    $(n.children).each(function (a,m) {
                        var $li2 = $("<li></li>");
                        var $a2 = $("<a></a>");
                        var $i2_1 = $("<i class=\"layui-icon\"></i>");
                        var $i2_2 = $("<i class=\"iconfont nav_right\"></i>");
                        var $city2 = $("<cite></cite>");
                        $a2.attr("_href",m.url);
                        $i2_1.html(m.icon);
                        $i2_2.html('&#xe697;');
                        $city2.html(m.name);

                        $a2.append($i2_1);
                        $a2.append($city2);
                        if(m.children.length >  0){
                            $a2.append($i2_2);
                        }
                        $a2.appendTo($li2);
                        if(m.children.length >  0){
                            var $nav3 = $("<ul class=\"sub-menu\"></ul>");
                            $(m.children).each(function (b,v) {
                                var $li3 = $("<li></li>");
                                var $a3 = $("<a></a>");
                                var $i3_1 = $("<i class=\"layui-icon\"></i>");
                                var $i3_2 = $("<i class=\"layui-icon nav_right\"></i>");
                                var $city3 = $("<cite></cite>");
                                $a3.attr("_href",v.url);
                                $i3_1.html(v.icon);
                                // $i3_2.html('&#xe697;');
                                $city3.html(v.name);
                                $a3.append($i3_1);
                                $a3.append($city3);
                                // $a3.append($i3_2);
                                $a3.appendTo($li3);
                                $nav3.append($li3);
                            });
                            $li2.append($nav3);
                        }
                        $nav2.append($li2);
                    });
                    $li1.append($nav2);
                }
                $nav.append($li1);
            });
            console.log($nav);
            $("#side-nav").append($($nav));
        }
    })
});