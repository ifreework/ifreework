
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
		u,
		r,
		f;
		if (i && i.length != 0) {
			if (!i.hasClass("menu-dropdown")){
				var url = i.data("url");
				if(!isNull(url)){
					var nowTime = new Date().getTime();
					if(url.indexOf("?")!=-1) {
						url += "&_time=" + nowTime;
					}else{
						url += "?_time=" + nowTime;
					}
					window.frames['ifm'].location.href = url;
				}
			} //如果点击的a标签含有下拉菜单，则显示下拉菜单
				
			if (r = i.next().get(0), !$(r).is(":visible")) {
				if (f = $(r.parentNode).closest("ul"), n && f.hasClass("sidebar-menu")) return;
				f.find("> .open > .submenu").each(function() {
					this == r || $(this.parentNode).hasClass("active") || $(this).slideUp(200).parent().removeClass("open")
				})
			}
			return n && $(r.parentNode.parentNode).hasClass("sidebar-menu") ? !1 : ($(r).slideToggle(200).parent().toggleClass("open"), !1)
		}
	})
}


//打开编辑头像界面
function openUserImg(){
	var dialog = bootbox.dialog({
		title: "头像设置",
		height:485,
		width:740,
        message: "<iframe id='userChangeImgDialog' name='userChangeImgDialog'  src='main/userChangeImg'></iframe>",
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
}

//打开用户编辑界面
function openUserEdit(username){
	var url = "system/user/edit?username=" + username,
	    t = new Date().getTime();
	url += "&_time=" + t;
	window.frames['ifm'].location.href = url;
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
    	}
    	
    });
}


//当页面大小发生改变时，修改iframe的高度
function initFrameHeight(){
	var bodyHeight = $("body").height();
	console.log("bodyHeight:" +  bodyHeight);
	$("#ifm").height(bodyHeight - 101);
}


//页面加载完成后，执行该方法
function _pageLoaded(){
	InitiateSideMenu();
	initFrameHeight();
	initUserDropDown();
	$(window).resize(initFrameHeight);
}

document.addEventListener('DOMContentLoaded', _pageLoaded, false);

/*
 * //# sourceMappingURL=beyond.min.js.map
 */
