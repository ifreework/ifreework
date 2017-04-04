<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

<%
	//设置项目路径
	String contextPath = request.getContextPath();
	String cssPath = contextPath + "/resources/css";
	String jsPath = contextPath + "/resources/js";
	String imagePath = contextPath + "/resources/img";
%>

<!DOCTYPE html>

<link rel="shortcut icon" href="<%=imagePath%>/index/favicon.ico"
	type="image/x-icon" />

<!-- bootstrap css -->
<link rel="stylesheet" href="<%=cssPath%>/main/font-awesome.css">
<link rel="stylesheet" href="<%=cssPath%>/main/entypo-icon.css">
<link rel="stylesheet" href="<%=cssPath%>/main/maki-icons.css">
<link rel="stylesheet" href="<%=cssPath%>/main/weather-icons.min.css">
<link rel="stylesheet" href="<%=cssPath%>/main/dripicon.css">
<link rel="stylesheet" href="<%=cssPath%>/main/open-sans.css">
<link rel="stylesheet" href="<%=cssPath%>/main/awwwards.css">


<link rel="stylesheet" href="<%=cssPath%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=cssPath%>/bootstrap/bootstrap-responsive.min.css">
<link rel="stylesheet" href="<%=cssPath%>/main/loader-style.css">

<link rel="stylesheet" href="<%=cssPath%>/color/color.css">
<link rel="stylesheet" href="<%=cssPath%>/base.css">



<!-- jquery -->
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.cookie.js"></script>
<script src="<%=jsPath%>/bootstrap/bootstrap.min.js"></script>
<script src="<%=jsPath%>/base.js"></script>
<script src="<%=jsPath%>/jquery/jquery.extend.js"></script>
<script>
	$().ready(function() {
		var errorType = getRequestParamByName("errorType");
		if (errorType == "userIsNull") {
			if (window == top)
				$.alert("用户尚未登录或用户登录超时，请重新登录。");
			else
				top.location.href = location.href;
		}
	});
</script>
