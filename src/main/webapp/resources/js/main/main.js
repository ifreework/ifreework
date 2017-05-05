
/**
 * 初始化菜单及菜单切换按钮
 */
function InitiateSideMenu() {
	
	var n = $("#sidebar").hasClass("menu-compact");//菜单是否为mini模式
	
	//菜单模式切换按钮
	$("#sidebar-collapse").on("click",function() {
		if(!$("#sidebar").is(":visible")){
			$("#sidebar").toggleClass("hide");
		}
		
		$("#sidebar").toggleClass("menu-compact");
		$(".sidebar-collapse").toggleClass("active");
		n = $("#sidebar").hasClass("menu-compact");
		
		if(n){
			$(".open > .submenu").removeClass("open");
		}
	});
	
	
	$(".sidebar-menu").on("click",function(t) {
		var i = $(t.target).closest("a"),//获取点击的a标签
		ul = i.closest("ul"),
		u,
		r,
		f;
		if (i && i.length != 0) {
			if (!i.hasClass("menu-dropdown")){
				var url = i.data("url");
				if(!isNull(url)){
					$(".sidebar-menu").find("li.active").removeClass("active");
					i.closest("li").addClass("active");
					W.openPage(url);
				}
			} else {//如果点击的a标签含有下拉菜单，则显示下拉菜单
				if (r = i.next().get(0), !$(r).is(":visible")) {
					if (f = $(r.parentNode).closest("ul"), n && f.hasClass("sidebar-menu")) return;
					f.find("> .open > .submenu").each(function() {
						this == r || $(this.parentNode).hasClass("active") || $(this).slideUp(200).parent().removeClass("open")
					})
				}
			} 
			return n && $(r.parentNode.parentNode).hasClass("sidebar-menu") ? !1 : ($(r).slideToggle(200).parent().toggleClass("open"), !1)
		}
	})
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

//打开用户编辑界面
function openUserEdit(username){
	var url = "system/user/edit";
	W.openPage(url,{username:username});
}

//初始化用户下拉框点击事件
function initUserDropDown(){
    $("#user-manager ul.dropdown-menu").click(function(t){
    	console.log(t);
    	var i = $(t.target).closest("a"),//获取点击的a标签
    	    id = i.data("id");
    	if(id === "user-img"){
    		openUserImg();
    	} else if( id === "user-edit"){
    		var username = i.data("username");
    		openUserEdit(username);
    	} else if( id === "logout"){
    		 location.href = "logout";
    	}
    	
    	
    });
}


//当页面大小发生改变时，修改iframe的高度
function initFrameHeight(){
	var bodyHeight = $("body").height();
	console.log(bodyHeight);
	$("#page-body").css({
		minHeight:bodyHeight - 85
	});
}


//页面加载完成后，执行该方法
function _pageLoaded(){
	InitiateSideMenu();
	initFrameHeight();
	initUserDropDown();
	$(window).resize(initFrameHeight);
}


$().ready(function(){
	_pageLoaded();
});

/*
 * //# sourceMappingURL=beyond.min.js.map
 */
