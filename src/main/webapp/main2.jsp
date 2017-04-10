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
	
<link rel="stylesheet" href="<%=cssPath%>/main/form.css">
<link rel="stylesheet" href="<%=cssPath%>/main/animate.min.css">
<link rel="stylesheet" href="<%=jsPath%>/sidebar/css/sidebar.css">
<link rel="stylesheet" href="<%=cssPath%>/main/style.css">
<link rel="stylesheet" href="<%=cssPath%>/main/chat.css">
<link rel="stylesheet" href="<%=cssPath%>/main/shake.css">

<script src="<%=jsPath%>/bootstrap/bootbox/bootbox.js"></script>
<script src="<%=jsPath%>/sidebar/sidebar.js"></script>
<script src="<%=jsPath%>/bootstrap/chat/chatwindow.js"></script>
<script src="<%=jsPath%>/bootstrap/chat/chat.js"></script>
<script src="<%=jsPath%>/main/websocket/sockjs.js"></script>
<script src="<%=jsPath%>/main/websocket/stomp.js"></script>

<script type="text/javascript">
	$().ready(function() {
		$(".main-container").height(($("body").height() - 50));
		$("#sidebar").sidebar();
		$("#user_img").click(function(){
			var dialog = bootbox.dialog({
				title: "头像设置",
				height:485,
				width:740,
                message: "<iframe id='userChangeImgDialog' name='userChangeImgDialog'  src='<%=contextPath%>/main/userChangeImg'></iframe>",
                buttons: {
                    "确定": {
                    	className:"btn-primary",
                        callback: function () {
                        	var dropzone = window.frames["userChangeImgDialog"].dropz, img = $(window.frames["userChangeImgDialog"].document).find(".dropzone-img-view img"),
                        	 	cDiv = $(window.frames["userChangeImgDialog"].document).find(".dropzone-img-view .center"),
                        	 	width=img.width(),height=img.height(),sw = cDiv.width() + 2,sh = cDiv.height() + 2,sx = cDiv.position().left,sy = cDiv.position().top;
                        	dropzone.options.params = {
                        		width:width,
                        		height:height,
                        		sw:sw,
                        		sh:sh,
                        		sx:sx,
                        		sy:sy
                        	};
                        	dropzone.on("success", function(file) {
                        		$("#userImg").attr("src",$("#userImg").attr("src")  + "?_time=" + new Date().getTime());
                        		dialog.modal("hide");
                        	});
                        	dropzone.processQueue();
                        	return false;
                        }
                    },
                    "取消": {
                    	className: "btn-primary",
                        callback: function () { }
                    }
                }
            });

		});
		$("#logout").click(function(){
			bootbox.confirm("确定要退出登录吗？", function (result) {
                if (result) {
                   location.href = "<%=contextPath%>/logout";
                }
            });
		});
		
		
		$(".nav .theme-bg div").click(function() {
			
			var index = $(this).index() + 1;
			var _this = this;
			$.ajax({
				url : "<%=contextPath%>/main/skinChoose",
														data : {
															skin : "body_skin"
																	+ index
														},
														dataType : "json",
														success : function(data) {
															if (data.result = "success") {
																$(_this)
																		.parent()
																		.find(
																				"div")
																		.removeClass(
																				"active");
																$(_this)
																		.addClass(
																				"active");
																$("body")
																		.removeClass()
																		.addClass(
																				"body_skin"
																						+ index);
															}
														}
													});
										});
					});
</script>
</head>
<body
	class="${user.skin == null || user.skin == '' ? 'body_skin1' : user.skin }">
	<!-- 页面导航 navbar -->
	<%@ include file="/WEB-INF/jsp/system/main/include/top.jsp"%>
	<!-- /navbar -->
	
	
	<!-- 页面主体Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">
			<!-- 导航菜单Page Sidebar -->
			<%@ include file="/WEB-INF/jsp/system/main/include/left.jsp"%>
			<!-- /Page Sidebar -->
			<!-- 主体内容Page Content -->
			<%@ include file="/WEB-INF/jsp/system/main/include/content.jsp"%>
			<!-- /Page Container -->
		</div>
	</div>
	<!-- /Main Container -->
	
	<%@ include file="/WEB-INF/jsp/system/main/include/chat.jsp"%>
	<%@ include file="/WEB-INF/jsp/system/main/include/chatWindow.jsp"%>
	
	<script src="<%=jsPath%>/main/load.js"></script>
	<script src="<%=jsPath%>/main/main.js"></script>
</body>
</html>