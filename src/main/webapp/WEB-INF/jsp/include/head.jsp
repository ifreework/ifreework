<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.ifreework.util.Const" %>

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
<link rel="stylesheet" href="<%=assetsPath %>/css/beyond.min.css"></link>
<link rel="stylesheet" href="<%=assetsPath %>/css/font-awesome.min.css"></link>
<link href="<%=assetsPath %>/css/weather-icons.min.css" rel="stylesheet" />

<link href="<%=assetsPath %>/css/typicons.min.css" rel="stylesheet" />

<link href="<%=assetsPath%>/css/select2/select2.css" rel="stylesheet" />
<link href="<%=assetsPath%>/css/datepicker/bootstrap-datepicker.css" rel="stylesheet" />
<link href="<%=cssPath %>/base.css" rel="stylesheet" />



<!-- jquery -->
<script src="<%=assetsPath %>/js/skins.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.cookie.js"></script>
<script src="<%=assetsPath %>/js/bootstrap.min.js"></script>

<script src="<%=assetsPath%>/js/bootbox/bootbox.js"></script>
<script src="<%=assetsPath%>/js/validation/bootstrapValidator.js"></script>
<script src="<%=assetsPath%>/js/datetime/bootstrap-datepicker.js"></script>
<script src="<%=assetsPath%>/js/select2/select2.js"></script>
<script src="<%=assetsPath%>/js/textarea/jquery.autosize.js"></script>



<script src="<%=jsPath%>/base.js"></script>


<script>
	var SUCCESS = "<%=Const.SUCCESS %>";
	var ERROR = "<%=Const.ERROR %>";
	var FAILED = "<%=Const.FAILED %>";
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
