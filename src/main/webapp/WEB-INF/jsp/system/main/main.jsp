<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title><%=Config.init().get(Config.SYSTEM_NAME) %></title>
<link rel="shortcut icon" href="${ imagePath }/system/favicon.ico"
	type="image/x-icon" />

<!-- bootstrap核心样式 -->	
<link rel="stylesheet" href="${ cssPath }/bootstrap.min.css"></link>
<link rel="stylesheet" href="${ cssPath }/ifreework.min.css"></link>

<!-- icon字体样式 -->
<link rel="stylesheet" href="${ cssPath }/font-awesome.min.css"></link>
<link rel="stylesheet" href="${ cssPath }/weather-icons.min.css"  />
<link rel="stylesheet" href="${ cssPath }/typicons.min.css"  />

<!-- jquery,bootstrap插件样式 -->
<link rel="stylesheet" href="${ cssPath }/select2/select2.css"></link>
<link rel="stylesheet" href="${ cssPath }/datepicker/bootstrap-datepicker.css"  />
<link rel="stylesheet" href="${ cssPath }/dataTables.bootstrap.css"  />

<!-- 自定义核心样式 -->
<link rel="stylesheet" href="${ cssPath }/base.css"  />
<link rel="stylesheet" href="${ cssPath }/main/style.css"  />

<!-- jquery,bootstrap核心 -->
<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ jsPath }/jquery/jquery.cookie.js"></script>
<script src="${ jsPath }/bootstrap/bootstrap.min.js"></script>

<!-- jquery,bootstrap插件 -->
<script src="${ jsPath }/bootstrap/validation/bootstrapValidator.js"></script>
<script src="${ jsPath }/bootstrap/datetime/bootstrap-datepicker.js"></script>
<script src="${ jsPath }/bootstrap/select2/select2.js"></script>
<script src="${ jsPath }/bootstrap/textarea/jquery.autosize.js"></script>
<script src="${ jsPath }/bootstrap/datatable/jquery.dataTables.js"></script>
<script src="${ jsPath }/bootstrap/datatable/dataTables.bootstrap.js"></script>
<script src="${ jsPath }/bootstrap/bootbox/bootbox.js"></script>

<!-- 部分自定义方法 -->
<script src="${ jsPath }/base.js"></script>

<!-- main界面js -->
<script src="${ jsPath }/main/main.js"></script>

<script>
	var SUCCESS = "<%=Constant.SUCCESS %>"; //前后台统一成功标志
	var ERROR = "<%=Constant.ERROR %>";  //前后台错误标志
	var FAILED = "<%=Constant.FAILED %>"; //前后台失败标志
	
	$().ready(function(){
		$("#page-body").load("${contextPath}/system/user");
	});
</script>

<body class="body-skin2" >
	<!-- 页面导航 navbar -->
	<%@ include file="/WEB-INF/jsp/system/main/include/top.jsp"%>
	
	<!-- Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
    	<div class="page-container">
    	
    	<!-- 页面左边导航 sidebar -->
    	<%@ include file="/WEB-INF/jsp/system/main/include/left.jsp"%>
    	
    	    <!-- Page Content -->
            <div class="page-content">
            
				<!-- Page Breadcrumb -->
                <div class="page-breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="javascript:void(0)">首页</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)">系统设置</a>
                        </li>
                        <li class="active">用户管理</li>
                    </ul>
                </div>
                <!-- /Page Breadcrumb -->
                
                <!-- Page Body -->
                <div class="page-body" id="page-body">
                	
                </div>
    		</div>
    		<!-- /Page Content -->
    		
    	</div>
		<!-- /Page Container -->
	</div>
	<!-- /Main Container -->
</body>
</html>
