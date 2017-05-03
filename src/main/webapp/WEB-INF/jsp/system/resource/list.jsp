<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
(function(){
	var dataTable;
	$().ready(function(){
		dataTable = $('#resourceTab').DataTable({
			searching : false,//
			ordering : false,
			serverSide:true, //是否启用服务器模式
			paging : false,
			pageLength: 0 ,
			autoWidth: false,
			ajax:{
				url:"${contextPath}/system/resource/query",
				data: function ( d ) {
		      		return d;
			    }
			},
			columns : [
				{  
		            data : "resourceName",  
		            title : "资源名",  
		            defaultContent : "" ,
		            render:function( data, type, row, meta ){
		        		return ( '<i class="fa fa-plus-square-o row-details" data-id="' + row.resourceId  + '" data-depth="0" parent="' + row.parentId + '" style="' + (row.isLeaf == '0' ? '' : 'display:none' ) + '"></i>' ) + '<span class="margin-left-10">' + data +'</span>';
		        	}
		        },{  
		        	data : "resourceUrl",  
		            title : "路径",  
		            defaultContent : ""
		        }, {  
		        	data : "pk",  
		            title : "pk",  
		            defaultContent : ""
		        }, {  
		        	data : "resourceType",  
		        	title : "资源类型",  
		        	defaultContent : ""
		        }, {  
		        	data : "iconCls",  
		        	title : "图标",  
		        	defaultContent : ""
		        },  {  
		        	data : "remarks",  
		        	title : "说明",  
		        	defaultContent : ""
		        }, {  
		        	title : "",  
		        	defaultContent : "", 
		        	className : "text-center"  ,
		        	render:function( data, type, row, meta ){
		        		return '<a class="btn btn-add btn-sky btn-xs icon-only" title="新增" data-resourceid="' + row.resourceId + '" href="javascript:void(0);"><i class="fa fa-plus "></i></a>' +
		        			   '<a class="btn btn-edit btn-info btn-xs icon-only margin-left-10" title="修改" data-resourceid="' + row.resourceId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
		        			   '<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-resourceid="' + row.resourceId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>';
		        	}
		        }
		    ]
	    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
	        bootbox.load();
	    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
	    	 bootbox.unload();
	    } ).on( 'draw.dt', function () {
	    	 $("#resourceTab .row-details").click(function(){ //显示隐藏切换按钮
	    		 toggleTr(this);
	    	 });
	    	 
	 		 $(".btn-add").on("click",function(e){
	 			 gotoAdd(this);
			 });
	 		 $(".btn-edit").on("click",function(e){
	 			 gotoEdit(this);
			 });
	 		 $(".btn-delete").on("click",function(e){
	 			 gotoDelete(this);
			 });
	    } );
		
		$("#addRoot").on("click",function(){
			W.openPage("${contextPath}/system/resource/add");
		})

	});

	//展开与隐藏子节点
	function toggleTr(e){
		var id = $(e).data("id"),//操作的ID
			depth = $(e).data("depth") + 1, //节点的深度
			tr = $(e).closest("tr"),
			children = $("#resourceTab .row-details[parent='" + id + "']").closest("tr");
		
		console.log(children);
		
		if($(e).hasClass("fa-plus-square-o")){  //如果节点为隐藏状态，则展开节点
			$(e).removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
			
			if(children.length == 0){//如果没有子节点，则加载子节点，否则直接显示子节点
				loadChildren(id,tr,depth);
			}else{
				children.show();
			}
			
		}else{
			$(e).removeClass("fa-minus-square-o").addClass("fa-plus-square-o");
			hideChildren(children);
		}
	}
	
	
	function loadChildren(id,tr,depth){
		var opt = {
			url : "${contextPath}/system/resource/query",
			data:{id:id},
			success:function(param){
				var html = "";
				for(var i = 0; i < param.data.length;i++){
					var row = param.data[i];
					html = html +  "<tr>" +
						"<td style='padding-left:" + ( depth * 20 + 10) + "px'>" + ( '<i class="fa fa-plus-square-o row-details" data-id="' + row.resourceId  + '" data-depth="'+  depth +'" parent="' + row.parentId + '" style="' + (row.isLeaf == '0' ? '' : 'display:none' ) + '"></i>' ) + '<span class="margin-left-' + (row.isLeaf == '0' ? '10' : '20' ) + '">' + row.resourceName + "</span></td>" + 
						"<td>" + row.resourceUrl + "</td>" + 
						"<td>" + row.pk + "</td>" + 
						"<td>" + row.resourceType + "</td>" + 
						"<td>" + row.iconCls + "</td>" + 
						"<td>" + row.remarks + "</td>" + 
						"<td class='text-center'>" + '<a class="btn btn-add btn-sky btn-xs icon-only" title="新增" data-resourceid="' + row.resourceId + '" href="javascript:void(0);"><i class="fa fa-plus "></i></a>' +
	        			   		 '<a class="btn btn-edit btn-info btn-xs icon-only margin-left-10" title="修改" data-resourceid="' + row.resourceId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
	        			   		 '<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-resourceid="' + row.resourceId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>' + 
	        			   		 "</td>" + 
						"</tr>";
				}
				tr.after(html);
				
				$("#resourceTab .row-details").unbind().on("click",function(){
		    		toggleTr(this);
		    	});
		 		 $(".btn-add").unbind().on("click",function(e){
		 			 gotoAdd(this);
				 });
		 		 $(".btn-edit").unbind().on("click",function(e){
		 			 gotoEdit(this);
				 });
		 		 $(".btn-delete").unbind().on("click",function(e){
		 			 gotoDelete(this);
				 });
			}
		};
		ajax(opt);
	}
	
	
	//点击隐藏的时候，除了隐藏子元素之外，同步递归隐藏孙子，穷孙子元素
	function hideChildren(e){
		e.each(function(index,event){
			$(event).hide(0,function(){
				$(event).find(".row-details").removeClass("fa-minus-square-o").addClass("fa-plus-square-o");
				var id = $(event).find(".row-details").data("id");
				id = id== null ? "0" : id;
				var children = $("#resourceTab .row-details[parent='" + id + "']").closest("tr");
				hideChildren(children);
			});
		});
	}
	
	
	function gotoAdd(e){
		var resourceId = $(e).data("resourceid");
		W.openPage("${contextPath}/system/resource/add",{resourceId:resourceId});
	}
	
	function gotoEdit(e){
		var resourceId = $(e).data("resourceid");
		console.log(resourceId);
		W.openPage("${contextPath}/system/resource/update",{resourceId:resourceId});
	}
	
	function gotoDelete(e){
		var resourceId = $(e).data("resourceid");
		W.openPage("${contextPath}/system/resource/delete",{resourceId:resourceId});
	}
}());
</script>
<div class="container-content">
	<div class="container-body">
		<div class="table-toolbar">
	    	<form class="form-horizontal" id="queryForm">
				<div class="has-feedback row">
					<div class="col-sm-12">
						<a id="addRoot" class="btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加根节点</a>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-bordered table-hover" id="resourceTab">
		</table>
	</div>
</div>
