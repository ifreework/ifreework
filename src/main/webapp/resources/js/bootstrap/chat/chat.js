(function ($) {

    var defaults = {
        userUrl: "",// 获取联系人地址
        server: null, // websocket服务器地址
        send: null, // 消息发送地址
        subscribe: null,// 消息接收地址
        fromUser: null,// 发送人
        fromUserName: "",
        onLine: true // 登录后是否发送上线消息，默认发送
    }

    //初始化init
    var _initWebSocket = function (obj, options) {
        var _websocket = new SockJS(options.server); 
        var stompClient = Stomp.over(_websocket);
        options.stomp = stompClient;
        stompClient.connect({}, function (frame) {
            _connect(obj,options,frame);
        }, function (error) {
            _disconnect(obj,options,error);
        });
        obj.data('options', options);
    }

    //websocket连接成功后执行的办法
    var _connect = function(obj, options,frame){
        _initChatWindow(obj, options); //初始化chatwindow组件
        options.stomp.subscribe("/user" + options.subscribe, function (data) { // 订阅消息
            var _body = JSON.parse(data.body);
        });
    }

    var _disconnect = function(obj, options,error){
        $.notify("聊天系统登录失败，请与管理员联系。","right","bottom");
        options.stomp.isOnl
        console.log(error);
    }

    // 初始化chatwindow
    var _initChatWindow = function (obj, options) {
        var _chatWindow = obj.find("#chat-win").chatwindow({
            stomp: options.stomp
        });
        options.chatwindow = _chatWindow;
    }
    // 获取联系人信息
    var _ajaxUser = function (obj, options) {
        // 获取联系人信息
        $.ajax({
            url: options.userUrl,
            dataType: "json",
            data: {
                _data: new Date().getTime()
            },
            success: function (data) {
                _initUser(obj, data);
            }
        });
    }

    //初始化联系人信息
    var _initUser = function (obj, data) {
        var _onLinks = _getLiArray(obj, data.onLineContacts);
        var _allLinks = _getLiArray(obj, data.allContacts);
        for (var k = 0; k < _onLinks.length; k++) {
            obj.find("#onLine_friends ul").append(_onLinks[k]);
        }
        for (var i = 0; i < _allLinks.length; i++) {
            obj.find("#my_friends ul").append(_allLinks[i]);
        }
        obj.find("#my_friends span b").text(data.onLineNum + "/" + data.allNum);
    }

    //初始化联系人信息，并绑定点击事件
    var _getLiArray = function (obj, array) {
        var _html = new Array();
        for (var m = 0; m < array.length; m++) {
            var _li = $("<li>").addClass("contact-user").append("<a href='#'>");
            _li.find("a").append("<img>").append("<span>");
            _li.find("a img").attr(
                "src",
                "main/contractImgDownload?imgPath=" + array[m].imgPath
                + "&isOnline=" + array[m].isOnline);
            _li.find("a span").text(array[m].name);
            _li.data("options", array[m]);
            _li.click(function () {
                var _opt = _li.data("options");
                _openWindow(obj, _opt);
            });
            _html.push(_li);
        }
        return _html;
    }

    //打开聊天窗口
    var _openWindow = function (obj, _li) {
        var _opt = obj.data("options");
        _opt.chatwindow.chatwindow("open", {
            id: _li.userName,
            toUser: _li.userName,
            toUserName: _li.name
        });
    }
    //初始化事件
    var _initEvent = function (obj, opt) {
        // 联系人div显示隐藏切换
        obj.find(".contact-bottom").click(function () {
            var _div = obj.find(".contact-body");
            var _that = $(this);
            if (!_that.hasClass("active")) {
                _that.addClass("active");
                _div.show();
                _div.animate({
                    height: "550px"
                });
            } else {
                _div.animate({
                    height: "0px"
                }, function () {
                    _div.hide();
                    _that.removeClass("active");
                });
            }
        });

        // 联系人折叠与显示
        obj.find(".chat-user > li > a").click(function () {
            var _i = $(this).parent().find("i").first();
            if (_i.hasClass("fa-caret-down")) {
                _i.removeClass("fa-caret-down");
                _i.addClass("fa-caret-right");
                $(this).parent().find("ul").hide();
            } else {
                _i.removeClass("fa-caret-right");
                _i.addClass("fa-caret-down");
                $(this).parent().find("ul").show();
            }

        });

    }

    var methods = {
        init: function (options) {
            return this.each(function () {
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
        open: function (opt) {
            return $(this).each(function () {
                var $this = $(this);
                console.log();
                console.log(opt);
                _add($this, $.extend({}, $this.data("options"), opt));
            });
        }
    }

    $.fn.chat = function () {
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

    var _init = function (obj, options) {
        var _body = ' <div class="contact-body" style="height: 0px; display: none;">'
                + ' <div class="contact-body-top"> '
                + ' <div class="form-group"> '
                + ' <span class="input-icon">'
                + ' <input type="text" class="form-control input-sm"> '
                + ' <i class="searchicon fa fa-search blue"></i>'
                + ' </span>'
                + ' </div>'
                + ' </div>'
                + ' <div class="contact-body-content">'
                + ' <div class="tabbable tabs-below">'
                + ' <ul class="nav nav-tabs nav-justified"> '
                + ' <li class="active"> '
                + ' <a data-toggle="tab" href="#goodFriends"> '
                + ' <i class="icon icon-user">'
                + ' </i> '
                + ' </a> '
                + ' </li>'
                + ' <li> '
                + ' <a data-toggle="tab" href="#userGroups">'
                + ' <i class="icon icon-user-group">'
                + ' </i>'
                + ' </a>'
                + ' </li>'
                + ' <li>'
                + ' <a data-toggle="tab" href="#lastContracts">'
                + ' <i class="entypo-clock">'
                + ' </i>'
                + ' </a> '
                + ' </li> '
                + ' </ul>'
                + ' <div class="tab-content"> '
                + ' <div id="goodFriends" class="tab-pane in active"> '
                + ' <ul class="chat-user"> '
                + ' <li id="onLine_friends"><a href="#"><i class="fa fa-caret-right"></i><span>在线好友 </span> </a> <ul></ul></li> '
                + ' <li id="my_friends"><a href="#"><i class="fa fa-caret-right"></i><span>我的好友 <b></b></span></a> <ul></ul></li> '
                + ' </ul>'
                + ' </div>'
                + ' <div id="userGroups" class="tab-pane">'
                + ' <ul class="chat-user">'
                + ' <li><a href="#"><i class="fa fa-caret-right"></i><span>我的群组</span></a><ul></ul></li> '
                + ' <li><a href="#"><i class="fa fa-caret-right"></i><span>常用群组</span></a><ul></ul></li> '
                + ' </ul>'
                + ' </div>'
                + ' <div id="lastContracts" class="tab-pane">'
                + ' <ul class="chat-user">'
                + ' <li class="contact-user"><a href="#"><img src="main/userImgDownload"><span>爱新觉罗玄烨</span><span class="time">15:12</span></a></li>'
                + ' </ul>'
                + ' </div>'
                + ' </div>'
                + ' </div>'
                + ' </div>'
                + ' </div>',
            _bottom = ' <div class="contact-bottom glyphicon glyphicon-send"></div>',
            _chatwindow = '<div id="chat-win"></div>';

        obj.addClass("chat").append(_body).append(_bottom).append(_chatwindow);

        _initWebSocket(obj, options);

        _ajaxUser(obj, options);
        _initEvent(obj, options);
    }

})(jQuery);