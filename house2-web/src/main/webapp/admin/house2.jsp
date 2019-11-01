<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/25
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
   <script language="JavaScript" src="js/house2.js"></script>
</head>
<body>
<table id="dg"></table>
<%--工具栏--%>
<div id="tb">
    区域 <select></select>
    街道 <select></select>
    类型 <select></select>
    价格 <input type="text" name="startPrice" id="startPrice">-<input type="text" name="endPrice" id="endPrice">
    <a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>

</div>
<%--制作添加对话框信息--%>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 350px; height: 340px; padding: 10px 20px;"
     title="区域信息"
     closed="true" data-options="modal:true">
    <form action=""id="addForm" method="" name="addForm">
        区域名称:  <input type="text" name="name" id="">
    </form>
</div>


<%--通过button的#AddDialogButtons关联对话框
      制作添加对话框按钮
--%>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog()"class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<%--制作修改对话框--%>
<div id="UpDialog" class="easyui-dialog" buttons="#UpDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;"
     title="修改信息"
     closed="true" data-options="modal:true">
    <form action=""id="upForm" method="post" name="upForm">
        区域名称:  <input type="text" name="name" id="name"> <br>
        <%--设置隐藏域--%>
        <input type="hidden" name="id" >
        <%--第二种方法：显示id但不可更改--%>
        <%-- <input type="text" readonly name="id"  >--%>
    </form>
</div>


<%--制作修改关联对话框按钮--%>
<div id="UpDialogButtons">
    <a href="javascript:Update()" class="easyui-linkbutton" iconCls="icon-ok">更新</a>
    <a href="javascript:CloseDialog()"class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>
