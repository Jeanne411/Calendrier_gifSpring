
<% /* JSP: Java Server Page (1999) Une JSP débute par les directives

La vue ne fait que de l'affichage


*/ %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% /* On fait appel à une bibliothèque de balises (taglib) : JSTL
    Java Standard Tag Library
    */ %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendrier Gif</title>
<c:if test="${sessionScope.utilisateur.theme.id==1}">
	<style type="text/css">
<%@ include file="Style/Solitaire.css"%>
</style>
</c:if>
<c:if test="${sessionScope.utilisateur.theme.id==2}">
	<style type="text/css">
<%@ include file="Style/Dark.css"%>
</style>
</c:if>
</head>
<body>
<h1>Calendrier Gif : les émotions</h1>
<a href="/calendrier">Retour</a>
<c:forEach items="${emotions}" var="emotion">
  <ul class="emojis">
	<li><a class="button" href="emotion?ID=${emotion.id}">Modifier</a></li>
	<li><p>${emotion.code} : ${emotion.nom}</p> </li>
  </ul>
</c:forEach>
<a class="button" href="emotion">Ajouter une émotion</a>
</body>
</html>