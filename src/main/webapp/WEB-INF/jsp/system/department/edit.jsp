<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
(function(){
	var bootstrapValidator;
	$().ready(function(){
		 bootstrapValidator = $("#saveForm").bootstrapValidator({
	     	message: '数值未通过验证',
	     	fields: {
	     		departmentNo: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写部门编号'
	                    }
	                }
	            },
	            departmentName: {
	                validators: {
	                    notEmpty: {
	                        message: '请填写部门名称'
	                    }
	                }
	            },
	            tel: {
	                validators: {
	                    phone:{
	                    	message: '电话号码格式不正确，请重新输入'
	                    }
	                }
	            }
	     	}
		}).data('bootstrapValidator');
	    $('#remarks').autosize({ append: "\n" });
	    
	    $("#btn-save").click(function(){
	    	bootstrapValidator.validate();
	    	if(bootstrapValidator.isValid()){
	    		var data = $("#saveForm").serializeJson();
	    		var opt = {
	    				url : "${ contextPath }/system/department/save",
	    				data:data,
	    				success:function(param){
	    					if(param.result === SUCCESS){
    							$("#departmentDialog").modal("hide");
    							console.log(departmentTreeNode);
    							
    							var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
    							if($("#departmentId").val()){
    								departmentTreeNode.name = $("#departmentName").val();
    								treeObj.updateNode(departmentTreeNode);
    							}else{
	    							if(departmentTreeNode != null){
	    								departmentTreeNode.isParent = true ;
	    							}
		    						treeObj.reAsyncChildNodes(departmentTreeNode, "refresh");
    							}
	    					}else{
	    						bootbox.alert("数据异常，保存失败");
	    					}
	    				}
	    		};
	    		W.ajax(opt);
	    	}
	    });
	});
}());
</script>
<div class="container-content">
	<div class="container-body">
   	<div class="row">
           <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="widget flat radius-bordered">
                         <form id="saveForm" method="post" class="form-horizontal">
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">部门编号</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-user-md circular"></i>
	                                    <input type="text" class="form-control" name="departmentNo" placeholder="部门编号" value="${department.departmentNo }">
                                   	</span>
                            	</div>
                               
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">部门名称</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-group circular"></i>
	                                    <input type="text" class="form-control" id="departmentName" name="departmentName" placeholder="部门名称" value="${department.departmentName }" >
                                   	</span>
                            	</div>
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">联系电话</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-phone circular"></i>
	                                    <input type="text" class="form-control" name="tel" placeholder="联系电话" value="${department.tel }" >
                                   	</span>
                            	</div>
                            </div>
                            <div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">地址</label>
                            	<div class="col-xs-10">
                            		<span class="input-icon icon-left">
	                            		<i class="fa fa-building-o circular"></i>
	                                   <input type="text" class="form-control" name="address" placeholder="地址" value="${department.address }" >
                                   	</span>
                            	</div>
                            </div>
                            
                       		<div class="form-group has-feedback row">
                            	<label class="col-xs-2 control-label">其他</label>
                            	<div class="col-xs-10">
                                       <textarea class="form-control" id="remarks" name="remarks">${department.remarks }</textarea>
                            	</div>
                            </div>
                            
                            <input type="hidden" id="departmentId" name="departmentId" value="${department.departmentId }">
                            <input type="hidden" name="parentId" value="${department.parentId }">
                        </form>
                    </div>
                </div>
            </div>
   	</div>
	<div class="text-center container-footer">
		<a class="btn btn-primary" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
	</div>