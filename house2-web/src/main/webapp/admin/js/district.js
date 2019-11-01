//加载显示区域
$(function(){  //加载事件
    //显示数据
    $('#dg').datagrid({
        /*把工具栏绑定到datagrid中*/
        toolbar:"#tb",
        title:'区域信息',
        url:'getAllDistrictByPage.do',
        pagination:true,
        pageSize:3,
        pageList:[3,5,10,15],

        columns:[[
            {field:'chk',checkbox:true,title:'',width:100},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'区域名称',width:100},
            {field:'dd',title:'操作',width:100,
                formatter: function(value,row,index){
                    return "<a href='javascript:deleteDistrict("+row.id+")'>删除</a>  <a href=''>修改</a>"
                }
            },

        ]]
    });

});
/*
* 添加
*
* */
//点击添加按钮调用的函数
function add() {
    //alert("你好")
    $("#AddDialog").dialog("open")//打开对话框窗口
}
function CloseDialog() {
    $("#AddDialog").dialog("close")//关闭对话框窗口
}
function SaveDialog() {
    //使用easyui的表单提示插件
    $('#addForm').form('submit',{  //提交
        url:'addDistrict.do',    //
        success:function(data){//获得的是json字符串
            //将json字符串转换为json对象
            data=$.parseJSON(data);
            if(data.result==1){
                $("#dg").datagrid("reload")//刷新
                $("#AddDialog").dialog("close")//关闭对话框窗口
            }else {
                alert("添加失败")
            }
        }
    });
}
/*
 * 修改
 *   信息
 * */
//点击修改调用的函数
function Updateload() {
    //判断是否选中行
    //getSelections获取所有选中的行
    var rows= $("#dg").datagrid("getSelections");
    if(rows.length==1){
        //1先获取id  2根据id发送异步请求查询数据 3返回数据显示到对话框
        var bh=rows[0].id;
        $.post("selectByOne.do",{"id":bh},function (data) {

            //还原表单数据
            $("#upForm").form("load", data)

        },"json");

        //打开窗口
        $("#UpDialog").dialog("open")
    }
    else{
        $.messager.alert('消息提示', '你没有选中或者选中多行', 'warning');
    }

}
//修改信息
//点击更新按钮调用函数
function Update() {

    $("#upForm").form('submit', {
        //设置隐藏域
        url:'updateDistrict.do',
        success:function (data) {//获取的是json字符串
            //把json字符串转换为json对象
            data=$.parseJSON(data)
            if(data.result==1){
                $("#dg").datagrid("reload")//刷新
                $("#UpDialog").dialog("close")//关闭对话框窗口
            }else {
                $.messager.alert('提示信息','信息修改失败','error');
            }
        }

    },"json");
}
//删除区域
function deleteDistrict(id) {
    //确认提示框
    $.messager.confirm('删除区域', '确定要删除吗?', function(r){
        if (r){
            //异步请求   删除
            $.post("deleteDistrict.do",{"id": id},function (data) {
                if (data.result == 1) {
                    $("#dg").datagrid("reload")//刷新

                } else {
                    $.messager.alert('提示信息', '信息删除失败', 'error');
                }
            },"json");
        }
    });
}
//批量删除
function DeleteMore() {
    //判断是否选中行
    //getSelections获取所有选中的行
    var rows= $("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert('提示信息','您还没有选中行','info');
    }else {
        $.messager.confirm("删除区域","确定删除吗？",function (r) {
            if(r){
                var ids='';
                for( var i =0;i<rows.length;i++){
                    ids=ids+rows[i].id+','
                };
                //去除,号
                ids=ids.substring(0,ids.length-1);
                $.post("delMoreDistrict.do",{"ids":ids},function (data) {
                    if(data.result>=1){
                        $("#dg").datagrid("reload")//刷新
                    }else {
                        $.messager.alert('提示信息', '信息删除失败', 'error');
                    }
                },"json");
            }

        })
    }
}