<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head></head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>信息修改</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/user/css/bootstrap.min.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/user/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/user/js/bootstrap.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/user/css/style.css"
	type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
	 float:left; */
	
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
</head>
<body>

	<!-- 动态包含 -->
	<jsp:include page="/user/jsp/head.jsp"></jsp:include>

	<div class="container"
		style="width:100%;background:url('${pageContext.request.contextPath}/user/image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>

			<div class="col-md-8"
				style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
				<font>基础信息修改</font>
				<form class="form-horizontal" style="margin-top:5px;"
					action="${pageContext.request.contextPath }/User?method=updateUser"
					method="post">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								placeholder="请输入用户名" name="username" style="width:300px;"
								value="${user.username}" disabled="true">
						</div>
						<div id="div1"></div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3"
								placeholder="Email" name="email" style="width:300px;"
								value="${user.email}">
						</div>
						<div id="div4"></div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption"
								placeholder="请输入姓名" name="name" style="width:300px;"
								value="${user.name}">
						</div>
						<div id="div5"></div>
					</div>

					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<c:if test="${user.sex == '男' }">
								<label class="radio-inline"> <input type="radio"
									id="inlineRadio1" name="sex" value="男" checked="checked"> 男
								</label>
								<label class="radio-inline"> <input type="radio"
									id="inlineRadio2" name="sex" value="女">
									女
								</label>
							</c:if>
							<c:if test="${user.sex == '女' }">
								<label class="radio-inline"> <input type="radio"
									id="inlineRadio1" name="sex" value="男"> 男
								</label>
								<label class="radio-inline"> <input type="radio"
									id="inlineRadio2" name="sex" value="女" checked='checked'>
									女
								</label>
							</c:if>
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" id="mybirth" class="form-control"
								name="birthday" placeholder="请输入出生日期" style="width:300px;"
								value="${user.birthday}">
						</div>
						<div id="div8"></div>
					</div>

					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">手机</label>
						<div class="col-sm-6">
							<input type="text" id="phone" class="form-control"
								id="usercaption" placeholder="请输入手机" name="telephone"
								style="width:300px;" value="${user.telephone}">
						</div>
						<div id="div7"></div>
					</div>




					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="修改" name="submit"
								border="0"
								style="background: url('${pageContext.request.contextPath}/user/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 动态包含 -->
	<jsp:include page="/user/jsp/foot.jsp"></jsp:include>

</body>
</html>
