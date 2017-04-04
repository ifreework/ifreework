(function($) {

	var add_ = function(obj, options) {//
		var defaults = {
			id : "",
			text:"",
			parentId : "",
			children : [],
			root : false,
			icon : [],
			onClick : function() {

			},
			onOpened : function() {

			}
		};
		var opt = $.extend({}, defaults, options);

		var parentNode_;
		if (opt.root) {// 如果是根节点的话
			parentNode_ = obj;
		} else {
			parentNode_ = obj.find("#" + opt.parentId);
			if (parentNode_.length == 0) {
				console.log("Node id '" + opt.parentId + "' can not find!");
			}
		}
		var ul_ = parentNode_.find(">ul");
		if (ul_.length == 0) {
			ul_ = $("<ul></ul>");
			parentNode_.append(ul_);
		}
		if (opt.root) {// 如果是根节点的话
			ul_.addClass("nav").addClass("nav-list").addClass("sidebar");
		}
		
		var li_ = $("<li>");  //子节点信息
		li_.attr("id",opt.id);
		var a_ = $("<a>");
		a_.attr("href","#");
		
		var i_ = $("<i>");
		i_.addClass("menu-icon");
		if(typeof(opt.icon) == 'string'){
			i_.addClass(opt.icon);
		}
		if(opt.icon instanceof Array){
			for(var i = 0;i < opt.icon.length ; i++){
				i_.addClass(opt.icon[i]);
			}
		}
		
		var span_ = $("<span>");
		span_.html(opt.text);
		
		var b_ = $("<b>");
		
		if(opt.root){
			span_.addClass("menu-text");
		}
		
		
		a_.append(i_).append(span_).append(b_);
		li_.append(a_);
		ul_.append(li_);

		if(opt.children.length != 0 ){
			b_.addClass("icon-chevron-down");
			a_.click(function(){
				_bindClick(this,options);
			});
		}
		
		
		for(var k= 0 ; k < opt.children.length ; k++ ){
			opt.children[k].parentId = opt.id;
			add_(obj,opt.children[k]);
		}
	}

	var _bindClick = function(obj, options) {// 绑定按钮点击事件
		var $li = $(obj).parent();
		if ($li.find("ul").length == 0) {// 最低级菜单
			$(obj).bind("click",this.onClick ? this.onClick : options.onClick ? options.onClick : function() {});
		} else {
			if ($li.hasClass("opened")) {
				$li.removeClass("opened");
				$li.find("ul").first().slideUp(300);
				$(obj).find(">b").removeClass("icon-chevron-up");
				$(obj).find(">b").addClass("icon-chevron-down");
			} else {
				var $open = $li.prevAll("li.opened").length == 0 ? $li
						.nextAll("li.opened") : $li.prevAll("li.opened");
				$open.find("ul").first().slideUp(300);
				$(obj).find(">b").removeClass("icon-chevron-down");
				$(obj).find(">b").addClass("icon-chevron-up");
				$open.removeClass("opened");
				$li.addClass("opened");
				$li.find("ul").first().slideDown(300);
			}
		}
	}

	var _initCollapse = function(obj, options) {// 初始化mini模式切换按钮
		var collapse_ = $("<div></div>");
		var iconLeft_ = $("<i></i>");
		collapse_.addClass("sidebar-toggle").addClass("sidebar-collapse");
		iconLeft_.addClass("sidebar-collapse-icon").addClass(
				"fontawesome-double-angle-left");
		collapse_.append(iconLeft_);
		obj.append(collapse_);

		collapse_.click(function() {
			obj.find(".opened").removeClass("opened");
			obj.find("a").unbind("click");
			obj.find("ul").css({
				display : ""
			});

			var i_ = $(this).find("i");
			if (i_.hasClass("fontawesome-double-angle-right")) {
				i_.removeClass("fontawesome-double-angle-right");
				i_.addClass("fontawesome-double-angle-left");

				obj.removeClass("mini");

				obj.find("a").click(function() {
					_bindClick(this);
				});
			} else {
				i_.removeClass("fontawesome-double-angle-left");
				i_.addClass("fontawesome-double-angle-right");

				obj.addClass("mini");

				obj.find("> ul > li > ul  a").click(function() {
					_bindClick(this);
				});
			}

		});
	}

	var _init = function(obj, options) {// 初始化sidebar控件
		obj.addClass("sidebar-body");
		var ul_ = obj.find(">ul");
		if (!ul_) {
			ul_ = $("<ul>")
			obj.append(ul_);
		}

		ul_.addClass("nav").addClass("nav-list").addClass("sidebar");
		ul_.find("> li>a>i,> li > ul > li > a > i").addClass("menu-icon");
		ul_.find("> li>a>span").addClass("menu-text");
		ul_.find("li").each(function() {
			if ($(this).find("ul").length > 0) {
				$(this).find(">a").append("<b class='icon-chevron-down'></b>");
			}
		});

		ul_.find("a ").click(function() {
			_bindClick(this);
		});

		if (options.mini) {
			_initCollapse(obj, options);
		}

	}

	var defaults = {
		mini : true,
		onClick : function() {

		}
	}

	var methods = {
		init : function(options) {
			return this.each(function() {
				var $this = $(this);
				var settings = $this.data('options');
				if (typeof (settings) == 'undefined') {
					settings = $.extend({}, defaults, options);
					$this.data('options', settings);
				} else {
					settings = $.extend({}, settings, options);
				}
				// 代码在这里运行
				_init($this, settings);
			});
		},
		destroy : function(options) {
			return $(this).each(function() {
				var $this = $(this);

				$this.removeData('sidebar');
			});
		},
		val : function(options) {
			var someValue = this.eq(0).html();

			return someValue;
		},
		add:function(options){
			add_($(this),options);
		}
	};

	$.fn.sidebar = function() {
		var method = arguments[0];
		if (methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if (typeof (method) == 'object' || !method) {
			method = methods.init;
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.sidebar.');
			return this;
		}
		return method.apply(this, arguments);

	}

})(jQuery);