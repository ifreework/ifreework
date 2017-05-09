(function() {

	/**
	 * 当页面大小发生改变时，修改PageBody的高度
	 */
	function changePageBodyHeight() {
		var bodyHeight = $("body").height();
		console.log("Body Height:" + bodyHeight);
		$("#page-body").css({
			minHeight : bodyHeight - 85
		});
	}

	/**
	 * 初始化用户下拉框点击事件
	 */
	function initUserDropDown() {
		$("#user-manager ul.dropdown-menu").click(function(t) {
			
			var _a = $(t.target).closest("a"), // 获取点击的a标签
			id = _a.data("id");
			
			console.log(_a);
			
			if (id === "user-img") {
				openUserImg();
			} else if (id === "user-edit") {
				var username = _a.data("username");
				openUserEdit(username);
			} else if (id === "password-reset") {
				openResetPwd();
			} else if (id === "logout") {
				location.href = "logout";
			}

		});
	}
	
	// 打开用户编辑界面
	function openUserEdit(username){
		var url = "system/user/edit";
		W.openPage(url,{username:username});
	}
	
	
	//打开编辑头像界面
	function openUserImg(){
		var dialog = bootbox.dialog({
			id:"userImgDialog",
			title: "头像设置",
			width:700,
	        loadUrl: "main/userChangeImg"
	    });
	}
	
	
	//打开修改密码界面
	function openResetPwd(){
		var dialog = bootbox.dialog({
			id:"userImgDialog",
			title: "密码重置",
			width:700,
	        loadUrl: "system/user/changePwd"
	    });
	}
	
	/**
	 * 初始化菜单切换按钮
	 */
	function initiateSideMenuCompact() {
		// 菜单模式切换按钮
		$("#sidebar-collapse").on("click",function() {
			if(!$("#sidebar").is(":visible")){
				$("#sidebar").toggleClass("hide");
			}
			
			$("#sidebar").toggleClass("menu-compact");  //
			$(".sidebar-collapse").toggleClass("active");
			
			compact = $("#sidebar").hasClass("menu-compact");
			
			if(compact){
				$("#sidebar .open > .submenu").removeClass("open");
			}
		});
	}
	
	/**
	 * 初始化菜单
	 */
	function initiateSideMenu(){
		var compact = $("#sidebar").hasClass("menu-compact");// 菜单是否为compact模式
		
		$(".sidebar-menu").on("click",function(t) {
			var _a = $(t.target).closest("a"),// 获取点击的a标签
			ul = _a.closest("ul"),
			u,
			r,
			f;
			if (_a && _a.length != 0) {
				if (!_a.hasClass("menu-dropdown")){
					var url = _a.data("url");
					if(!W.isNull(url)){
						$(".sidebar-menu").find("li.active").removeClass("active");
						_a.closest("li").addClass("active");
						changeBreadcrumb(_a);
						W.openPage(url);
					}
				} else {// 如果点击的a标签含有下拉菜单，则显示下拉菜单
					if (r = _a.next().get(0), !$(r).is(":visible")) {
						if (f = $(r.parentNode).closest("ul"), compact && f.hasClass("sidebar-menu")) return;
						f.find("> .open > .submenu").each(function() {
							this == r || $(this.parentNode).hasClass("active") || $(this).slideUp(200).parent().removeClass("open")
						})
					}
				} 
				return compact && $(r.parentNode.parentNode).hasClass("sidebar-menu") ? !1 : ($(r).slideToggle(200).parent().toggleClass("open"), !1)
			}
		});
	}
	
	function changeBreadcrumb(_a) { // 重置页面标头导航
		var parentsLi, // 父级li节点
		a, // 父级a节点
		text, _li, _a;
		$("#ul-breadcrumb").html("");

		parentsLi = _a.parents("li");
		console.log(parentsLi);

		$.each(parentsLi, function(index, element) {
			a = $(element).find("> a ");
			text = a.find(" > .menu-text");

			_li = $("<li></li>");
			if(index == 0){
				_li.addClass("active");
			}
			if (parentsLi.length - 1 == index) {
				_li.append('<i class="fa fa-home"></i>');
			}

			_a = $('<a href="javascript:void(0)" >' + text.text() + '</a>');

			if (a.data("url") != null && a.data("url") != "") {
				_a.data("url", a.data("url"));
			}
			_li.append(_a);
			$("#ul-breadcrumb").prepend(_li);
		});
		
		$("#ul-breadcrumb li a").unbind("click").on("click",W.breadcrumbLiClick);
	}
	
	$().ready(function(){
		initiateSideMenuCompact();
		initiateSideMenu();
		changePageBodyHeight();
		initUserDropDown();
		$(window).resize(changePageBodyHeight);
	});

}());