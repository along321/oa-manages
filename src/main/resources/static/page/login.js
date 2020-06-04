$(function () {
    layui.use('form', function () {
        var form = layui.form;
        //监听提交
        form.on('submit(login)', function (data) {

            var btn = $(this);
            btn.text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
            // var index=null;
            console.log(data);
            $.ajax({
                type: "POST",
                url: ctx+"login",
                data: data.field,
                success: function (r) {
                    // layer.close(index);
                    console.log(data.field)
                    if (r.code == 200) {
                        layer.msg(r.msg,{time:1000,icon:1},function(){
                            window.location.href = ctx+"main";
                        });
                    } else {
                        layer.msg(r.msg,{time:1000,icon:2 ,shift:6});
                        btn.text("登录").removeAttr("disabled").removeClass("layui-disabled");
                    }
                }
            });
            return false;
        });
    });
})
