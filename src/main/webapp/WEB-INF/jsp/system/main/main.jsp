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
	<!-- Navbar -->
    <div class="navbar">
    	<div class="navbar-inner">
    	 	<!-- Navbar Barnd -->
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small>
                        <img alt="" src="resources/img/index/Logo_48px.png">
                        <span class="logo-text">iFreeWork</span>
                    </small>
                </a>
			</div>
            <!-- /Navbar Barnd -->
    	
			<!-- Sidebar Collapse -->
            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="collapse-icon fa fa-bars"></i>
            </div>
            <!-- /Sidebar Collapse -->
    	
    		<!-- Account Area and Settings --->
            <div class="navbar-header pull-right">
            	<div class="navbar-account">
            		<ul class="account-area">
            			<li>
            				 <a class=" dropdown-toggle" data-toggle="dropdown" title="Help" href="#">
                             	<i class="icon fa fa-warning"></i>
                             </a>
                             
                             <!--Notification Dropdown-->
                             <ul class="pull-right dropdown-menu dropdown-arrow dropdown-notifications">
                          	 	<li>
                          	 		<a href="#">
										<div class="clearfix">
											<div class="notification-icon">
                                                <i class="fa fa-phone bg-themeprimary white"></i>
                                            </div>
											<div class="notification-body">
												<span class="title">下班打卡</span>
                                                <span class="description">01:00 pm</span>
											</div>
											<div class="notification-extra">
                                                    <i class="fa fa-clock-o themeprimary"></i>
                                                    <span class="description">office</span>
                                            </div>
										</div>
                          	 		</a>
                                </li>
                                <li>
                          	 		<a href="#">
										<div class="clearfix">
											<div class="notification-icon">
                                                 <i class="fa fa-check bg-darkorange white"></i>
                                            </div>
											<div class="notification-body">
												<span class="title">参加公司内部会议</span>
                                                <span class="description">01:00 pm - 03:00 pm</span>
											</div>
											<div class="notification-extra">
                                                   <i class="fa fa-clock-o darkorange"></i>
                                            </div>
										</div>
                          	 		</a>
                                </li>
                                <li class="dropdown-footer ">
                                	<span>今天，2017年04月07日</span>
                                    <span class="pull-right">
                                    	10°c
                                        <i class="wi wi-cloudy"></i>
                                    </span>
                                </li>
                             </ul>
                             <!--/Notification Dropdown-->
            			</li>
            			<li>
            				<a class="wave in dropdown-toggle" data-toggle="dropdown" title="Help" href="#">
                            	<i class="icon fa fa-envelope"></i>
                                <span class="badge">3</span>
                           	</a>
                           	
            				<!--Messages Dropdown-->
                            <ul class="pull-right dropdown-menu dropdown-arrow dropdown-messages">
                           		<li>
                                	<a href="#">
                                    	<img src="<%=assetsPath %>/img/avatars/divyia.jpg" class="message-avatar" alt="Divyia Austin">
                                        <div class="message">
                                        	<span class="message-sender">
                                            	张学友
                                            </span>
                                            <span class="message-time">
                                                2分钟前
                                            </span>
                                            <span class="message-subject">
                                              	有时间来听我的演唱会
                                            </span>
                                            <span class="message-body">
                                              	下个星期我将会在济南举办演唱会，有时间的话欢迎来参加
                                            </span>
                                        </div>
                                    </a>
								</li>
                            	<li>
                                	<a href="#">
                                    	<img src="<%=assetsPath %>/img/avatars/bing.png" class="message-avatar" alt="Divyia Austin">
                                        <div class="message">
                                        	<span class="message-sender">
                                            	Bing.com
                                            </span>
                                            <span class="message-time">
                                             	昨天
                                            </span>
                                            <span class="message-subject">
                                            	Bing重大升级
                                            </span>
                                            <span class="message-body">
                                                Bing公司对搜索引擎进行重大升级，包涵许多新的功能
                                            </span>
                                        </div>
                                    </a>
								</li>
            				</ul>
            				<!--/Messages Dropdown-->
            			</li>
            			<li>
            				<a class="dropdown-toggle" data-toggle="dropdown" title="Tasks" href="#">
                            	<i class="icon fa fa-tasks"></i>
                                <span class="badge">4</span>
                            </a>
                            
                            <!--Tasks Dropdown-->
                            <ul class="pull-right dropdown-menu dropdown-tasks dropdown-arrow ">
                            	<li class="dropdown-header bordered-darkorange">
                                	<i class="fa fa-tasks"></i>
                                        4个未完成进度
                                </li>
                                <li>
                                	<a href="#">
                                    	<div class="clearfix">
                                            <span class="pull-left">帐号申请进度</span>
                                            <span class="pull-right">65%</span>
                                        </div>

                                        <div class="progress progress-xs">
                                            <div style="width:65%" class="progress-bar"></div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">文件下载进度</span>
                                            <span class="pull-right">35%</span>
                                        </div>
                                        <div class="progress progress-xs">
                                            <div style="width:35%" class="progress-bar progress-bar-success"></div>
                                        </div>
                                    </a>
                                </li>
                                <li class="dropdown-footer">
                                	<a href="#">
                                      	查看所有进度
                                    </a>
                                    <button class="btn btn-xs btn-default shiny darkorange icon-only pull-right"><i class="fa fa-check"></i></button>
                                </li>
                            </ul>
                            <!--/Tasks Dropdown-->
            			</li>
            			<li id="user-manager">
            				<a class="login-area dropdown-toggle" data-toggle="dropdown">
                                <div class="avatar" title="View your public profile">
                                    <img src="main/userImgDownload">
                                </div>
                                <section>
                                    <h2><span class="profile"><span>${user.personName }</span></span></h2>
                                </section>
                            </a>
                            
                            <ul class="pull-right dropdown-menu dropdown-tasks dropdown-arrow dropdown-login">
                            	<li><a href="#" data-id="user-img"><b class="fa fa-user"></b><span>编辑头像</span></a></li>
								<li><a href="#" data-id="user-edit" data-username="${user.username }"><b class="fa fa-cog"></b><span>用户设置</span></a></li>
								<li><a href="#"><b class="fa fa-info-circle"></b><span>&nbsp;&nbsp;帮&nbsp;&nbsp;&nbsp;&nbsp;助&nbsp;&nbsp;</span></a></li>
								<li><a href="#" data-id="logout"><b
										class="fa fa-ban red"></b><span class="red">&nbsp;&nbsp;退&nbsp;&nbsp;&nbsp;&nbsp;出</span></a></li>
                            </ul>
            			</li>
            		</ul>
            	</div>
    		</div>
    	</div>
    </div>
    <!-- /Navbar -->
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
	                            <a href="#" data-url="<%=contextPath%>/system/user">
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