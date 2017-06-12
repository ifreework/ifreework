<%@page import="com.ifreework.common.manager.UserManager"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link rel="stylesheet" href="${ cssPath }/dropzone/dropzone.css"></link>
<script src="${ jsPath }/bootstrap/dropzone/dropzone.js"></script>
<script type="text/javascript">
$.namespace("attendance.leaveBill.add");
attendance.leaveBill.edit = function(){
	var attendanceUserEdit ,
		bootstrapValidator,
		dropzone,
		startTime,
		endTime,
		DEFAULT_START_TIME = '8:30:00', //默认上午考勤时间
		DEFAULT_START_CUMPUTE = 8.5, //默认早上计算考勤时间
		DEFAULT_END_TIME = '17:30:00', //默认下午考勤时间
		DEFAULT_END_CUMPUTE = 17.5; //默认下午计算考勤时间
	function initBootstrapValidator(){
		bootstrapValidator = attendanceUserEdit.find("#saveForm").bootstrapValidator({
	     	fields: {
	            leaveBillname: {
	                validators: {
	                	leaveType: {
	                        message: '请选择你的请假原因'
	                    }
	                }
	            },
	            sDate: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假开始日期'
	                    }
	                }
	            },
	            sTime: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假开始时间'
	                    }
	                }
	            },
	            eDate: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入结束日期'
	                    },
	                    callback : {
	                    	message:"请假结束时间必须大于开始时间，且必须大于1个小时，请重新输入"
	                    }
	                }
	            },
	            eTime: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假结束时间'
	                    }
	                }
	            },
	            leaveCause: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入请假结束时间'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
	}
	
	function initSelect(){
		attendanceUserEdit.find("#leaveType").select2();
	}
	
	function initDate(){
		attendanceUserEdit.find('#sDate').datepicker({
			autoclose:true
		}).on("changeDate",function(e){
			validateTime();
		});
		
		attendanceUserEdit.find('#sTime').timepicker({
			defaultTime:DEFAULT_START_TIME,
			maxHours:18,
			minHours:8,
			showMeridian:false,
			showSeconds:true
		}).on("changeTime.timepicker",function(e){
			
			validateTime();
		});
		attendanceUserEdit.find('#eDate').datepicker({
			autoclose:true
		}).on("changeDate",function(e){
			validateTime();
		});
		
		attendanceUserEdit.find('#eTime').timepicker({
			defaultTime:DEFAULT_END_TIME,
			maxHours:18,
			minHours:8,
			showMeridian:false,
			showSeconds:true
		}).on("changeTime.timepicker",function(e){
			validateTime();
		});
	}
	
	function initAutosize(){
		attendanceUserEdit.find('#leaveCause').autosize({ append: "\n" });
	}
	
	//上传附件
	function initDropzone(){
		dropzone = new Dropzone("#system-attachment #dropzone", {
			url : "${ contextPath }/system/attachment/fileUpload",
			autoProcessQueue : true,
			addRemoveLinks:true,
			maxFilesize:100,
			success:function(file,data){
				console.log(file);
				console.log(data);
				file.attachmentId = data.attachmentId;
				
				$(file.previewElement).addClass("dz-success");
			},
			removedfile:function(file){
				console.log(file);
				var opt = {
					url:"${contextPath}//system/attachment/delete",
					data:{
						attachmentId:file.attachmentId
					},
					success:function(){
						$(file.previewElement).remove();
					}
				}
				W.ajax(opt);
			}
		});
	}
	
	function initSave(){
	    attendanceUserEdit.find("#btn-save").click(function(){
	    	bootstrapValidator.validate();
	    	if(bootstrapValidator.isValid()){
	    		var data = attendanceUserEdit.find("#saveForm").serializeJson();
	    		var opt = {
	    				url : "${ contextPath }/attendance/leaveBill/save",
	    				data:data,
	    				success:function(param){
	    					if(param.result === SUCCESS){
	    						bootbox.alert("数据保存成功","",function(){
	    							attendance.main.history();
	    						});
	    					}else{
	    						bootbox.alert("数据异常，保存失败");
	    					}
	    				}
	    		};
	    		W.ajax(opt);
	    	}
	    });
	}
	
	//验证结束时间是否大于开始时间
	function validateTime(){
		var sDate = $("#sDate").val(),
			sTime =  $("#sTime").val(),
			eDate = $("#eDate").val(),
			eTime =  $("#eTime").val(),
			minuteTime; 
		
		if(!W.isNull(sDate) && !W.isNull(sTime) && !W.isNull(eDate) && !W.isNull(eTime)){
			startTime = new Date(sDate + " " + sTime);
			endTime = new Date(eDate + " " + eTime);
			
			minuteTime = endTime.getTime() - startTime.getTime();
			
			if(minuteTime <= 1000*60*60){  //验证结束时间是否大于开始时间一个小时
				bootstrapValidator.updateStatus("eTime", "INVALID", "callback");
				return;
			}
			bootstrapValidator.updateStatus("eTime", "VALID");
			computeLeaveDay(sDate,sTime,eDate,eTime);
		}
	}
	
	//计算请假时间
	function computeLeaveDay(sDate,sTime,eDate,eTime){
		var minuteTime,  
		tempTime,
		nextStartDay, //开始时间的下一天
		dateNum;
		
		nextStartDay = new Date(sDate + " " + sTime);
		nextStartDay.setDate(nextStartDay.getDate() + 1);
		nextStartDay.setHours(0,0,0,0);
		
		minuteTime = (endTime.getTime() - nextStartDay.getTime()) / (1000 * 60 * 60 * 24); 
		console.log(minuteTime);
		
		if( minuteTime < 0 ){  //如果小于0，说明请假是在当天
			tempTime = (endTime.getHours() + endTime.getMinutes() / 60 ) - ( startTime.getHours() + startTime.getMinutes() / 60) ;
			dateNum = tempTime > 4 ? 1 : 0.5 ;
		}else if( minuteTime > 0 ){  //如果是隔天
			tempTime = DEFAULT_END_CUMPUTE - (startTime.getHours() + startTime.getMinutes() / 60); //获取开始日期当天的请假时间
			dateNum = tempTime > 4 ? 1 : 0.5 ;   
			
			tempTime = (endTime.getHours() + endTime.getMinutes() / 60) - DEFAULT_START_CUMPUTE; //获取结束当天的请假时间
			tempTime = tempTime > 4 ? 1 : 0.5 ;   
			dateNum += tempTime;
			if(minuteTime >= 1){
				tempTime = parseInt(minuteTime);
				dateNum += tempTime;
			}
		}
		
		$("#dateNum").val(dateNum + "天");
	}
	
	return {
		init:function(){
			attendanceUserEdit = $("#attendance-leaveBill-edit");
			initBootstrapValidator();
			initSelect();
			initDate();
			initAutosize();
			initDropzone();
			initSave();
			
		}
	}
	
}();

$().ready(function(){
	attendance.leaveBill.edit.init();
});
</script>
<div class="container-content" id="attendance-leaveBill-edit">
	<div class="container-body">
		<div id="registration-form">
			<form id="saveForm" method="post" class="form-horizontal">
		
				<div class="form-title">
						请假人员：<%=UserManager.getUser().getPersonName() %>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">请假类型</label>
					<div class="col-sm-9">
						<select  id="leaveType" name="leaveType" style="width: 100%;" >
							<option value="sj">事假</option>
							<option value="bj">病假</option>
						</select>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">开始时间</label>
					<div class="col-sm-5">
						<span class="input-icon icon-left">
							<i class="fa fa-calendar"></i>
							<input type="text" class="form-control" name="sDate" id="sDate" readonly="readonly">
						</span>
					</div>
					<div class="col-sm-4">
						<span class="input-icon icon-left">
							<i class="fa fa-clock-o"></i>
							<input type="text" class="form-control" name="sTime" id="sTime" readonly="readonly">
						</span>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">结束时间</label>
					<div class="col-sm-5">
						<span class="input-icon icon-left">
							<i class="fa fa-clock-o"></i>
							<input type="text" class="form-control" name="eDate" id="eDate" readonly="readonly">
						</span>
					</div>
					<div class="col-sm-4">
						<span class="input-icon icon-left">
							<i class="fa fa-clock-o"></i>
							<input type="text" class="form-control" name="eTime" id="eTime" readonly="readonly">
						</span>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">请假天数</label>
					<div class="col-sm-9">
						<input type="text" id="dateNum" class="form-control" value="0天" readonly="readonly" >
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label">请假原因</label>
					<div class="col-sm-9">
						   <textarea class="form-control" id="leaveCause" name="leaveCause">${leaveBill.leaveCause }</textarea>
					</div>
				</div>
				
				<div class="form-group has-feedback row">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-9">
						<div id="dropzone" class="dropzone ">
						</div>
					</div>
				</div>
				
				<input type="hidden" name="leaveBillId" value="${leaveBill.leaveBillId }">
			</form>
		</div>
		<div class="text-center container-footer">
			<a class="btn btn-sky" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
		</div>
	</div>
</div>