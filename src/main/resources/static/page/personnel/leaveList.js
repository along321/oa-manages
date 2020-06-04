layui.use(['laydate','table','form','layer'], function(){
    var laydate = layui.laydate,
        table = layui.table,
        form = layui.form,
        layer = layui.layer;
    table.render({
        elem: '#allList'
        ,id: 'idList'
        ,url: '/leave/leaverList' //数据接口
        ,limit:5
        ,page: true //开启分页
        ,loading:true
        ,even: true //开启隔行背景
        ,size: 'lg' //大/小尺寸的表格
        ,cellMinWidth:'60'
        ,cols: [[ //表头
            {field: 'leatype', title: '标题', sort: true, fixed: 'left'}
            ,{field: 'realName', title: '申请人'}
            ,{field: 'deptName', title: '所属部门'}
            // ,{field: 'sex', title: '性别 ', width:'5%',templet:function (d) {
            //         return d.sex=='1'?'女':'男';}}
            ,{field: 'applyDate', title: '申请时间',templet: function(d){
                    return Format(d.applyDate,'yyyy-MM-dd')}}
            ,{field: 'startDate', title: '开始时间',templet: function(d){
                    return Format(d.startDate,'yyyy-MM-dd')}}
            ,{field: 'endDate', title: '结束时间',templet: function(d){
                    return Format(d.endDate,'yyyy-MM-dd')}}
            ,{field: 'numDays', title: '请假天数'}
            ,{field: 'conent', title: '备注'}
            ,{field: 'rank', title: '状态 ',templet:function (d) {
                    return d.rank=='4'?'已完成':'待审批';}}
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
        }else if(layEvent == 'edit'){
            layer.open({
                type: 2,
                area: ['500px', '550px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade:0.4,
                title: "编辑",
                content: '/editLeave/'+data.id,
                end:function () {
                    window.location.reload();
                }
            });
            // layer.msg(data.id);
        }
    });

});
