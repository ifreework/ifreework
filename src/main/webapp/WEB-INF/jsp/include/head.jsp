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
	String assetsPath = contextPath + "/resources/assets";
%>

<!DOCTYPE html>

<link rel="shortcut icon" href="<%=imagePath%>/index/favicon.ico"
	type="image/x-icon" />

<!-- bootstrap css -->
<link rel="stylesheet" href="<%=assetsPath %>/css/bootstrap.min.css"></link>

<link rel="stylesheet" href="<%=assetsPath %>/css/font-awesome.min.css"></link>
<link href="<%=assetsPath %>/css/weather-icons.min.css" rel="stylesheet" />

<link href="<%=assetsPath %>/css/typicons.min.css" rel="stylesheet" />
<link href="<%=cssPath %>/base.css" rel="stylesheet" />

<!-- jquery -->
<script src="<%=assetsPath %>/js/skins.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.cookie.js"></script>
<script src="<%=assetsPath %>/js/bootstrap.min.js"></script>

<script src="<%=assetsPath%>/js/bootbox/bootbox.js"></script>

<script src="<%=jsPath%>/base.js"></script>


<script>
	$().ready(function() {
		var errorType = getRequestParamByName("errorType");
		if (errorType == "userIsNull") {
			if (window == top)
				bootbox.alert("用户未登录或登录超时，请重新登录。");
			else
				top.location.href = location.href;
		}
	});
</script>
