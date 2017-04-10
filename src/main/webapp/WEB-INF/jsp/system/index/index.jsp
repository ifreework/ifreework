<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<html lang="en">
<head>
<title>${pd.SYSNAME}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=cssPath%>/index/index.css">
<script>
	$().ready(function() {
		loadCookie();
		
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
					url : "<%=contextPath%>/login",
					data:data,
					success : function(result) {
						console.log(result);
						if(result.result == "failed"){
							bootbox.alert(result.msg);
						}else{
							changeCookie($("#cookieBox").get(0).checked);
							location.href="<%=contextPath%>/main"
						}
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
			showMsg("请输入用户名或者手机号。");
			return false;
		}
		if (isNull($("#psd").val())) {
			showMsg("请输入用户密码。");
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
							<a href="#">忘记密码？</a>
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
	<img src="<%=imagePath%>/index/9.jpg" width="100%" style="position: absolute;left:0px;">
</body>
</html>