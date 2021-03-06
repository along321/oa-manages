layui.use(['laydate','table','form','layer'], function(){
    var laydate = layui.laydate,
        table = layui.table,
        form = layui.form,
        layer = layui.layer;
    table.render({
        elem: '#allList'
        ,id: 'idList'
        ,url: '/post/list' //数据接口
        ,limit:5
        ,page: true //开启分页
        ,loading:true
        ,even: true //开启隔行背景
        ,size: 'lg' //大/小尺寸的表格
        ,cellMinWidth:'60'
        ,cols: [[ //表头
            // {type:'checkbox'},//复选框
            {field: 'id', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'name', title: '岗位名称'}
            ,{title:'操作',align:'center',toolbar:'#barDemo'}//把操作按钮添加进来
        ]]
        ,request:{
            pageName:'pageNum'//页码的参数名称，默认：page
            ,limitName:'pageSize' //每页数据量的参数名，默认：limit
        }
        ,response:{
            statusName:'code'
            ,statusCode:'100'
            ,msgName:'msg'
            ,countName:'count'
            ,dataName:'data'
        }
        ,done:function (res,curr,count) {
            $("#count").text("共有数据："+count+"条");
        }
    });
    table.on('tool(test)',function (obj) {//注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data;//获得当前行数据
        var layEvent = obj.event;//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        console.log(data);
        if (layEvent == 'detail') {
            layer.msg(data.id);
        }else if(layEvent == 'del'){
            layer.confirm('真的删除此岗位吗？',function (index) {
                $.ajax({
                    type: "POST",
                    data: {"id":data.id},
                    url: ctx+"post/delPost",
                    success: function (data) {
                        data = eval(data);
                        if(data.code==200){
                            layer.alert("删除成功", {icon: 6},function () {
                                obj.del();
                                window.location.reload();
                            });
                        }else{
                            layer.alert("删除失败");
                            layer.close(index);
                        }
                    }
                });
            });
        }else if(layEvent == 'edit'){
            layer.open({
                type: 2,
                area: ['300px', '200px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade:0.4,
                title: "编辑",
                content: '/editPost/'+data.id,
                end:function () {
                    window.location.reload();
                }
            });
            // layer.msg(data.id);
        }
    });
});
function x_admin_show() {
    layer.open({
        type: 2,
        area: ['300px', '200px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: "编辑",
        content: '/addPost',
        end:function () {
            window.location.reload();
        }
    });
}