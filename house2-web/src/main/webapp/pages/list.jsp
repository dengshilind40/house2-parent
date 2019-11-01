<%@ page pageEncoding="utf-8" language="java" contentType="text/html;utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script src="../scripts/jquery-1.8.3.js"></script>
  <script>
      $(function () {
          //发送异步请求加载类型数据
          $.post("/getAllType.do",null,function (data) {
              //循环一次取得一个选项的值
              for(var i=0;i<data.length;i++){
                  //创建dom节点
                  //$("<option></option>")是获取option这个标签，
                  // text("")是给标签添加内容，也就是选择框显示的值，
                  // val("")是给标签value属性赋值，就是传递的值
                  var option = $("<option value="+data[i].id+">"+data[i].name+"</option>");
                  //添加节点
                  $("#type_id").append(option)
              }
              //设置类型下拉框选中项
              $("#type_id").val(${houseUtil.tid});
          },"json");
          //异步加载所有区域
          $.post("/findAllDistrict.do",null,function (data) {
              //循环一次取得一个选项的值
              for(var i=0;i<data.length;i++){
                  //创建dom节点

                  var option = $("<option value="+data[i].id+">"+data[i].name+"</option>");
                  //添加节点
                  $("#district_id").append(option)
              }
              //设置区域的下拉框选中项
              $("#district_id").val(${houseUtil.did});
              //加载街道
              changeStreet();
          },"json");
          //异步加载街道
          $("#district_id").change(function () { //触发区域的改变事件
              changeStreet();
          });
      });
      function changeStreet() {
          var did=$("#district_id").val();
          //发送异步请求获取街道
          //清空选项
          $("#street_id>option:gt(0)").remove();
          //加载数据
          $.post("/getStreet.do",{"districtId":did},function(data){
              //循环一次一行对就一个选项
              for (var i=0;i<data.length;i++){
                  //创建dom节点
                  var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                  //添加节点
                  $("#street_id").append(option);
              }
              //设置街道选中项
              $("#street_id").val(${houseUtil.sid})
          },"json");
      }


      function goPage(num) {  //num当前页码
          $("#setPage").val(num);   //设置当前页码
          $("#form").submit()     //表单提交
      }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id="form" method="post" action="/searchHouse.do">
    <input type="hidden" name="page" id="setPage" value="1">
      标题： <input type="text" name="title" value="${houseUtil.title}">
       区域： <select name="did" id="district_id">
    <option value="">请选择</option>
  </select>
      街道： <select name="sid" id="street_id">
    <option value="">请选择</option>
  </select>
    类型： <select name="tid" id="type_id">
    <option value="">请选择</option>
  </select>
    价格：  <input type="text" name="startPrice" value="${houseUtil.startPrice}" >- <input type="text" name="endPrice" value="${houseUtil.endPrice}">
    <input type="submit" name="search" value="搜索">
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${house.list}" var="h">
  <TR>
    <TD class=house-thumb><span><A href="details.html" target="_blank"><img src="../images/thumb_house.gif" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.html" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}-${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
  </c:forEach>
  <TR>无租房信息</TR>
  </TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${house.prePage==0?1:house.prePage})">上一页</A></LI>
  <LI><A href="javascript:goPage(${house.nextPage==0?house.pages:house.nextPage})">下一页</A></LI>
  <LI><A href="javascript:goPage(${house.pages})">末页</A></LI></UL><SPAN
class=total>${house.pageNum}/${house.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
