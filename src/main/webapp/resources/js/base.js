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

/* common */
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

