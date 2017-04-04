
var monthNames = [ "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月",
		"11月", "12月" ];

var dayNames = [ "周日", "周一", "周二", "周三", "周四", "周五", "周六" ]

function initClock(){
	var newDate = new Date();
	var h = newDate.getHours() > 9 ? newDate.getHours() : '0' + newDate.getHours();
	var m = newDate.getMinutes() > 9 ? newDate.getMinutes() : '0' + newDate.getMinutes();
	var s = newDate.getSeconds() > 9 ? newDate.getSeconds() : '0' + newDate.getSeconds();
	$('#date span').html(
			newDate.getFullYear() + "年" + monthNames[newDate.getMonth()] 
					+ newDate.getDate() + "日  " + h + ":" + m + ":"
					+ s  + " " + dayNames[newDate.getDay()]);
}
initClock();
setInterval(initClock,1000);