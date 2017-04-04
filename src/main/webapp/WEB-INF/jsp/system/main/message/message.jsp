<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8" />
<!-- jsp文件头和头部 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>${pd.SYSNAME}</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<script src="<%=jsPath%>/main/websocket/sockjs.js"></script>
<script src="<%=jsPath%>/main/websocket/stomp.js"></script>

<script type="text/javascript">
	function connect() {
		var websocket;

		websocket = new SockJS("/ifreework/websocket");
		var w1 = new SockJS("/ifreework/websocket");
		var stompClient = Stomp.over(websocket);
		var st = Stomp.over(w1);
		stompClient.connect({}, function(frame) {
			stompClient.subscribe('/user/topic/greetings', function(data) { //订阅消息
				console.log("jieshouxiaoxi " + data);
				$("#response").html($("#response").html() + "<br>" + data);
			});
			
		});
		
		st.connect({}, function(frame) {
			st.subscribe('/topic/greetings1', function(data) { //订阅消息
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
</head>
<body>

	<button id="connect" onclick="connect()">connect</button>
	<button id="disconnect" onclick="disconnect()" />
	disconnect
	</button>
	<br>
	<input id="message" value="send message" />
	<button id="ws">发送消息</button>
	<div id="response"></div>
</body>
</html>
