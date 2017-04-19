<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8" />
<!-- jsp文件头和头部 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>${pd.SYSNAME}</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />


<link rel="stylesheet" href="<%=cssPath%>/main/style.css">
<script src="<%=jsPath%>/main/main.js"></script>
<script>
$().ready(function(){
	
});
</script>
<body class="body-skin1" >
	<!-- 页面导航 navbar -->
	<%@ include file="/WEB-INF/jsp/system/main/include/top.jsp"%>
	<!-- /navbar -->
    	
	<!-- Main Container -->
	<div class="main-container container-fluid">
	
		<!-- Page Container -->
    	<div class="page-container">
       	<!-- Page Sidebar -->
           <div class="page-sidebar" id="sidebar">
			           
				<!-- Page Sidebar Header-->
                <div class="sidebar-header-wrapper">
                   <input type="text" class="searchinput" />
                   <i class="searchicon fa fa-search"></i>
                   <div class="searchhelper">Search Reports, Charts, Emails or Notifications</div>
                </div>
                <!-- /Page Sidebar Header -->
                
				<!-- Sidebar Menu -->
                <ul class="nav sidebar-menu">
                
                	<!--Dashboard-->
                    <li>
                        <a href="index.html">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 首页 </span>
                        </a>
                    </li>
                
	                <!--UI Elements-->
	                <li class ="active open">
	                    <a href="#" class="menu-dropdown">
	                        <i class="menu-icon fa fa-desktop"></i>
	                        <span class="menu-text"> 系统管理 </span>
	
	                        <i class="menu-expand"></i>
	                    </a>
	
	                    <ul class="submenu">
	                        <li>
	                            <a href="#" data-url="http://www.baidu.com">
	                                <span class="menu-text">用户管理</span>
	                            </a>
	                        </li>
	                        <li>
	                            <a href="#" class="menu-dropdown">
	                                <span class="menu-text">
	                                  	组织机构管理
	                                </span>
	                                <i class="menu-expand"></i>
	                            </a>
	
	                            <ul class="submenu">
	                                <li>
	                                    <a href="font-awesome.html">
	                                        <i class="menu-icon fa fa-rocket"></i>
	                                        <span class="menu-text">Font Awesome</span>
	                                    </a>
	                                </li>
	                                <li>
	                                    <a href="glyph-icons.html">
	                                        <i class="menu-icon glyphicon glyphicon-stats"></i>
	                                        <span class="menu-text">部门管理</span>
	                                    </a>
	                                </li>
	                                <li>
	                                    <a href="typicon.html">
	                                        <i class="menu-icon typcn typcn-location-outline"></i>
	                                        <span class="menu-text">角色管理</span>
	                                    </a>
	                                </li>
	                                <li>
	                                    <a href="weather-icons.html">
	                                        <i class="menu-icon wi-day-snow"></i>
	                                        <span class="menu-text">权限管理</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
	                        <li>
	                            <a href="tabs.html">
	                                <span class="menu-text">菜单管理</span>
	                            </a>
	                        </li>
	                        <li>
	                            <a href="#" data-url="<%=contextPath%>/system/config">
	                                <span class="menu-text">系统设置</span>
	                            </a>
	                        </li>
	                        <li>
	                            <a href="modals.html">
	                                <span class="menu-text">数据字典</span>
	                            </a>
	                        </li>
	                        <li class ="active">
	                            <a href="buttons.html">
	                                <span class="menu-text">Buttons</span>
	                            </a>
	                        </liclass>
	                        <li>
	                            <a href="nestable-list.html">
	                                <span class="menu-text"> Nestable List</span>
	                            </a>
	                        </li>
	                        <li>
	                            <a href="treeview.html">
	                                <span class="menu-text">Treeview</span>
	                            </a>
	                        </li>
	                    </ul>
	                </li>
	                
	                <!--Tables-->
                    <li>
                        <a href="#" class="menu-dropdown">
                            <i class="menu-icon fa fa-table"></i>
                            <span class="menu-text"> Tables </span>

                            <i class="menu-expand"></i>
                        </a>

                        <ul class="submenu">
                            <li>
                                <a href="tables-simple.html">
                                    <span class="menu-text">Simple & Responsive</span>
                                </a>
                            </li>
                            <li>
                                <a href="tables-data.html">
                                    <span class="menu-text">Data Tables</span>
                                </a>
                            </li>
                        </ul>
                    </li>
	                
                
                </ul>
                <!-- /Sidebar Menu -->
           </div>
    		<!-- /Page Sidebar -->
    		
    		<!-- Page Content -->
            <div class="page-content">
            
				<!-- Page Breadcrumb -->
                <div class="page-breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">首页</a>
                        </li>
                        <li>
                            <a href="#">系统设置</a>
                        </li>
                        <li class="active">用户管理</li>
                    </ul>
                </div>
                <!-- /Page Breadcrumb -->
                
                <!-- Page Body -->
                <div class="page-body">
                	<iframe id="ifm" name="ifm" src="<%=contextPath%>/system/dept"></iframe>
                </div>
    		</div>
    		<!-- /Page Content -->
		</div>
		<!-- /Page Container -->
	</div>
	<!-- /Main Container -->
	
</body>

</html>