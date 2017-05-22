<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("system.requestLog");

system.requestLog = function(){
	var systemRequestLog,
		dataTable;
	
	function initTable(){
		dataTable = systemRequestLog.find('#requestLogTab').DataTable({
			searching:false,//
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			order:[[7,'desc']],
			ajax:{
				url:"${contextPath}/system/requestLog/query",
				data: function ( d ) {
		      		return $.extend( {}, d, systemRequestLog.find("#queryForm").serializeJson());
			    }
			},
			columns : [ {  
	            data : "resource.resourceName",  
	            title : "访问名称",  
	            defaultContent : "" ,
	            orderable:false
	        }, {  
	            data : "resource.resourceUrl",  
	            title : "访问地址",  
	            defaultContent : "" ,
	            orderable:false
	        }, {  
	            data : "username",  
	            name : "username",  
	            title : "用户名",  
	            defaultContent : "" 
	        }, {  
	            data : "ip",  
	            title : "IP",  
	            defaultContent : "" ,
	            orderable:false
	        },{  
	        	data : "browser",  
	            title : "浏览器",  
	            defaultContent : "",
	            orderable:false,
	            render:function( data, type, row, meta ){
	            	return data + " (" + row.browserVersion + ")"
	            }
	        },{  
	        	data : "deviceType",  
	            title : "设备类型",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "osInfo",  
	            title : "操作系统",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "requestTime",  
	        	name : "REQUEST_TIME",
	            title : "访问时间",  
	            defaultContent : ""
	        },{  
	        	data : "timeLength",  
	        	name : "TIME_LENGTH",
	            title : "访问持续时间", 
	            className : "text-right",
	            defaultContent : ""
	        }]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } );
	}
	
	function query(){
		dataTable.ajax.reload();
	}
	
	return {
		init : function(){
			systemRequestLog = $("#system-requestLog");
			initTable();
			systemRequestLog.find("#query").on("click",query);
		} 
	}
}();

$().ready(function(){
	system.requestLog.init();
});


</script>
<div class="container-content" id="system-requestLog">
	<div class="container-body">
		<div class="table-toolbar">
			<form class="form-horizontal" id="queryForm">
				 <div class="has-feedback row">
				<label class="col-sm-1 control-label">用户名</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="username" placeholder="用户名">
				</div>
				<div class="col-sm-1">
					<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
				</div>
			   </div>
			</form>
		 </div>
		<table class="table table-striped table-bordered table-hover" id="requestLogTab">
		</table>
	</div>
</div>