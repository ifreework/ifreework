			<!-- Page Sidebar -->
            <div class="page-sidebar" id="sidebar">
				<!-- Sidebar Menu -->
                <ul class="nav sidebar-menu">
                
                	
                	<!--Dashboard-->
                    <li>
                        <a href="index.html">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> 首页 </span>
                        </a>
                    </li>
	                <c:forEach items="${ menuList }" var="menu1">
	                <li>
	                	<a href="javascript:void(0)" class="${fn:length(menu1.children) > 0 ? 'menu-dropdown' : '' }" data-url="${menu1.resourceUrl == null || menu1.resourceUrl == '' ? '' : (contextPath + menu1.resourceUrl ) }">
	                		<i class="menu-icon ${menu1.iconCls }"></i>
	                		<span class="menu-text">${menu1.resourceName }</span>
	                		<c:if test="${fn:length(menu1.children) > 0 }">
	                			<i class="menu-expand"></i>
	                		</c:if>
	                	</a>
	                	<c:if test="${fn:length(menu1.children) > 0 }">
	                	<ul class="submenu">
	                		 <c:forEach items="${ menu1.children }" var="menu2">
	                		 <li>
	                			<a href="javascript:void(0)" class="${fn:length(menu2.children) > 0 ? 'menu-dropdown' : '' }" data-url="${menu2.resourceUrl == null || menu2.resourceUrl == '' ? '' : ( contextPath + menu2.resourceUrl ) }">
	                		 		<span class="menu-text">${menu2.resourceName }</span>
	                		 		<c:if test="${fn:length(menu2.children) > 0 }">
			                			<i class="menu-expand"></i>
			                		</c:if>
	                		 	</a>
	                		 	<c:if test="${fn:length(menu2.children) > 0 }">
	                		 	 <ul class="submenu">
	                		 	 	 <c:forEach items="${ menu2.children }" var="menu3">
	                		 	 	 <li>
	                		 	 	 	<a href="javascript:void(0)" data-url="${ contextPath }${menu3.resourceUrl }">
	                                        <i class="menu-icon ${menu3.iconCls }"></i>
	                                        <span class="menu-text">${menu3.resourceName }</span>
	                                    </a>
	                		 	 	 </li>
	                		 	 	 </c:forEach>
	                		 	 </ul>
	                		 	</c:if>
	                		 </li>
	                		 </c:forEach>
	                	</ul>
	                	</c:if>
					</li>
					</c:forEach>
                
                </ul>
                <!-- /Sidebar Menu -->
           </div>
    		<!-- /Page Sidebar -->