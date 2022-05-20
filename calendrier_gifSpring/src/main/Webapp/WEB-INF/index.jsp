
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
<a class="button" href="connexion">Se connecter</a>
<a class="button" href="inscription">S'inscrire</a>
<h2>Utilisateurs ayant réagi au moins 5 fois</h2>
<ul>
<c:forEach items="${utilisateurs}" var="utilisateur">
	<li>${utilisateur.prenom}</li>
</c:forEach>
</ul>
<h2>Nb d'inscrits par année et par mois</h2>
<div class="Donnees">
<c:forEach items="${nbInscrits}" var="nbInscrit">
	${nbInscrit.annee}/${nbInscrit.mois} : ${nbInscrit.nbInscrits}<br>
</c:forEach>
Nombre d'inscrits : ${nbTotalInscrits}<br>
</div>
<div class="Donnees">
<jsp:include page="piedDePage.jsp"></jsp:include>
</div>
</body>
</html>