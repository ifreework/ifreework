<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<html lang="en">
<head>
<title>${pd.SYSNAME}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="<%=cssPath%>/index/index.css">
<script src="<%=jsPath %>/easyui/jquery.easyui.min.js"></script>
<script>
	$().ready(function() {
		loadCookie();
		$("#login").click(function(event) {
			event.preventDefault();
			if (validate()) {
				$("#loginForm").form("submit", {
					url : "<%=contextPath%>/login",
					onSubmit : function() {
						$.messager.progress({border:'thin'}); // 如果表单是无效的则隐藏进度条
					},
					success : function(data) {
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						data = $.parseJSON(data);
						if(data.result == "failed"){
							showMsg(data.msg);
						}else{
							changeCookie($("#cookieBox").get(0).checked);
							location.href="<%=contextPath%>/main"
						}
					}

				});
			}
		});
	});

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
	var showTime = 0;
	function showMsg(html){
		$("#msg").html(html);
		$("#msg").show(500);
		showTime = new Date().getTime();
		setTimeout("hideMs()", 10000);
	}
	
	function hideMs(){
		var nowTime =  new Date().getTime();
		if((nowTime - showTime)/1000 >= 10){
			$('#msg').hide(500);
		}else{
			setTimeout("hideMs()", 10000);
		}
		
	}
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
<body style="overflow: hidden; background: red;">
	<div class="login_div">
		<div class="col-xs-12 login_title">登录</div>
		<form action="" class="login" id="loginForm" method="post">
			<div class="nav">
				<div class="nav login_nav">
					<div class="col-xs-4 login_username">用户名:</div>
					<div class="col-xs-6 login_usernameInput">
						<input type="text" name="username" id="name" value=""
							placeholder="&nbsp;&nbsp;用户名/手机号" />
					</div>
				</div>
				<div class="nav login_psdNav">
					<div class="col-xs-4">密&nbsp;&nbsp;&nbsp;码:</div>
					<div class="col-xs-6">
						<input type="password" name="password" id="psd"
							placeholder="&nbsp;&nbsp;密码" />
					</div>
				</div>
				<div class="col-xs-12 login_cookie">
					<div class="col-xs-4"></div>
					<div class="col-xs-6">
						<div class="col-xs-6">
							<label><input type="checkbox" id="cookieBox"
								style="position: absolute;top:2px;"><span style="position: absolute;left:15px">下次自动登录</span></label>
						</div>
						<div class="col-xs-4">
							<a href="#">忘记密码？</a>
						</div>
					</div>
				</div>
				<div class="col-xs-12 login_btn_div">
					<input type="submit" class="sub_btn" id="login" value="登录" />
				</div>
				<div class="col-xs-12 login_msg" >
					<span  id="msg" style="display: none;"></span>
				</div>
			</div>
		</form>

		<div class="col-xs-12 barter_btnDiv">
			<span class="barter_btn">Copyright © Wangyh qq735789026 2199</span>
		</div>
	</div>

	<img src="<%=imagePath%>/index/9.jpg" width="100%" style="position: absolute;left:0px;">
</body>
</html>