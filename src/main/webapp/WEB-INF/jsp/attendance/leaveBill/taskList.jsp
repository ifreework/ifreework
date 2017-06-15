<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("attendance.leaveBill.taskList");

attendance.leaveBill.taskList = function(){
	var attendanceLeaveBill,
		dataTable;
	
	function initTable(){
		dataTable = attendanceLeaveBill.find('#leaveBillTaskTab').DataTable({
			searching : false,//
			lengthChange: false,
			info:false,
			ordering : false,
			serverSide:true, //是否启用服务器模式
			paging : false,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/attendance/leaveBill/queyTaskListByName"
			},
			columns : [ {  
	            data : "leaveBill.leaveType",  
	            name : "leave_type",  
	            title : "请假类型",  
	            defaultContent : "" 
	        }, {  
	            data : "leaveBill.startTime", 
	            name : "start_time",  
	            title : "开始时间",  
	            defaultContent : "" 
	        }, {  
	            data : "leaveBill.endTime", 
	            name : "end_time",  
	            title : "结束时间",  
	            defaultContent : "" 
	        }, {  
	        	data : "leaveBill.leaveDays",  
	            title : "请假天数",  
	            defaultContent : "",
	            orderable:false
	        }, {  
	        	data : "leaveBill.leaveCause",  
	            title : "请假原因",  
	            defaultContent : "",
	            orderable:false
	        }, {  
	        	data : "leaveBill.status",  
	            title : "审批状态",  
	            defaultContent : "",
	            orderable:false
	        },{  
	        	data : "leaveBill.attachments",  
	            title : "附件",  
	            defaultContent : "",
	            orderable:false,
	            render:function( data, type, row, meta ){
	            	console.log(data);
	            	var attStr = "";
	            	for(var i = 0;i < data.length;i++){
	            		var html = '<a href="javascript:void(0)" class="tab-attachment" data-attachmentid="' + data[i].attachmentId + '">' + data[i].attachmentName + '</a>';
	            		attStr += html;
	            		if(i < data.length - 1 ){
	            			attStr += "<br />";
	            		}
	            	}
	            	return attStr;
	            }
	        },{  
	        	title : "操作",  
	        	data : "status",  
	        	defaultContent : "", 
	        	searchable:false,
	        	orderable:false,
	        	className : "text-center"  ,
	        	render:function( data, type, row, meta ){
	        		var html = '<a class="btn btn-view btn-sky btn-xs icon-only" title="查看详情" data-leavebillid="' + row.leaveBillId + '" href="javascript:void(0);"><i class="fa fa-info "></i></a>' +
	        				   '<a class="btn btn-agree btn-palegreen btn-xs icon-only margin-left-10" title="提报" data-taskid="' + row.taskId + '" href="javascript:void(0);"><i class="fa  fa-check-square-o "></i></a>' +
	        				   '<a class="btn btn-reject btn-danger btn-xs icon-only margin-left-10" title="提报" data-taskid="' + row.taskId + '" href="javascript:void(0);"><i class="fa  fa-check-square-o "></i></a>' ;
	        		return html;
	        	}
	        }]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } ).on( 'draw.dt', function () {
	    	attendanceLeaveBill.find('.btn-agree').on("click",function(){
	    		var id = $(this).data("taskid");
	    		complete(id);
	    	});
	    });
	}
	
	//提报
	function complete(id){
		bootbox.confirm("确定通过该请假申请吗？","",function(e){
			if(e){
				W.ajax({
					url : "${ contextPath }/attendance/leaveBill/complete",
					data:{ taskId : id ,status:"1"},
					success:function(param){
						if(param.result === SUCCESS){
							bootbox.alert("数据提报成功","",function(){
								dataTable.ajax.reload();
							});
						}else{
							bootbox.alert("数据异常，提报失败");
						}
					}
				});
			}
		});
	}
	
	return {
		init : function(){
			attendanceLeaveBill = $("#attendance-leaveBill-task");
			initTable();
		} 
	}
}();

$().ready(function(){
	attendance.leaveBill.taskList.init();
});


</script>
<div class="container-content" id="attendance-leaveBill-task">
	<div class="container-body">
		<table class="table table-striped table-bordered table-hover" id="leaveBillTaskTab">
		</table>
	</div>
</div>