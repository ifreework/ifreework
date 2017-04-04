(function(jQuery) {
	jQuery.fn.weather = function(options) {
		var defaults = {
			city : null
		};
		var _this = this;
		var opts = jQuery.extend(defaults, options);

		var src = "main/getWeather";
		$.ajax({
			url : src,
			success : function(data) {
				if (data != null && data != '') {
					initHtm(data);
				}
			}
		});
	}

	function initHtm(data) {
		data = data.replace(/\\t/g, " ");
		data = eval(data);
		var div = $("<div>");
		div.hide();
		div.append(data);
		var local = div.find(".cityname").text();// 地址
		var currentWeather = div.find(".divCurrentWeather").text();
		console.log(currentWeather);
		var arrayStr = currentWeather.split(" ");
		var array = new Array();
		var temperature = "";// 温度
		var tq = "" // 天气
		if (arrayStr.length > 0) {
			for (var i = 0; i < arrayStr.length; i++) {
				if (arrayStr[i] != "") {
					array.push(arrayStr[i]);
				}
			}
			for (var i = 0; i < array.length; i++) {
				var index = array[i].lastIndexOf("℃")
				if (index > 0) {
					temperature = array[i].substring(0, index + 1);
				}
			}
			tq = choseWeather(array.length > 0  ? array[0] : "");
			$("#weather").find("i").removeClass().addClass(tq).attr("title",array.length > 0  ? array[0] : "");
			$("#weather").find("span").text(local + " " + temperature);
		}
	}

	function choseWeather(w) {
		var h;
		switch (w) {
		case "晴":
			h = "wi-day-sunny";
			break;
		case "多云":
			h = "wi-day-cloudy";
			break;
		case "阴":
			h = "wi-cloud";
			break;
		case "大雨":
			h = "wi-rain";
			break;
		default:
			h = "wi-day-down";
		}
		return h;
	}

})(jQuery);
