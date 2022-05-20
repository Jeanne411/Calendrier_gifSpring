
<%
/* JSP: Java Server Page (1999) Une JSP débute par les directives

La vue ne fait que de l'affichage


*/
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%
/* On fait appel à une bibliothèque de balises (taglib) : JSTL
   Java Standard Tag Library
   */
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	<c:if test="${emotion.id eq null}">
		<h1>Calendrier Gif : Ajouter une émotion</h1>
	</c:if>
	<c:if test="${emotion.id ne null}">
		<h1>Calendrier Gif : Modifier une émotion</h1>
	</c:if>
	<form action="" method="post">
		<input type="hidden" name="ID" value="${emotion.id}"><br>
		<label>Nom</label> <input type="text" name="NOM"
			value="${emotion.nom}"><br> <label>Code</label> <input
			type="text" name="CODE" value="${emotion.code}"><br>
		<c:if test="${emotion eq null}">
			<input class="button" type="submit" value="Ajouter">
		</c:if>
		<c:if test="${emotion ne null}">
			<input class="button" type="submit" value="Modifier">
		</c:if>
	</form>
</body>
</html>