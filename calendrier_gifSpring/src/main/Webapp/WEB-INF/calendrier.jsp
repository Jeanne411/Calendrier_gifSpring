<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
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
<title>Calendrier Gif</title>
</head>
<body>
	<header>
		<div></div>
		<a href="calendrier/emotions">
		<button type="button" class="btn btn-outline-secondary">
		<i class="bi bi-emoji-smile-fill"></i>
		</button>
		</a>
		<h1>Calendrier Gif</h1>
		<nav>
			<a class="button" class=Deco href="deconnexion">Déconnexion</a>
		</nav>
        <div class = "btn-group">
        		<a href="calendrier?sort=nbPoints,desc">
			<button type="button" class="btn btn-outline-secondary">
				<i class="bi bi-joystick"></i>
			</button></a> 
        		<a href="calendrier?sort=date">
			<button type="button" class="btn btn-outline-secondary">
				<i class="bi bi-controller"></i>
			</button></a> 
        </div>
	</header>
	<div class=Ut>
		<h2>${sessionScope.utilisateur.prenom}</h2>
		<h2>${sessionScope.utilisateur.nbPoints}points</h2>
	</div>
	<table>
		<thead>
			<tr>
				<th>Jours</th>
				<th>Gifs</th>
				<th>Utilisateurs</th>
				<th>Réactions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pages.content}" var="jour">
				<tr>
					<td>${jour}</td>
					<td><c:choose>
							<c:when test="${jour.gif eq null}">
								<p>${jour.nbPoints}points</p>
								<ul>
									<li><a href="calendrier/gifDistant?date=${jour.date}">Placer un
											gif distant</a></li>
									<li><a href="calendrier/gifTeleverse?date=${jour.date}">Placer un
											gif téléversé</a></li>
								</ul>
							</c:when>
							<c:otherwise>
								<c:if test="${jour.gif.getClass().simpleName eq 'GifDistant'}">
									<img src="${jour.gif.url}">
								</c:if>
								<c:if test="${jour.gif.getClass().simpleName eq 'GifTeleverse'}">
                                        <img src="images/${jour.gif.nomFichierOriginal}" 
                                            alt="${jour.gif.nomFichierOriginal}"
                                            title="${jour.gif.nomFichierOriginal}">
                                    </c:if>
								<p>${jour.gif.legende}</p>
							</c:otherwise>
						</c:choose></td>
					<td>${jour.gif.utilisateur.prenom}</td>
					<td>
						<ul>
							<c:forEach items="${jour.gif.reactions}" var="reaction">
								<li>${reaction.emotion.code}${reaction.utilisateur.prenom}
									${reaction.utilisateur.nom}</li>
							</c:forEach>
							<c:if test="${jour.gif ne null}">
								<li><a href="calendrier/reaction?gif=${jour.gif.id}">Réagir</a></li>
							</c:if>
						</ul>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2>
		<c:if test="${!pages.first}">
			<a href="calendrier?page=0&sort=${sort}">&#x23EE;</a>
			<a href="calendrier?page=${pages.number-1}&sort=${sort}">&#x23EA;</a>
		</c:if>
		Page ${pages.getNumber()+1}
		<c:if test="${!pages.last}">
			<a href="calendrier?page=${pages.number+1}&sort=${sort}">&#x23E9;</a>
			<a href="calendrier?page=${pages.totalPages - 1}&sort=${sort}">&#x23ED;</a>
		</c:if>
	</h2>
</body>
</html>