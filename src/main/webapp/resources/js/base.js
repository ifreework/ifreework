/*
 * 该JS中包涵一些常用的公共方法
 */

/*
 * 判断字符串是否为空字符串
 */
function isNull(str) {
	return str == null || str == "";
}

/*
 * 通过名字获取页面路径中的参数
 */
function getRequestParamByName(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

/**
 * 对jquery ajax的二次封装，添加的请求前后遮罩动画处理
 * @param opt
 */
function ajax(opt){
	var opts = {
			type:'POST',
			dataType : "json",
			beforeSend : function(){
				bootbox.load();
			},
			error : function(jqXHR, textStatus, errorThrown){
				if(textStatus == "timeout"){
					bootbox.alert("网络连接超时，请稍后再试。");
				}
				if(textStatus == "error"){
					bootbox.alert("系统出现异常，请稍后再试。");
				}
			},
			complete : function(xhr, ts){
				bootbox.unload();
			}
		};
		var  o = $.extend(opts,opt);
		$.ajax(o);
}


/**
 * canvas图片压缩
 * 
 * @param {[Object]}
 *            opt [配置参数]{src:文件路径，或者为base64编码图片，
 *            				type:是否预览，1/0，
 *            				width:压缩后宽度，
 *            				height:压缩后高度}
 * @param {[Function]}
 *            cbk [回调函数]
 * @return {图片base64编码后的字符串};
 */
function writeDom(opt, cbk) {
	var _opt = $.extend({
		sx : 0, sy : 0, swidth:0, sheight:0, x:0, y:0, width:0, height:0
	},opt), _cbk = cbk;
	$('#_img,#_canvas,#img-c').remove();
	$('body').append($('<canvas id="_canvas" style="display: none;"></canvas><img id="img-c" src="" style="display:none;"/><img id="_img" src="" style="width:300px;"/>'));
	_image = new Image();
	_image.src = _opt.src || "";
	$('#img-c').attr('src', _opt.src)[0].onload = function() {
		var _this = $(this);
		var _canvas = document.getElementById('_canvas');
		_canvas.width = _opt.width;
		_canvas.height = _opt.height;
		var _context = _canvas.getContext('2d');
		_context.drawImage(_image,  _opt.sx, _opt.sy, _opt.swidth, _opt.sheight, _opt.x, _opt.y, opt.width, opt.height);
		if (_opt.type) {
			$('#img').attr('src', _canvas.toDataURL('image/jpeg'));
		}
		;
//		
		_cbk(_canvas.toDataURL('image/jpeg'));
		$('#_img,#_canvas,#img-c').hide();
		
	};
}


/**
 * alert弹出框，load加载遮罩
 */
$.extend(bootbox, {
	alert : function(msg,title,fn) {
		bootbox.dialog({
	        message: '<div class="row"><div class="col-md-12"><span class="alert-text">'+ msg +'</span></div></div>',
	        title: '<i class="typcn typcn-info-outline"></i>&nbsp;&nbsp;' + (title == null || title == "" ? "提示" : title),
	        className: "modal-darkorange modal-alert",
	        closeButton : false,
	        buttons: {
	            success: {
	                label: "确定",
	                className: "btn-default",
	                	callback: fn
	                }
	        }
		});
	},
	load : function(){
		var loadDiv = $(".loading-container",window.top.window.document);
		if(loadDiv.length == 0 ){
			var html = '<div class="loading-container"><div class="loading-progress"><div class="rotator"><div class="rotator"><div class="rotator colored"><div class="rotator"><div class="rotator colored"><div class="rotator colored"></div><div class="rotator"></div></div><div class="rotator colored"></div></div><div class="rotator"></div></div><div class="rotator"></div></div><div class="rotator"></div></div><div class="rotator"></div></div></div>';
			$("body",window.top.window.document).append(html);	    
		}else{
			loadDiv.removeClass("loading-inactive");
		}
	},
	unload : function(){
		var loadDiv = $(".loading-container",window.top.window.document);
		loadDiv.addClass("loading-inactive");
	}
});


/**
 * 添加jquery组件，将form序列成json返回
 * @param $
 */
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value;   
            }  
        });  
        return serializeObj;  
    };  
})(jQuery);  



