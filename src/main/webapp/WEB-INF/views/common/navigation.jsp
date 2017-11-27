 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <nav class="navbar ">
        <a class="navbar-brand" href="gamestart.htm"><img src="<c:url value="/Images/brand.jpg" />" width="50" /> <span class="second text-info"> | Hangman</span></a></a>
        <div >
            <span aria-hidden="true" class="text-secondary" title="user"><i class="fa fa-user-circle-o" aria-hidden="true"></i>&nbsp;${sessionScope.userName}</span>&nbsp;  &nbsp;&nbsp;
            <a class="text-secondary" title="view history" href="history_view.htm"><i class="fa fa-history" aria-hidden="true" class="text-secondary"></i>&nbsp;history</a>&nbsp;
            <a href="../logout.htm" class="text-secondary" title="sign out account">&nbsp;Sign out</a>
        </div>
 </nav>