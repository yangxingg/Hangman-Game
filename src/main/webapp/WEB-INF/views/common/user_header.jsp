<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- if cookie has userId -->

	if (session.getAttribute("Username") == null || session.getAttribute("Username").equals(""))
	