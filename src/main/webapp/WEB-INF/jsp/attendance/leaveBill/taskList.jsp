<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">

$.namespace("system.workFlow");

system.workFlow = function(){
	var systemWorkFlow,
		dataTable;
	
	function initTable(){
		dataTable = systemWorkFlow.find('#workFlowTab').DataTable({
			searching:false,//
			lengthChange: false,
			autoWidth: false,
	    });
	}
	
	
	return {
		init : function(){
			systemWorkFlow = $("#system-workFlow");
			initTable();
			systemWorkFlow.find("#add").on("click",add);
		} 
	}
}();

$().ready(function(){
	system.workFlow.init();
});


</script>
<div class="container-content" id="system-workFlow">
	<div class="container-body">
		<table class="table table-striped table-bordered table-hover" id="workFlowTab">
			<thead>
				<tr>
					<th>任务ID</th>
					<th>任务名称</th>
					<th>创建时间</th>
					<th>创建人</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ tasks }" var="task">
				<tr>
					<td>${task.id }</td>
					<td>${task.name }</td>
					<td><fmt:formatDate value="${task.createTime }" pattern="yyyy/MM/dd HH:mm:ss"/></td>
					<td>${task.assignee }</td>
					<td></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>