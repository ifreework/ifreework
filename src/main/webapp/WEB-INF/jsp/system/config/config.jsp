<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ page import="com.ifreework.entity.system.Config" %>
<html lang="en">
<head>
<meta charset="UTF-8" />
<!-- jsp文件头和头部 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>部门管理</title>
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<script type="text/javascript">
$().ready(function(){
	 var bootstrapValidator = $("#saveForm").bootstrapValidator({
     	message: '数值未通过验证',
     	fields: {
     		system_name: {
                validators: {
                    notEmpty: {
                        message: '请填写您的系统名称'
                    }
                }
            },
            file_path: {
                validators: {
                    notEmpty: {
                        message: '请填写文件上传之后的默认保存地址'
                    }
                }
            }
     	}
	}).data('bootstrapValidator');
	 
	//是否启用ftp,如果启用ftp，则需要填写ftp相关配置 
	$("#ftp_enable_checkbox").change(function(){
		var checked = this.checked;
		if(checked){
			$("#ftp_enable").val(1);
			$(".ftp").show();
		}else{
			$("#ftp_enable").val(0);
			$(".ftp").hide();
		}
	});
	$("#button_auth_enable_checkbox").change(function(){
		var checked = this.checked;
		if(checked){
			$("#button_auth_enable").val(1);
		}else{
			$("#button_auth_enable").val(0);
		}
	});
    
    $("#btn-save").click(function(){
    	bootstrapValidator.validate();
    	if(bootstrapValidator.isValid()){
    		var data = $("#saveForm").serializeJson();
    		var opt = {
    				url : "<%=contextPath%>/system/config/save",
    				data:data,
    				success:function(param){
    					if(param.result === SUCCESS){
    						bootbox.alert("数据保存成功");
    					}else{
    						bootbox.alert("数据异常，保存失败");
    					}
    				}
    		};
    		ajax(opt);
    	}
    });
});
</script>
</head>
<body>
	<div class="main-container container-fluid">
    	<div class="row">
            <div class="col-lg-12 col-sm-12 col-xs-12">
	            <div class="widget flat radius-bordered">
	                <div class="widget-body">
	                    <div id="registration-form">
	                         <form id="saveForm" method="post" class="form-horizontal">

	                            <div class="form-title icon-left">
	                                <i class="fa fa-cog "></i>
	                                <span>系统设置</span>
	                            </div>
	                            <div class="form-group has-feedback row">
	                            	<label class="col-sm-2 control-label">系统名称</label>
	                            	<div class="col-sm-9">
	                            		<span class="input-icon icon-left">
		                            		<i class="fa fa-user circular"></i>
		                                    <input type="text" class="form-control" name="system_name" placeholder="系统名称" value="<%=Config.init().get(Config.SYSTEM_NAME)%>">
                                    	</span>
	                            	</div>
	                               
	                            </div>
	                            <div class="form-group has-feedback row">
	                            	<label class="col-sm-2 control-label">是否启用FTP服务</label>
	                            	<div class="col-sm-9">
					                	    <input class="checkbox-slider toggle colored-darkorange" id="ftp_enable_checkbox"  type="checkbox"  <%="1".equals(Config.init().get(Config.FTP_ENABLE)) ? "checked='checked'" : ""  %>>
					                	    <span class="text no-margin-left" style="margin-top:7px; "></span>
					                	    <input type="hidden" name="ftp_enable" id="ftp_enable" value="<%=Config.init().get(Config.FTP_ENABLE) %>">
	                            	</div>
	                            </div>
	                            
	                            <div class="form-group has-feedback row">
	                            	<label class="col-sm-2 control-label">文件保存地址</label>
	                            	<div class="col-sm-9">
	                            		<span class="input-icon icon-left">
		                            		<i class="fa fa-phone circular"></i>
		                                    <input type="text" class="form-control" name="file_path" placeholder="文件保存地址" value="<%=Config.init().get(Config.FILE_PATH)%>" >
                                    	</span>
	                            	</div>
	                            </div>
	                            
	                            <div class="form-group has-feedback row ftp" style="<%="1".equals(Config.init().get(Config.FTP_ENABLE)) ? "block" : "none"  %>">
	                            	<label class="col-sm-2 control-label">FTP端口号</label>
	                            	<div class="col-sm-9">
	                            		<span class="input-icon icon-left">
		                            		<i class="fa fa-phone circular"></i>
		                                    <input type="text" class="form-control" name="ftp_port" placeholder="FTP端口号" value="<%=Config.init().get(Config.FTP_PORT)%>" >
                                    	</span>
	                            	</div>
	                            </div>
	                            
	                            <div class="form-group has-feedback row ftp" style="<%="1".equals(Config.init().get(Config.FTP_ENABLE)) ? "block" : "none"  %>">
	                            	<label class="col-sm-2 control-label">FTP用户名</label>
	                            	<div class="col-sm-9">
	                            		<span class="input-icon icon-left">
		                            		<i class="fa fa-phone circular"></i>
		                                    <input type="text" class="form-control" name="ftp_username" placeholder="FTP用户名" value="<%=Config.init().get(Config.FTP_USERNAME)%>" >
                                    	</span>
	                            	</div>
	                            </div>
	                            
	                            <div class="form-group has-feedback row ftp" style="<%="1".equals(Config.init().get(Config.FTP_ENABLE)) ? "block" : "none"  %>">
	                            	<label class="col-sm-2 control-label">FTP密码</label>
	                            	<div class="col-sm-9">
	                            		<span class="input-icon icon-left">
		                            		<i class="fa fa-phone circular"></i>
		                                    <input type="text" class="form-control" name="ftp_password" placeholder="FTP密码" value="<%=Config.init().get(Config.FTP_PASSWORD)%>" >
                                    	</span>
	                            	</div>
	                            </div>
	                            
	                            <div class="form-group has-feedback row">
	                            	<label class="col-sm-2 control-label">是否启用按钮权限</label>
	                            	<div class="col-sm-9">
					                	    <input class="checkbox-slider toggle colored-darkorange" id="button_auth_enable_checkbox" type="checkbox" <%="1".equals(Config.init().get(Config.BUTTON_AUTH_ENABLE)) ? "checked='checked'" : ""  %>>
					                	    <span class="text no-margin-left" style="margin-top:7px; "></span>
					                	    <input type="hidden" name="button_auth_enable" id="button_auth_enable" value="<%=Config.init().get(Config.FTP_ENABLE) %>">
	                            	</div>
	                            </div>
	                            
	                            <div class="row text-center">
	                            	<a class="btn btn-default" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	                            </div>
	                            
	                        </form>
	                    </div>
	                </div>
	            </div>
            </div>
    	</div>
    </div>
</body>
</html>