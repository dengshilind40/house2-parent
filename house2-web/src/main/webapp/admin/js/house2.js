$(function () {
    $('#dg').datagrid({
        /*把工具栏绑定到datagrid中*/
        toolbar:"#tb",
        title:'出租房信息',
        url:'/admin/getHousePass.do',
        pagination:true,
        pageSize:2,
        pageList:[2,5,10,15],
        columns:[[
            {field:'chk',checkbox:true,title:'',width:100},
            {field:'id',title:'编号',width:100},
            {field:'title',title:'标题',width:100},
            {field:'dname',title:'区域名称',width:100},
            {field:'sname',title:'街道名称',width:100},
            {field:'tname',title:'类型名称',width:100},
            {field:'floorage',title:'面积',width:100},
            {field:'price',title:'价格',width:100},
            {field:'contact',title:'联系方式',width:100},
            {field:'dd',title:'操作',width:100,
                formatter: function(value,row,index){
                    return "<a href='javascript:HousePass("+row.id+")'>取消审核</a> "
                }
            },

        ]]
    });
});
     //取消审核出租房信息
function HousePass(id) {
    $.post("/admin/goHouseNotPass.do",{"id":id},function (data) {
        if(data.result>0){
            $("#dg").datagrid("reload")//刷新
        }else
            $.messager.alert("提示信息","审核失败","error")
    },"json")
}

