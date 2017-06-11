<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("attendance.leaveBill");

attendance.leaveBill = function(){
	var attendanceLeaveBill,
		dataTable;
	
	function initTable(){
		dataTable = attendanceLeaveBill.find('#leaveBillTab').DataTable({
			searching:false,//
			lengthChange: false,
			serverSide:true, //是否启用服务器模式
			pageLength: 100 ,
			autoWidth: false,
			order:[[5,'desc']],
			ajax:{
				url:"${contextPath}/attendance/leaveBill/query",
				data: function ( d ) {
		      		return $.extend( {}, d, attendanceLeaveBill.find("#queryForm").serializeJson());
			    }
			},
			columns : [ {  
	            data : "leaveType",  
	            name : "leave_type",  
	            title : "请假类型",  
	            defaultContent : "" 
	        }, {  
	            data : "startTime", 
	            name : "start_time",  
	            title : "开始时间",  
	            defaultContent : "" 
	        }, {  
	            data : "endTime", 
	            name : "end_time",  
	            title : "结束时间",  
	            defaultContent : "" 
	        },{  
	        	data : "browser",  
	            title : "请假天数",  
	            defaultContent : "",
	            orderable:false,
	            render:function( data, type, row, meta ){
	            	return data + " (" + row.browserVersion + ")"
	            }
	        },{  
	        	data : "leaveCause",  
	            title : "请假原因",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "status",  
	            title : "审批状态",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "attachmentId",  
	        	name : "attachment_Id",
	            title : "附件",  
	            defaultContent : "",
	            orderable:false
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
			attendanceLeaveBill = $("#attendance-leaveBill");
			initTable();
			attendanceLeaveBill.find("#query").on("click",query);
		} 
	}
}();

$().ready(function(){
	attendance.leaveBill.init();
});


</script>
<div class="container-content" id="attendance-leaveBill">
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
		<table class="table table-striped table-bordered table-hover" id="leaveBillTab">
		</table>
	</div>
</div>