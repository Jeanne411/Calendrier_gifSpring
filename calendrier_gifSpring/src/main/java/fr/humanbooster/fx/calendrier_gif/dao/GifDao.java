package fr.humanbooster.fx.calendrier_gif.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.humanbooster.fx.calendrier_gif.business.Gif;
import fr.humanbooster.fx.calendrier_gif.business.Jour;

public interface GifDao extends JpaRepository<Gif, Long> {

	Gif findByJour(Jour jour);

	// Renvoie les gif en ordre décroissant de leurs réactions
	// Les gifs n'ayant pas de récations sont exclus de la requête
	@Query(value = """
			SELECT r.gif
			FROM Reaction r
			GROUP BY r.gif
			ORDER BY count(*) DESC
			""")
	List<Gif> sortByReaction();
}
