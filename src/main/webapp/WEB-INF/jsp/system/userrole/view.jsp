<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link href="${cssPath }/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ jsPath }/bootstrap/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
	(function(){
		var $userRole;
		$().ready(function(){
			$userRole = $("#userRole");
			
			var setting = {
				check: {
					enable: true,
					chkboxType: { "Y": "p", "N": "p" },
					autoCheckTrigger: true
				},
				async: {
					enable: true,
					url:"${ contextPath }/system/userrole/queryRoleList",
					autoParam:["id"],
					otherParam:{userId:"${userId}"},
					dataFilter: filter
				}
			};
			
			function filter(treeId, parentNode, childNodes) {
				if (!childNodes) return null;
				for (var i=0, l=childNodes.length; i<l; i++) {
					childNodes[i].id = childNodes[i].roleId;
					childNodes[i].name = childNodes[i].role.roleName;
					childNodes[i].isParent = childNodes[i].role.isLeaf == 1 ? false : true;
					childNodes[i].checked = childNodes[i].userRoleId == null || childNodes[i].userRoleId == "" ? false : true;
				}
				return childNodes;
			}
			
			
			$.fn.zTree.init($userRole.find("#userRoleTree"), setting);
			
			$userRole.find("#btn-save").on("click",function(){
				editUserRole();
			})
			
			//保存修改后的权限树
			function editUserRole(){
				var treeObj = $.fn.zTree.getZTreeObj("userRoleTree");
				var nodes = treeObj.getChangeCheckedNodes(),
					addNodes = [],
					deleteNodes = [],
					addStr,
					deleteStr;
				for(var i = 0 ; i < nodes.length;i++){
					var obj = {
						userId : "${userId}",
						roleId: nodes[i].roleId
					};
					if(nodes[i].checked){
						addNodes.push(obj);
					}else{
						deleteNodes.push(obj);
					}
				}
				addStr = W.arrayToString(addNodes);
				deleteStr =  W.arrayToString(deleteNodes);
				var opt = {
					url : "${ contextPath }/system/userrole/save",
					data : {addStr:addStr,deleteStr:deleteStr},
					success:function(data){
						if(data.result == SUCCESS){
							bootbox.alert("数据保存成功","",function(){
								var treeObj = $.fn.zTree.getZTreeObj("userRoleTree");
								treeObj.reAsyncChildNodes(null, "refresh");
							});
						}else{
							bootbox.alert("系统异常，保存失败");
						}
					}
					
				}
				ajax(opt);
			}
		});
		
		
	}());
</script>
<div class="container-content" id="userRole">
	<div class="container-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="databox databox-shadowed padding-10 no-margin-bottom">
					<ul id="userRoleTree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<div class="text-center container-footer">
		<a class="btn btn-info" href="javascript:void(0);" id="btn-save"><i class="fa fa-save"></i>保存</a>
	</div>
</div>
