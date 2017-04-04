(function($) {
	var privateFunction = function() {
		// 代码在这里运行
	}
	
	_init = function(obj,options){ 
		obj.addClass("sidebar-body");
		var ul_ = obj.find("ul").first();
		ul_.addClass("sidebar").addClass("nav").addClass("nav-list");
	}
	
	var defaults = {
		mini : true,
		onClick:function(){
			
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
				_init($this,settings);
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
		message : function(options) {
			alert(123)
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