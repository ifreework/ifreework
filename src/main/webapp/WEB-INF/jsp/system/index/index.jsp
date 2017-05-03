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

<!-- 自定义核心样式 -->
<link rel="stylesheet" href="${ cssPath }/base.css"  />
<link rel="stylesheet" href="${ cssPath }/main/style.css"  />

<link rel="stylesheet" href="${ cssPath }/index/index.css">

<!-- jquery,bootstrap核心 -->
<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ jsPath }/jquery/jquery.cookie.js"></script>
<script src="${ jsPath }/bootstrap/bootstrap.min.js"></script>

<!-- jquery,bootstrap插件 -->
<script src="${ jsPath }/bootstrap/bootbox/bootbox.js"></script>

<!-- 部分自定义方法 -->
<script src="${ jsPath }/base.js"></script>

<script>
	var SUCCESS = "<%=Constant.SUCCESS %>"; //前后台统一成功标志
	var ERROR = "<%=Constant.ERROR %>";  //前后台错误标志
	var FAILED = "<%=Constant.FAILED %>"; //前后台失败标志
	
	$().ready(function() {
		validateLogin(); //验证是否有登录提示信息
		loadCookie(); //加载是否有cookie信息
		
		//点击登录按钮，执行登录操作
		$("#login").click(function(event) {
			event.preventDefault();
			submitUserData();
		});
		
		//在页面下按下回车键，执行登录操作
		$("body").keypress(function(event){
			console.log(event);
			if(event.keyCode == 13){//keyCode == 13为回车键
				submitUserData();
			}
		});
	});
	
	

	//登录操作
	function submitUserData(){
		if (validate()) {
			var data = $("#loginForm").serializeJson();
			ajax({
					url : "${contextPath}/login",
					data:data,
					success : function(result) {
						console.log(result);
						if(result.result == SUCCESS){
							changeCookie($("#cookieBox").get(0).checked);
							location.href="${contextPath}/main"
						}else{
							bootbox.unload();
							bootbox.alert(result.msg);
						}
					},
					complete : function(xhr, ts){
						
					}
			});
		}
	}

	//如果用户在cookie中保存了数据的话，将自动填充用户名密码
	function loadCookie(){
		var username = $.cookie("username");
		var pwd = $.cookie("password");
		if(!isNull(username) && !isNull(pwd)){
			$("#cookieBox").attr("checked","checked"); 
			$("#name").val(username);
			$("#psd").val(pwd);
		}
	}
	
	//验证用户名密码是否为空
	function validate() {
		if (isNull($("#name").val())) {
			bootbox.alert("请输入您的用户名。");
			return false;
		}
		if (isNull($("#psd").val())) {
			bootbox.alert("请输入您的密码。");
			return false;
		}
		return true;
	}
	
	//显示验证提示信息
	//保存和修改cookie
	function changeCookie(checked){
		if(checked){
			$.cookie("username", $("#name").val());
			$.cookie("password", $("#psd").val());
		}else{
			$.cookie('username', '', { expires: -1 });
			$.cookie('password', '', { expires: -1 });
		}
	}

	//验证是否有用户登录提示信息
	function validateLogin(){
		var errorType = "${errorType}";
		if (errorType === "userIsNull") {
			bootbox.alert("用户未登录或登录超时，请重新登录。","",function(){
				window.location.href = "${contextPath}";
			});
		}
	}
	
</script>
</head>
<body>
	<div class="login_div">
		<div class="col-xs-12 login_title">登录</div>
		<form action="" class="login" id="loginForm" method="post">
			<div class="nav">
				<div class="nav login_nav">
					<div class="col-xs-2 login_username"></div>
					<div class="col-xs-8 login_usernameInput">
						<input type="text" name="username" id="name" value=""
							placeholder="&nbsp;&nbsp;用户名/手机号" />
					</div>
				</div>
				<div class="nav login_psdNav">
					<div class="col-xs-2 login_username"></div>
					<div class="col-xs-8 login_usernameInput">
						<input type="password" name="password" id="psd"
							placeholder="&nbsp;&nbsp;密码" />
					</div>
				</div>
				<div class="col-xs-12 login_cookie">
					<div class="col-xs-2"></div>
					<div class="col-xs-8">
						<div class="col-xs-6">
							<label><input type="checkbox" id="cookieBox"><span style="position: absolute;left:20px">下次自动登录</span></label>
						</div>
						<div class="col-xs-4">
							<a href="javascript:void(0)">忘记密码？</a>
						</div>
					</div>
				</div>
				<div class="col-xs-12 login_btn_div">
					<input type="button" class="btn btn-blue sub_btn" id="login" value="登录" />
				</div>
			</div>
		</form>

		
	</div>
	
	<div class="col-xs-12 barter_btnDiv">
		<span class="barter_btn">版权所有：iFreeWork开发者团队</span>
		<span class="barter_btn">Copyright © Wangyh qq735789026 2014-2017</span>
	</div>
	<img src="${imagePath}/system/9.jpg" width="100%" style="position: absolute;left:0px;">
</body>
</html>