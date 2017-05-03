<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link href="${cssPath }/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ jsPath }/bootstrap/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
(function(){
	var $role = $("#role");
	$().ready(function(){
		//创建右键menu对象
		var menu = new W.menu({
			id : "menu", //menu id
			items :[{
				id:"authorization",
				icon:"fa fa-pagelines sky",
				text:"角色授权"
			},{
				id:"add",
				icon:"fa fa-plus green",
				text:"新增节点"
			},{
				id:"update",
				icon:"fa fa-edit blue",
				text:"修改节点"
			},{
				id:"delete",
				icon:"fa fa-trash-o red",
				text:"删除节点"
			}], 
			onclick:function(id){
				if(id == "authorization"){
					W.openPage("${ contextPath }/system/role/authorization?treeId=" + $role.data("treeId"));
				}else if(id == "add"){
					openDialog("${ contextPath }/system/role/add?treeId=" + $role.data("treeId"));
				}else if(id == "update"){
					var treeId = $role.data("treeId");
					if(treeId == null || treeId == "0"){
						bootbox.alert("请选择您要修改的角色");
						return;
					}
					openDialog("${ contextPath }/system/role/update?treeId=" + treeId);
				}else if(id == "delete"){
					var treeId = $role.data("treeId");
					if(treeId == null || treeId == "0"){
						bootbox.alert("请选择您要删除的角色");
						return;
					}
					var message = $role.data("treeNode").isParent ? "该角色删除后，其相关的子角色都会被删除，确定要删除该角色吗？" : "确定要删除该角色吗？"
					bootbox.confirm(message,"",function(e){
						if(e){
							deleteNode(treeId);
						}
					});
				}
			}
		});
		
		var setting = {
			async: {
				enable: true,
				url:"${ contextPath }/system/role/query",
				autoParam:["id"],
				dataFilter: filter
			},
			callback: {
				onRightClick: function(event, id, treeNode) {
					$role.data("treeNode",treeNode);
					$role.data("treeId",treeNode == null ? "0" : treeNode.id);
					menu.show(event.clientX,event.clientY);
				}
			},
			onDrop:function(event, treeId, treeNodes, targetNode, moveType){
					if(moveType == "inner"){//更换父节点
						updateNode(treeNodes[0].id,targetNode.id)
					}
				}
			};
			
			function filter(treeId, parentNode, childNodes) {
				if (!childNodes) return null;
				for (var i=0, l=childNodes.length; i<l; i++) {
					childNodes[i].id = childNodes[i].roleId;
					childNodes[i].name = childNodes[i].roleName;
					childNodes[i].isParent = childNodes[i].isLeaf == 1 ? false : true;
				}
				return childNodes;
			}
			
			$.fn.zTree.init($role.find("#roleTree"), setting);
		});
	
	
	function openDialog(url){
		var dialog = bootbox.dialog({
			id:"roleDialog",
			title: "头像设置",
			width:700,
			loadUrl: url
		});
	}
	
	//删除节点
	function deleteNode(treeId){
		var opt = {
			url : "${ contextPath }/system/role/delete",
			data:{roleId:treeId},
			success:function(param){
				if(param.result === SUCCESS){
					bootbox.alert("数据删除成功","",function(){
						var treeObj = $.fn.zTree.getZTreeObj("roleTree");
						treeObj.removeNode($role.data("treeNode"));
					});
				}else{
					bootbox.alert("数据异常，删除失败");
				}
			}
		};
		ajax(opt);
	}
	
	
	//节点拖拽后，修改父节点
	function updateNode(departmentId,parentId){
		var opt = {
			url : "${ contextPath }/system/department/save",
			data:{departmentId:departmentId,parentId:parentId},
			success:function(param){
				if(param.result === SUCCESS){
					var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
					treeObj.removeNode(departmentTreeNode);
				}else{
					bootbox.alert("数据异常，删除失败");
				}
			}
		};
		ajax(opt);
	}
}());
</script>
<div class="container-content" id="role">
	<div class="container-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="widget">
					<div class="widget-body">
						<ul id="roleTree" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
