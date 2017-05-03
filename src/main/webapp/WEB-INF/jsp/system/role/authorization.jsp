<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
	(function(){
		var $authorization ;
		$().ready(function(){
			$authorization = $("#authorization");
			$authorization.find("#menu").load("${contextPath}/system/roleauthority/menu",{roleId:"${roleId}"});
		});
	}());
</script>
<div class="container-content" id="authorization">
	<div class="container-body">
		<div class="row">
			<div class="col-lg-12 col-sm-12 col-xs-12">
				<div class="tabbable">
					
					<ul class="nav nav-tabs" id="myTab">
						<li class="active col-sm-4 no-padding">
							<a data-toggle="tab" href="#menu">
								访问授权
							</a>
						</li>

						<li class="col-sm-4 no-padding">
							<a data-toggle="tab" href="#data">
								数据授权
							</a>
						</li>

						<li class="col-sm-4 no-padding">
							<a data-toggle="tab" href="#button">
								按钮授权
							</a>
						</li>
					</ul>

					<div class="tab-content">
						<div id="menu" class="tab-pane in active">
						</div>

						<div id="data" class="tab-pane">
						</div>
						
						<div id="button" class="tab-pane">
						</div>
					</div>
				</div>
				<div class="horizontal-space"></div>

				
			</div>
		</div>

	</div>
</div>