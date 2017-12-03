<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <jsp:include page="/WEB-INF/views/common/user_header.jsp" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/lib/bootstrap/dist/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/game_start.css" />" />
<title>Hangman</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navigation.jsp" />
<!-- 
    // $Cust_ID = $_GET["id"];
    // $User_ID = $_COOKIE["User_ID"]; -->
   

    

    <div class="container-fluid" >     
        <div id="pictures" class="row">
            <div class="col-md-2 col-sm-2"></div> 
            <div class="col-md-8 col-sm-8">
                <img src="<c:url value="/Images/pic1.jpg" />" style="width: 100%; height: 100%;">
            </div> 
        </div>
        <div class="blank"></div>
        <div id="word" class="row">
            <div class="col-md-4 col-sm-4"></div>
            <div id="letters" class="col-md-4 col-sm-4">
            		<!-- start play game  -->
                <a class="btn btn-lg btn-block btn-success" href="gamestart.htm">start game</a>
            </div>
        </div>
        <div id="board" class="row ">
            <div class="col-md-2 col-sm-1"></div>
            <div id="buttons" class="col-md-8 col-sm-10">
               <div id="line1">
                   <div class="letterButton">
                       Q
                   </div>
                   <div class="letterButton">
                       W
                   </div>
                   <div class="letterButton">
                       E
                   </div>
                   <div class="letterButton">
                       R
                   </div>
                   <div class="letterButton">
                       T
                   </div>
                   <div class="letterButton">
                       Y
                   </div>
                   <div class="letterButton">
                       U
                   </div>
                   <div class="letterButton">
                       I
                   </div>
                   <div class="letterButton">
                       O
                   </div>
                   <div class="letterButton">
                       P
                   </div>
               </div>
               <div id="line2">
                    <div id="line2Blank">
                        
                    </div>
                    <div class="letterButton">
                       A
                   </div>
                   <div class="letterButton">
                       S
                   </div>
                   <div class="letterButton">
                       D
                   </div>
                   <div class="letterButton">
                       F
                   </div>
                   <div class="letterButton">
                       G
                   </div>
                   <div class="letterButton">
                       H
                   </div>
                   <div class="letterButton">
                       J
                   </div>
                   <div class="letterButton">
                       K
                   </div>
                   <div class="letterButton">
                       L
                   </div> 
               </div>
               <div id="line3">
                    <div id="line3Blank">
                        
                    </div>
                    <div class="letterButton">
                       Z
                   </div>
                   <div class="letterButton">
                       X
                   </div>
                   <div class="letterButton">
                       C
                   </div>
                   <div class="letterButton">
                       V
                   </div>
                   <div class="letterButton">
                       B
                   </div>
                   <div class="letterButton">
                       N
                   </div>
                   <div class="letterButton">
                       M
                   </div> 
               </div>
            </div>
            <div class="col-md-1">
                
            </div>
        </div>
     





        <footer class="footer">
            <hr>
               <p class="pull-right"> hangman by Xing</p>
        </footer>
    </div>



    
<!--     <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script> -->
     

    
  </body>
</html>


