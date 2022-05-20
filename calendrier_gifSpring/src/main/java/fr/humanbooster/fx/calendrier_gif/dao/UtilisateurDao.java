package fr.humanbooster.fx.calendrier_gif.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.humanbooster.fx.calendrier_gif.business.Emotion;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.util.NbInscrits;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);
	// L'annotation @Query accueille par défaut une requête HQL

	@Query(value = """
			FROM Utilisateur
			ORDER BY prenom
			""")
	List<Utilisateur> findAll();

	@Query(value = """
			FROM Emotion
			WHERE nom LIKE 's%'
			""")

	List<Emotion> findEmotionsHavingNameStartingWithS();

	@Query(value = """
			FROM Utilisateur
			WHERE id NOT IN
			(SELECT DISTINCT utilisateur.id from Gif)
			""")
	List<Emotion> findNonContributingUsers();

//  Ecrire la requête HQL qui donne le nom et le prénom des utilisateurs s’étant inscrits au mois d’avril 2022 et dont l’adresse email email se termine par @hb.com
	@Query(value = """
			SELECT nom, prenom
			FROM Utilisateur
			WHERE month(dateHeureInscription)='4'
			AND year(dateHeureInscription)='2022'
			AND email LIKE '%@hb.com'
			""")
	List<Utilisateur> findInscriptionAvrilUsers();

	List<Utilisateur> findByReactionsEmotionNomEquals(String nom);

	@Query(value = """
			SELECT new fr.humanbooster.fx.calendrier_gif.util.NbInscrits(year(u.dateHeureInscription),
			       month(u.dateHeureInscription), COUNT(*) as nbutilisateurs)
			FROM Utilisateur u
			GROUP BY month(u.dateHeureInscription), year(u.dateHeureInscription)
			""")
	List<NbInscrits> findNbInscrits();

}
