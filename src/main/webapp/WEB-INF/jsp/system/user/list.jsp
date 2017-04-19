<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
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
	    <!--Page Related styles-->
<link href="<%=assetsPath %>/css/dataTables.bootstrap.css" rel="stylesheet" />
<script src="<%=assetsPath %>/js/datatable/jquery.dataTables.js"></script>
<script src="<%=assetsPath %>/js/datatable/dataTables.bootstrap.js"></script>
<script type="text/javascript">
$().ready(function(){
	var dataTable = $('#userTab').DataTable({
		searching:false,//
		serverSide:true, //是否启用服务器模式
		order:[[1,'asc']],
		pageLength: 100 ,
		autoWidth: false,
		ajax:{
			url:"<%=contextPath%>/system/user/query",
			data: function ( d ) {
	      		return $.extend( {}, d, $("#queryForm").serializeJson());
		    }
		},
		columns : [{  
            data : "userId",  
            defaultContent : "", //此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错  
            visible : false, //此列不显示
            orderable:false  //是否进行排序
        }, {  
            data : "userNo",  
            name : "user_no", //列别名，对应数据库中字段名，如果该列不进行排序，可不进行设置 
            title : "编号",  
            defaultContent : "" 
        }, {  
            data : "username",  
            name : "username", //列别名，对应数据库中字段名，如果该列不进行排序，可不进行设置 
            title : "用户名",  
            defaultContent : "" 
        },{  
        	data : "personName",  
        	name : "person_name",
            title : "姓名",  
            defaultContent : ""
        }, {  
        	data : "email",  
            title : "电子邮箱",  
            defaultContent : "", 
            searchable:false,
            orderable:false
        }, {  
        	data : "phone",  
        	title : "联系电话",  
        	defaultContent : "", 
        	searchable:false,
            orderable:false
        }, {  
        	data : "deailAddress",  
        	title : "家庭住址",  
        	defaultContent : "", 
        	searchable:false,
        	orderable:false,
        	render:function( data, type, row, meta ){
        		var provinceName = row.province.provinceName == null ? "" : row.province.provinceName,
        			municipalityName = row.municipality.municipalityName == null ? "" : row.municipality.municipalityName,
        			countyName = row.county.countyName == null ? "" : row.county.countyName;	
        		return provinceName + " " + municipalityName + " " +  countyName + " " + data;
        	}
        },{  
        	data : "status",  
        	title : "是否启用",  
        	defaultContent : "", 
        	searchable:false,
        	orderable:false,
        	className : "text-center",
        	render:function( data, type, row, meta ){
        		return ' <label class="no-margin-bottom "> ' +
	                	   '<input class="checkbox-slider toggle colored-blue" type="checkbox" data-userid="' + row.userId + '" onchange="changeStatus(this)" ' + (data == 1 ? 'checked="checked"' : '') + '>' +
	                	   '<span class="text"></span>' + 
            		   '</label>';
        	}
        },{  
        	title : "",  
        	defaultContent : "", 
        	searchable:false,
        	orderable:false,
        	className : "text-center"  ,
        	render:function( data, type, row, meta ){
        		return '<a class="btn btn-edit btn-info btn-xs icon-only" title="修改" data-userid="' + row.userId + '" href="javascript:void(0);"><i class="fa fa-edit "></i></a>' +
        			   '<a class="btn btn-delete btn-danger btn-xs icon-only margin-left-10" title="删除" data-userid="' + row.userId + '" href="javascript:void(0);"><i class="fa fa-trash-o "></i></a>' + 
        			   '<a class="btn btn-reset btn-warning btn-xs icon-only margin-left-10" title="重置密码" data-userid="' + row.userId + '" href="javascript:void(0);" onclick="resetPwd(this)"><i class="fa fa-eraser "></i></a>';
        	}
        }]
    }).on('preXhr.dt', function ( e, settings, data ) {//页面发送请求前，显示加载框
        bootbox.load();
    }).on('xhr.dt', function ( e, settings, json, xhr ) {//页面发送请求后，关闭加载遮罩
    	 bootbox.unload();
    } );
	
	$("#query").click(function(){
		dataTable.ajax.reload();
	});
});

//用户启用或者停用
function changeStatus(e){
	var userId = $(e).data("userid"),
		data = {userId:userId,status:e.checked ? 1 : 0},
	    opt = {
			url : "<%=contextPath%>/system/user/save",
			data:data,
			success:function(param){
				if(param.result === SUCCESS){
				}else{
					bootbox.alert("数据异常，设置失败");
				}
			}
	};
	ajax(opt);
}


//用户启用或者停用
function resetPwd(e){
	var userId = $(e).data("userid"),
		data = {userId:userId},
	    opt = {
			url : "<%=contextPath%>/system/user/resetPwd",
			data:data,
			success:function(param){
				if(param.result === SUCCESS){
					bootbox.alert("密码重置成功");
				}else{
					bootbox.alert("数据异常，设置失败");
				}
			}
	};
	
	bootbox.confirm("queding?","",function(r){
		if(r){
			ajax(opt);
		}
	});
	
}

</script>
</head>
<body>
	<div class="main-container container-fluid">
		<div class="row">
		    <div class="col-xs-12 col-md-12">
		        <div class="widget">
		            <div class="widget-body">
                    	<div class="table-toolbar">
                    		<form class="form-horizontal" id="queryForm">
                    			 <div class="has-feedback row">
	                            	<label class="col-sm-1 control-label">姓名</label>
	                            	<div class="col-sm-2">
		                                <input type="text" class="form-control" name="username" placeholder="姓名/用户名">
	                            	</div>
	                            	<div class="col-sm-8">
	                            		<a id="query" class="btn btn-default" href="javascript:void(0);"><i class="fa fa-search"></i> 查询</a>
										<a id="add" class="btn btn btn-sky" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加</a>
	                            	</div>
	                            </div>
                    		</form>
                        </div>
				        <table class="table table-striped table-bordered table-hover" id="userTab">
				        </table>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</body>
</html>