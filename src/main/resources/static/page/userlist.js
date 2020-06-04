layui.use(['laydate','table','form','layer'], function(){
    var laydate = layui.laydate,
        table = layui.table,
        form = layui.form,
        layer = layui.layer;
    table.render({
        elem: '#allList'
        ,id: 'idList'
        ,url: '/user/list' //数据接口
        ,limit:5
        ,page: true //开启分页
        ,loading:true
        ,even: true //开启隔行背景
        ,size: 'lg' //大/小尺寸的表格
        ,cellMinWidth:'60'
        ,cols: [[ //表头
            {field: 'userName', title: '登录名称', width:'8%', sort: true, fixed: 'left'}
            ,{field: 'realName', title: '真实姓名', width:'10%'}
            ,{field: 'sex', title: '性别 ', width:'5%',templet:function (d) {
                    return d.sex=='1'?'女':'男';}}
            ,{field: 'birthday', title: '生日', width:'10%',templet: function(d){
                    return Format(d.birthday,'yyyy-MM-dd')}}
            ,{field: 'phone', title: '电话', width:'10%'}
            ,{field: 'email', title: '邮箱', width:'10%'}
            ,{field: 'oaDept', title: '部门', width: '10%',templet:function (d) {
                return d.oaDept.name }}
            ,{field: 'oaPost', title: '岗位', width: '18%',templet:function (d) {
                return d.oaPost.name  }}
            ,{field: 'principal', title: '负责人 ', width:'5%',templet:function (d) {
                    return d.principal=='1'?'是':'否';}}
            ,{title:'操作',width:'15%',align:'center',toolbar:'#barDemo'}//把操作按钮添加进来
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
            // layer.msg("hello",{
            //     icon:1,
            //     time:2000
            // });
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
            layer.confirm('真的删除此用户吗？',function (index) {
                $.ajax({
                    url:'/user/del',
                    type:"post",
                    data:{"id":data.id},
                    success:function (data) {
                        data = eval(data);
                        if(data.code==200){
                            layer.msg('删除成功');
                            obj.del();
                            window.location.reload();
                        }else{
                            layer.msg('删除失败');
                        }
                    }
                })
                layer.close(index);
            });
        }else if(layEvent == 'edit'){
            layer.open({
                type: 2,
                area: ['500px', '650px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade:0.4,
                title: "编辑",
                content: '/useredit/'+data.id,
                end:function () {
                    window.location.reload();
                }
            });
            // layer.msg(data.id);
        }
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });
    //搜索
    form.on("submit(sreach)",function (data) {
        console.log(data);
        table.reload('idList',{
            url: 'user/sreach',
            page:{
                curr:1  //从第一页开始
            },
            method:'post',
            where: data.field
        });
        return false;
    })
    laodClasses();
    function laodClasses() {
        $("#gang").empty();
        $.post("/user/loadPost",function (re) {
            var result =eval(re);
            $("#gang").append(new Option('岗位',''))
            $.each(result.extend.data,function (i,list) {
                $("#gang").append(new Option(list.name,list.id))
            });
            form.render();
        });
    };
});
function x_admin_show() {
    layer.open({
        type: 2,
        area: ['500px', '650px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: "添加用户",
        content: '/adduser',
        end:function () {
            window.location.reload();
        }
    });
};
function openDept() {
    layer.open({
        type: 2,
        area: ['600', '300'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: "选择部门",
        content: '/dept'
    });
}
