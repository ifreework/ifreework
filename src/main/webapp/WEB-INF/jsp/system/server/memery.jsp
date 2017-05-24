<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script src="${jsPath }/websocket/sockjs.js"></script>
<script src="${jsPath }/websocket/stomp.js"></script>

<script type="text/javascript">
	function connect() {
		var websocket;

		websocket = new SockJS("/ifreework/websocket");
		var stompClient = Stomp.over(websocket);

		stompClient.connect({}, function(frame) {
			stompClient.subscribe('/topic/memeryScale', function(data) { //订阅消息
				console.log(data);
				$("#response").html($("#response").html() + "<br>" + data);
			});

		});

		document.getElementById("ws").onclick = function() {
			stompClient.send("/app/httpSendMsg", {}, JSON.stringify({
				name : "nane",
				taskName : "taskName",
				taskDetail : "taskDetail"
			}));
		}
	}

	function sendName() {
		websocket.send(JSON.stringify({
			name : "nane",
			taskName : "taskName",
			taskDetail : "taskDetail"
		}));
	}

	/*   
	 1. new WebSocket('ws://localhost:8080//websocket')尝试与服务器建立连接;  
	 2. 握手成功并建立连接后，socket.onopen被调用  
	 3. 当接收来自服务器的消息，socket.onmessage被调用  
	 4. socket.send()用来发送消息至服务端  
	 */
</script>


