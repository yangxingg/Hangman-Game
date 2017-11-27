<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/lib/bootstrap/dist/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/sign_in.css" />" />
	    <script type="text/javascript" src="<c:url value="/resources/lib/jquery-3.2.1.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/lib/jqueryValidation/dist/jquery.validate.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/JS/sign_in.js" />"></script>
	<script src="<c:url value="/resources/ajax.js" />" ></script>
<title>Insert title here</title>
</head>
<body style="background-image:url(<c:url value="/Images/background.jpg" />); background-size: 100%;"> 
 
    <div class="navbar">
        <div class="navbar-inner">
			<a href="jumpToSignin.htm" class="webName text-info"><h2>Hangman</h2></a>
        </div>
    </div>
 
    <div class="row-fluid container" >
        <div class="row justify-content-md-center">
		<div class="col-12 col-md-auto" style="max-width: 20rem;">
			<div class="card-body" style=" background-color: rgba(245, 245, 245, 0.3);">
				<h3 class="block-heading text-secondary">Welcome</h3>
				<div class="block-body">
					<form id="signup" name="signin" action="user/signin.htm" method="post">
						<br/>
                      
							  <input class="span11 form-control" id="userName" name="userName" placeholder="User Name" >
						
						<br/>
						
							  <input class="span11 form-control" id="password" name="password" type="password" placeholder="Password" >
					
						<br/>
                       
                    
						<div class="centered">
						<span id="error_msg_signin" style="color:#FF0000 ; font-size:16px" align="center" >&nbsp;</span>
						<input class="btn btn-outline-info" name="submit" type="submit" id="submit" value="&nbsp; Sign in &nbsp;"  />
                        </div>
                        <div class="righted ">
                            <a href="jumpToSignup.htm " class="text-muted">Do not have an account</a>
						</div>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</div>
        </div>
	</div>
       
	
   
<!--     <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script> -->
    
  </body>
</html>