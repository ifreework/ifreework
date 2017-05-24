<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script src="${jsPath }/websocket/sockjs.js"></script>
<script src="${jsPath }/websocket/stomp.js"></script>

<script type="text/javascript">
	$.namespace("system.memery");

	system.memery = function() {
		var systemMemery, websocket, stompClient, memeryChart, labels;

		function connect() {
			websocket = new SockJS("${contextPath}/websocket");
			stompClient = Stomp.over(websocket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/memeryScale', function(data) { //订阅消息
					console.log(data);
					labels.shift();
					labels.push(formatDate(new Date()));
					memeryChart.data.datasets[0].data = eval(data.body);
					memeryChart.data.labels = labels;
					memeryChart.update();
				});

			});
		}

		function setLabels() {
			var now;
			labels = [];
			now = new Date();
			for (var i = 30; i > 0; i--) {
				now.setMinutes(now.getMinutes() - 1);
				labels.unshift(formatDate(now));
			}
		}

		function initChart() {
			var ctx = systemMemery.find("#memeryChart");
			memeryChart = new Chart(ctx, {
				type : 'line',
				data : {
					labels : labels,
					datasets : [ {
						label : "内存使用（%）",
						fill : false,
						lineTension : 0.1,
						backgroundColor : "rgba(75,192,192,0.4)",
						borderColor : "#fb6e52",
						borderCapStyle : 'butt',
						borderDash : [],
						borderDashOffset : 0.0,
						pointBorderColor : "#ffce55",
						pointBackgroundColor : "#ffce55",
						pointBorderWidth : 3,
						pointHoverRadius : 5,
						pointHoverBackgroundColor : "#ffce55",
						pointHoverBorderColor : "rgba(220,220,220,1)",
						pointHoverBorderWidth : 3,
						pointRadius : 1,
						pointHitRadius : 10,
						data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
						spanGaps : false
					} ]
				},
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                	max: 100,
			                    min: 0,
			                    stepSize: 10
			                }
			            }]
			        }
			    }
			});
		}

		function formatDate(date) {
			var hour = date.getHours();
			var minutes = date.getMinutes();
			hout = hour < 10 ? "0" + hour : hour;
			minutes = minutes < 10 ? "0" : minutes;
			return hour + ":" + minutes;
		}
		
		
		return {
			init : function(){
				systemMemery = $("#system-memery");
				connect();
				setLabels();
				initChart();
			}
		};
	}();
	
	$().ready(function(){
		system.memery.init();
	});
</script>
<div class="container-content" id="system-memery">
	<div class="container-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="widget">
					<div class="widget-body" style="height: 500px;">
						<canvas id="memeryChart" width="0" height="0"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

