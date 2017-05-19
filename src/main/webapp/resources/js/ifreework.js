/**
 * 命名空间，防止对象冲突
 */
$.namespace = function() {
	var a = arguments, o = null, i, j, d;
	for (i = 0; i < a.length; i = i + 1) {
		d = a[i].split(".");
		o = window;
		for (j = 0; j < d.length; j = j + 1) {
			o[d[j]] = o[d[j]] || {};
			o = o[d[j]];
		}
	}
	return o;
};

$.namespace("W"); // 创建公共命名空间W
W = function() {
	var historyArray = [], // 用于记录用户请求路径
	indexPath = "system/index";
	首页地址

	return {
		
		getContextPath : function() { // 获取项目名
			var pathName = window.document.location.pathname;
			var projectName = pathName.substring(0, pathName.substr(1).indexOf(
					'/') + 1);
			return projectName;
		},
		
		jsonArrayToString : function(array) { // 将Array[json]转换成json字符串
			if($.isArray(array)){
				var str = "[";
				for (var i = 0; i < array.length; i++) {
					if (i == array.length - 1) {
						str += JSON.stringify(array[i]);
					} else {
						str += JSON.stringify(array[i]) + ",";
					}
				}
				str += "]";
				return str;
			}
			
			console.error("%d is not Array!",array);
		},
		
		arrayRemove : function(array,obj){ //删除array中的某个对象
			var i = $.inArray( obj, array );
			if(i >= 0 ){
				array.splice(i,1);
			}
		},
		
		history : function() { // 返回上一页
			if (W.historyArray.length == 0) { // 如果没有历史记录，则不进行任何操作
				return;
			}
			if (W.historyArray.length == 1) { // 如果只打开了一个界面，则返回首页

			}
		}
	}
};
