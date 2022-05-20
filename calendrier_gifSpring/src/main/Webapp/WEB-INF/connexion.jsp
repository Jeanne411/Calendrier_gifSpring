
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
	<style type="text/css">
<%@ include file="Style/Solitaire.css"%>
</style>
</head>
<body>
<h1>Calendrier Gif</h1>
<c:if test="${param.notification ne null}"><h2>${param.notification}</h2></c:if>
<form action="connexion" method="post">
	<input type="email" name="EMAIL" placeHolder="Email" required><br>
	<input type="password" name="MOT_DE_PASSE" placeHolder="Mot de Passe" required><br>
	<input class="button" type="submit" value="Connexion">
</form>
<a href="inscription">S'inscrire</a>
</body>
</html>