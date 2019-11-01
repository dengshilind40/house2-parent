//加载显示区域
$(function(){  //加载事件
    //显示数据
    $('#dg').datagrid({
        /*把工具栏绑定到datagrid中*/
        toolbar:"#tb",
        title:'用户信息',
        url:'getByUsers.do',
        pagination:true,
        pageSize:3,
        pageList:[3,5,10,15],

        columns:[[
            {field:'chk',checkbox:true,title:'',width:100},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'用户名',width:100},
            {field:'telephone',title:'电话',width:100},
            {field:'cz',title:'操作',width:100,
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteUsers("+row.id+")'>删除</a>  <a href=''>修改</a>"
                }
            },

        ]]
    });

});
      //查询
           function search() {
               var name=$("#sname").val();
               var telephone=$("#stelephone").val();
                 $("#dg").datagrid("load",{"name":name,"telephone":telephone});

}