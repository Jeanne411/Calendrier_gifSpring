<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>Gif téléversé</title>
</head>
<body>
<h1>Ajoutez votre gif :</h1>
<form action= "" method= "post" enctype="multipart/form-data">
        <input type="file" name="nom_fichier_original" placeHolder="nom du fichier" required><br>
		<input type="text" name="legende" placeHolder="Légende" required><br>
	    <input class="button" type="submit" value="Ajouter votre gif">
</form>

</body>
</html>