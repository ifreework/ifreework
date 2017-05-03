<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<link href="${cssPath }/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<script src="${ jsPath }/bootstrap/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript">
var departmentTreeNode; //需要操作的deptTree全局节点属性
(function(){
	var treeId ;//选中的操作
	$().ready(function(){
		//创建右键menu对象
		var menu = new W.menu({
			id : "menu", //menu id
			items :[{
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
				if(id == "add"){
					openDialog("${ contextPath }/system/department/add?treeId=" + treeId);
				}else if(id == "update"){
					if(treeId == null || treeId == "0"){
						bootbox.alert("请选择您要修改的部门");
						return;
					}
					openDialog("${ contextPath }/system/department/update?treeId=" + treeId);
				}else if(id == "delete"){
					if(treeId == null || treeId == "0"){
						bootbox.alert("请选择您要删除的部门");
						return;
					}
					var message = departmentTreeNode.isParent ? "该部门删除后，其相关的子部门都会被删除，确定要删除该部门吗？" : "确定要删除该部门吗？"
					bootbox.confirm(message,"",function(e){
						if(e){
							deleteNode();
						}
					});
				}
			}
		});
		
		var setting = {
				view: {
					dblClickExpand: false
				},
				async: {
					enable: true,
					url:"${ contextPath }/system/department/query",
					autoParam:["id"],
					dataFilter: filter
				},
				edit:{
					enable: true,
					showRemoveBtn:false,
					showRenameBtn:false
				},
				callback: {
					onRightClick: function(event, id, treeNode) {
						departmentTreeNode = treeNode;
						treeId = treeNode == null ? "0" : treeNode.id;
						menu.show(event.clientX,event.clientY);
					},
					onDrop:function(event, treeId, treeNodes, targetNode, moveType){
						if(moveType == "inner"){//更换父节点
							updateNode(treeNodes[0].id,targetNode.id)
						}
					}
				}
			};
	
			function filter(treeId, parentNode, childNodes) {
				if (!childNodes) return null;
				for (var i=0, l=childNodes.length; i<l; i++) {
					childNodes[i].id = childNodes[i].departmentId;
					childNodes[i].name = childNodes[i].departmentName;
					childNodes[i].isParent = childNodes[i].isLeaf == 1 ? false : true;
				}
				return childNodes;
			}
			
			$(document).ready(function(){
				$.fn.zTree.init($("#departmentTree"), setting);
			});
	});
	
	
	function openDialog(url){
		var dialog = bootbox.dialog({
			id:"departmentDialog",
			title: "头像设置",
			width:700,
	        loadUrl: url
	    });
	}
	
	function deleteNode(){
		var opt = {
				url : "${ contextPath }/system/department/delete",
				data:{departmentId:treeId},
				success:function(param){
					if(param.result === SUCCESS){
						bootbox.alert("数据删除成功","",function(){
							var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
    						treeObj.removeNode(departmentTreeNode);
						});
					}else{
						bootbox.alert("数据异常，删除失败");
					}
				}
		};
		ajax(opt);
	}
	
	
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
<div class="container-content">
	<div class="container-body">
		<div class="row">
		    <div class="col-xs-12 col-md-12">
		        <div class="widget">
		            <div class="widget-body">
	                   	<ul id="departmentTree" class="ztree"></ul>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>
