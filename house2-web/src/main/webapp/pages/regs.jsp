<%@ page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript">
     $(function(){  //jquery加载事件
         $("#but1").click(function () {
              //发送异步请求
             $.post("/checkName.do",{"name":$("#txtName").val()},function(data){
                 if(data.result==0){
                    $("#notice").text("用户名可用").css("color","green");
                 }else{
                     $("#notice").text("用户名已存在").css("color","red");
                 }
             },"json");
         });

     });
     //提交之前进行检查，如果return false，则不允许提交
     function check() {
//根据ID获取值
         var txtName = $("#txtName").val();
         var password = $("#password").val();
         var rePassword = $("#rePassword").val();
         var phone = $("#telephone").val();
         if(txtName==null||txtName==''){
            alert("用户名不能为空")
             return false;
            }
         if(password==null||password==""){
           alert("密码不能为空")
             return false;
         }
         if(rePassword!=password){
             alert("两次密码不一致");
             return false;
         }
         if(phone==null||phone==""){
             alert("电话不能为空");
             return false;
         }
         var  reg=/^[1][3,4,5,6,7,8,9][0-9]{9}$/;
          if(!(reg.test(phone))) {
              alert("不是正确的11位手机号");
              return false;
          }
         else{
             return true;
         }


     }


  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="/reg.do"  method="post" name="form1" onsubmit="return check()">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text id="txtName" type="text" name="name"  >
      <span id="notice"></span>
      <input id="but1" type="button" value="验证用户是否存在"> </TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="password"></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=rePassword id="rePassword"> </TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone id="telephone"> </TD></TR>
  <TR>
    <TD class=field>年龄：</TD>
    <TD><INPUT class=text type=text name=age> </TD></TR></TBODY></TABLE>
  <DIV class=buttons>
<INPUT  name="addusers" value=立即注册 type="submit" id="regs">

</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
