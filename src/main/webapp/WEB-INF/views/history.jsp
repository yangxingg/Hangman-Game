<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/lib/bootstrap/dist/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/Css/history_view.css" />" />
<title>Hangman</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navigation.jsp" />
    <div class="container-fluid" >    
        <div id="pictures" class="row">
            <div class="col-md-2"></div> 
            <div class="col-md-8">
            	    <table class="table table-dark">
            	    		<tr>
            	    			<td><label>Win: <c:out value="${sessionScope.failCount}" /></label></td>
            	    			<td><label>Fail: <c:out value="${sessionScope.succeCount}" /></label></td>
            	    			<td><label>Win Rate: <c:out value="${sessionScope.ratio}%" /></label></td>
            	    		</tr>
            	    </table>
                <table class="table table-dark">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Time</th>
                      <th scope="col">Result</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach var = "history" items="${sessionScope.historyTime}" varStatus="status">
                    <tr>
                      <th scope="row">${status.index + 1 }</th>
                      <td><c:out value="${history}"/></td>
                      <td>
                     	<c:out value="${sessionScope.historyRes[status.index]}"/>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
            </div> 
        </div>
        <div class="blank"></div>
        <div id="word" class="row">
            <div class="col-md-4"></div>
            <div id="letters" class="col-md-4">
                <a class="btn btn-lg btn-block btn-success" href="gamestart.htm">start game</a>
            </div>
        </div>
        <footer class="footer">
            <hr>
               <p class="pull-right"> hangman by Xing</p>
        </footer>
    </div>
  </body>
</html>


