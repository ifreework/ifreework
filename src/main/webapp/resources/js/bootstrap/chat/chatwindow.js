(function($) {

	var _add = function(obj, options) {
		var _options = {
			id : null,
			toUser : null,
			toUserName : null,
			type : 1
		// 1为发送给单个用户，2为发送给群组，默认为1
		};

		var opt = $.extend({}, defaults, _options, options);
		var _user_img = $("<div>").addClass("user_img").append(
				"<img src='main/userImgDownload'>");
				_user_name = $("<span>").addClass("user_name").text(
						opt.toUserName),
				_top = $("<div>")
						.addClass("chat_window_top")
						.append(_user_img)
						.append(_user_name)
						.append(
								'<i class="fa fa-minus" data-id="'
										+ opt.id
										+ '"></i> <i class="fa fa-times" data-id="'
										+ opt.id + '"></i>'),
				_nav = $("<ul>").addClass("nav"),
				_body = $("<div>").addClass("chat_window_body").append(_nav),

				_editer = ' <div class="widget-body">'
						+ ' <div class="btn-toolbar wysiwyg-toolbar" data-role="editor-toolbar" data-target="#editor-'
						+ opt.id
						+ '">'
						+ ' <div class="btn-group"> '
						+ ' <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="字体"><i class="fa fa-font"></i><b class="caret"></b></a>'
						+ ' <ul class="dropdown-menu">'
						+ ' <li><a data-edit="fontName Arial" style="font-family:\'Arial\'">Arial</a></li>'
						+ ' <li><a data-edit="fontName Sans" style="font-family:\'Sans\'">Sans</a></li>'
						+ ' <li><a data-edit="fontName 宋体" style="font-family:\'宋体\'">宋体</a></li>'
						+ ' </ul>'
						+ ' </div>'
						+ ' <div class="btn-group">'
						+ ' <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="字体大小"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a> '
						+ ' <ul class="dropdown-menu dropdown-default">'
						+ ' <li><a data-edit="fontSize 5"><font size="5">大号</font></a></li>'
						+ ' <li><a data-edit="fontSize 3"><font size="3">中号</font></a></li>'
						+ ' <li><a data-edit="fontSize 1"><font size="1">小号</font></a></li>'
						+ ' </ul>'
						+ ' <a class="btn btn-default" data-edit="bold" title="加粗 (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a> '
						+ ' <a class="btn btn-default" data-edit="italic" title="斜体 (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a> '
						+ ' <a class="btn btn-default" data-edit="strikethrough" title="过时线"><i class="fa fa-strikethrough"></i></a>'
						+ ' <a class="btn btn-default" data-edit="underline" title="下划线 (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>'
						+ ' <a class="btn btn-default" title="Insert picture (or just drag & drop)" id="pictureBtn-'
						+ opt.id
						+ '"><i class="fa fa-picture-o"></i></a>'
						+ ' <input type="file" data-role="magic-overlay" data-target="#pictureBtn-'
						+ opt.id
						+ '" data-edit="insertImage" /> </div> '
						+ ' <input type="text" data-edit="inserttext" class="wysiwyg-voiceBtn" id="voiceBtn-'
						+ opt.id
						+ '" x-webkit-speech=""> </div> '
						+ ' <div class="wysiwyg-editor" id="editor-'
						+ opt.id
						+ '"></div> </div>';
		_submit = $('<a class="btn btn-default pull-right submit" data-id="'
				+ opt.id + '">发送</a>');
		_cannel = $('<a class="btn btn-default pull-right cannel" data-id="'
				+ opt.id + '">取消</a>');
		_button_bar = $("<div class='btn-toolbar wysiwyg-button'>").append(
				_submit).append(_cannel), _footer = $("<div>").addClass(
				"chat_window_footer").append(_editer).append(_button_bar),

		_div = $("<div>").addClass("chat_window").attr("id",
				"chat-window-" + opt.id).data("options", opt).append(_top)
				.append(_body).append(_footer);
		obj.append(_div);

		_div.css({
			top : ($("body").height() - 550) / 2,
			left : ($("body").width() - 650) / 2
		});
		_initEvent(obj, opt);
		_initEditer(obj, opt);
	};

	var _initEvent = function(obj, opt) {
		obj.find("#chat-window-" + opt.id + " i.fa-times").click(function() {// 关闭事件
			var _id = $(this).data("id");
			$("#chat-window-" + _id).hide();
		});

		obj.find("#chat-window-" + opt.id + " a.submit").click(function() {// 发送事件
			var id = $(this).data("id"), msg = $("#editor-" + id).html();
			if (msg == null || msg == "") {
				$(this).tooltip({
					container : 'body',
					content : '发送消息不能为空！'
				});
			} else {
				_addChatMessage(id, msg, "left");

				var _num = Math.ceil(msg.length / 60000), _guid = _getguid(),
				_sendBody = {
					uuid : _guid,
					length : _num,
					fromUser : opt.fromUser,
					fromUserName : opt.fromUserName,
					toUser : opt.toUser,
					toUserName : opt.toUserName
				};

				for (var _i = 0; _i < _num; _i++) {
					var _str;
					if (_i == (_num - 1)) {
						_str = msg.substr(_i * 60000);
					} else {
						_str = msg.substr(_i * 60000, 60000);
					}
					_sendBody.msg = _str;
					_sendBody.order = _i;
					var _sendStr = JSON.stringify(_sendBody);
					_send(obj, opt, _sendStr);
				}
			}
		});
	};

	var _initEditer = function(obj, opt) {
		obj.find("#chat-window-" + opt.id + ' .dropdown-menu input').click(
				function() {
					return false;
				}).change(
				function() {
					$(this).parent('.dropdown-menu').siblings(
							'.dropdown-toggle').dropdown('toggle');
				}).keydown('esc', function() {
			this.value = '';
			$(this).change();
		});
		obj.find("#chat-window-" + opt.id + ' [data-role=magic-overlay]').each(
				function() {
					var overlay = $(this), target = $(overlay.data('target'));
					overlay.css('opacity', 0).css('position', 'absolute')
							.offset(target.offset()).width(target.outerWidth())
							.height(target.outerHeight());
				});
		if ("onwebkitspeechchange" in document.createElement("input")) {
			var editorOffset = $('#editor-' + opt.id).offset();
			$('#voiceBtn-' + opt.id).css('position', 'absolute').offset(
					{
						top : editorOffset.top,
						left : editorOffset.left
								+ $('#editor-' + opt.id).innerWidth() - 35
					});
		} else {
			$('#voiceBtn-' + opt.id).hide();
		}
		obj.find("#chat-window-" + opt.id + ' .wysiwyg-editor').wysiwyg();

		window.prettyPrint && prettyPrint();
	}

	var _init = function($this, options) {
//		var _websocket = new SockJS(options.server);
//		var stompClient = Stomp.over(_websocket);
//		stompClient.connect({}, function(frame) {
//			stompClient.subscribe("/user" + options.subscribe, function(data) { // 订阅消息
//				var _body = JSON.parse(data.body);
//				_addChatMessage(_body.fromUser,_body.msg,"right");
//			});
//		});
//		options.stomp = stompClient;
		$this.data('options', options);
	}

	var _send = function(obj, opt, msg) {
		var stomp = opt.stomp;
		stomp.send(opt.send, {}, msg);
	}

	var _addChatMessage = function(id, msg, cls) {
		var opt = $("#chat-window-" + id).data("options"), _img = $("<img>")
				.css({
					width : 40,
					height : 40,
					position : "relative",
					left : 15
				}), _contentDiv = $('<div  class="message-popover">').css({
			position : "relative",
			left : 30
		}).html(msg), _li = $("<li>").append(_img).append(_contentDiv);
		$("#chat-window-" + id).find(".chat_window_body ul").append(_li);

		if (cls == null || cls == "" || cls == "left") {
			_contentDiv.addClass("left").css({
				left : 30
			});
			_img.attr("src", "main/userImgDownload?username=" + opt.toUser).css({
				left : 15
			});
		} else {
			_contentDiv.addClass("right").css({
				left : 590 - 40 - 40 - _contentDiv.width()
			});
			_img.attr("src",
					"main/userImgDownload").css({
				left : 590
			});
		}

		var _div = $("#chat-window-" + id).find(".chat_window_body")[0];
		$("#editor-" + id).focus().html("");
		_div.scrollTop = _div.scrollHeight;
	};

	var _getguid = function() {
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
				function(c) {
					var r = Math.random() * 16 | 0, v = c == 'x' ? r
							: (r & 0x3 | 0x8);
					return v.toString(16);
				});
	};

	var methods = {
		init : function(options) {
			return this.each(function() {
				var $this = $(this);
				var settings = $this.data('options');
				if (typeof (settings) == 'undefined') {
					settings = $.extend({}, defaults, options);
				} else {
					settings = $.extend({}, settings, options);
				}
				// 代码在这里运行
				_init($this, settings);
			});
		},
		open : function(opt) {
			return $(this).each(function() {
				var $this = $(this);
				console.log();
				console.log(opt);
				_add($this, $.extend({}, $this.data("options"), opt));
			});
		}
	}

	$.fn.chatwindow = function() {
		var method = arguments[0];
		if (methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if (typeof (method) == 'object' || !method) {
			method = methods.init;
		} else {
			$.error('Method ' + method
					+ ' does not exist on jQuery.chatwindow.');
			return this;
		}
		return method.apply(this, arguments);

	}

	var defaults = {
		server : null, // websocket服务器地址
		send : null, // 消息发送地址
		subscribe : null,// 消息接收地址
		fromUser : null,// 发送人
		fromUserName : "",
	}

})(jQuery);