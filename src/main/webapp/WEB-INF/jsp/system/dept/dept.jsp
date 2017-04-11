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
</head>
<body>
	<div class="main-container container-fluid">
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="widget">
                                <div class="widget-header ">
                                    <span class="widget-caption">Simple DataTable</span>
                                    <div class="widget-buttons">
                                        <a href="#" data-toggle="maximize">
                                            <i class="fa fa-expand"></i>
                                        </a>
                                        <a href="#" data-toggle="collapse">
                                            <i class="fa fa-minus"></i>
                                        </a>
                                        <a href="#" data-toggle="dispose">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="widget-body">
                                    <table class="table table-striped table-bordered table-hover" id="simpledatatable">
                                        <thead>
                                            <tr>
                                                <th>
                                                    <div class="checker"><span class=""><input type="checkbox" class="group-checkable" data-set="#flip .checkboxes"></span></div>
                                                </th>
                                                <th>
                                                    Username
                                                </th>
                                                <th>
                                                    Email
                                                </th>
                                                <th>
                                                    Points
                                                </th>
                                                <th>
                                                    Joined
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    shuxer
                                                </td>
                                                <td>
                                                    <a href="mailto:shuxer@gmail.com">shuxer@gmail.com</a>
                                                </td>
                                                <td>
                                                    120
                                                </td>
                                                <td class="center ">
                                                    12 Jan 2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    looper
                                                </td>
                                                <td>
                                                    <a href="mailto:looper90@gmail.com">looper90@gmail.com</a>
                                                </td>
                                                <td>
                                                    120
                                                </td>
                                                <td class="center ">
                                                    12.12.2011
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    userwow
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@yahoo.com">userwow@yahoo.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    user1wow
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">userwow@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.12.2012
                                                </td>
                                            </tr>
                                            <tr class="odd gradeX">
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    restest
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">test@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    foopl
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    19.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    weep
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    19.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    coop
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    19.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    pppol
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    19.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    test
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    19.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    userwow
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">userwow@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    test
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">test@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    goop
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    weep
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    15.11.2011
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    toopl
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    16.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    userwow
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">userwow@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    9.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    tes21t
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">test@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    14.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    fop
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    13.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    kop
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    17.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    vopl
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    19.11.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    userwow
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">userwow@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    wap
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">test@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    12.12.2012
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    test
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    19.12.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    toop
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    17.12.2010
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="checker"><span class=""><input type="checkbox" class="checkboxes" value="1"></span></div>
                                                </td>
                                                <td>
                                                    weep
                                                </td>
                                                <td>
                                                    <a href="mailto:userwow@gmail.com">good@gmail.com</a>
                                                </td>
                                                <td>
                                                    20
                                                </td>
                                                <td class="center ">
                                                    15.11.2011
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
	</div>
</body>
</html>