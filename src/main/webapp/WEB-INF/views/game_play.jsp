<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <jsp:include page="/WEB-INF/views/common/user_header.jsp" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/lib/bootstrap/dist/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/lib/jquery-3.2.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/JS/game_play.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/ajax.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/game_play.css" />" />
<title>Hangman</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navigation.jsp" />

    <div class="container-fluid" >     
        <div id="pictures" class="row">
           <div id="image">
                <img alt="err" src="<c:url value="${sessionScope.img}"/> "> 
            </div> 
        </div>
        <div class="blank"></div>
        <div id="word" class="row">
            <div id=" letters"  style="margin: auto;">
            	<!-- generate word guseeing section -->
            	   <div id="letters_result"></div>
               <c:forEach var='i' begin="1" end="${sessionScope.wordLength}"  >
               		<div class="word_result" style="display:inline-block; vertical-align: middle;  border-bottom: 2px solid white; width:50px; height:50px;"></div>
               </c:forEach>
            </div>
        </div>
        <div id="board" class="row">
            <div class="col-md-2"></div>
            <div id="buttons" class="col-md-8">
               <div id="line1">
                   <div class="letterButton">
                       <button class="letterBtn" value="Q" >Q</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="W">W</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="E">E</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="R">R</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="T">T</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="Y">Y</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="U">U</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="I">I</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="O">O</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="P">P</button>
                   </div>
               </div>
               <div id="line2">
                    <div id="line2Blank">
                        
                    </div>
                    <div class="letterButton">
                       <button class="letterBtn" value="A">A</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="S">S</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="D">D</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="F">F</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="G">G</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="H">H</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="J">J</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="K">K</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="L">L</button>
                   </div> 
               </div>
               <div id="line3">
                    <div id="line3Blank">
                        
                    </div>
                    <div class="letterButton">
                       <button class="letterBtn" value="Z">Z</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="X">X</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="C">C</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="V">V</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="B" >B</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="N">N</button>
                   </div>
                   <div class="letterButton">
                       <button class="letterBtn" value="M">M</button>
                   </div> 
               </div>
            </div>
            <div class="col-md-2" id="restart">
                <a href="gameRestart.htm" title="restart" ><i class="fa fa-refresh fa-3x" aria-hidden="true"></i></a>
            </div>
            <div class="col-md-12">
            		<div id="gameResult" class="col-md-4">
            			<h3 id="winGame"><a href="gamestart.htm">You win! click to start again!</a></h3>
            			<h3 id="failGame"><a href="gamestart.htm">Try One more! click to start again!</a></h3>
            		</div>
            </div>
        </div>
        <footer class="footer">
            <hr>
               <p class="pull-right"> hangman by Xing</p>
        </footer>
    </div>
  </body>
</html>


    