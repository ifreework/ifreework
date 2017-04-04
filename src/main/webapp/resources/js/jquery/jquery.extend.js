/*
 *该文件为js和easyui的一些扩展类 
 */
/*
 * 修改后的alert
 */
$.alert = function (msg, fn, icon, title) {
    if (icon == null) {
        icon = "info";
    }
    if (title == null) {
        title = " ";
    }
    $.messager.alert(title, msg, icon, fn);
}

/**
 * 通知提示
 * msg 提示信息
 * standards 水平位置"left,center,right"
 * vertical 垂直位置"top,center,bottom"
 * cls class
 */
$.notify = function (msg, standards, vertical, cls) {
    var _id = "_qazwsxedc_wangyh_" + standards + "_" + vertical,
        _notify = $("body").find("#" + _id),
        _bodyId = new Date().getTime(),
        _notifyBody = $("<div class='notify-body'>").attr("id",_bodyId),
        _icon = $("<i class='fa fa-bell shake shake-hard notify-icon'>"),
        _span = $("<span>").html(msg),
        _close = $("<i class='fa fa-times'>");
    if (_notify.length == 0) {
        _notify = $("<div>").attr("id", _id).addClass("notify");
        var _audio = $("<audio autoplay='false'> <source = src='resources/css/sound/notify.mp3' type='audio/mp3' ></audio>");
        _notify.append(_audio);
        $("body").append(_notify);
        if (standards != "center") {
            _notify.addClass(standards);
        }
        if (vertical != "center") {
            _notify.addClass(vertical);
        }
    }

    _notifyBody.append(_icon).append(_span).append(_close);
    _notify.append(_notifyBody);
    _notify.find("audio").get(0).play();
    _close.click(function () {
        $(this).parent().remove();
    });

    if (standards == "center") {
        _notify.css({
            left: ($("body").width() - $(this).width()) / 2
        });
    }
    if (vertical == "center") {
        _notify.css({
            top: ($("body").height() - $(this).height()) / 2
        });
    }
    var _t =  '$("#' + _id + '").find("#' + _bodyId + ' .notify-icon").removeClass("shake").removeClass("shake-hard")';
    var _o = '$("#' + _id + '").find(".notify-body:first").remove()';
    setTimeout(_t,1000);
    setTimeout(_o, 10000);
}