<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Reaction</title>
</head>
<body>
<h1>Reagissez à ce gif:</h1>
	<c:if test="${gif.getClass().simpleName eq 'GifDistant'}">
		<img src="${gif.url}" height="200" alt="Super image du calendrier !">
	</c:if>
	<c:if test="${gif.getClass().simpleName eq 'GifTeleverse'}">
		<img src="../images/${gif.nomFichierOriginal}"
			height="200" alt="${gif.nomFichierOriginal}"
			title="${gif.nomFichierOriginal}">
	</c:if>
<form action="" method="post">
        <label>Réaction</label>
        <select name="emotion_id">
        <option >Merci de choisir une réaction</option>
        <c:forEach items="${emotions}" var="emotion">
        <option value="${emotion.id}">${emotion.nom} : ${emotion.code}</option>
        </c:forEach>
        </select><br>
        <input class= button type="submit" value="Envoyer">
    </form>
</body>
</html>