package fr.humanbooster.fx.calendrier_gif.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.fx.calendrier_gif.business.Jour;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;

public interface JourDao extends JpaRepository<Jour, LocalDate> {

	List<Jour> findByGif(Utilisateur utilisateur);

	List<Jour> findByGifNull();

	List<Jour> findByGifNullAndNbPointsGreaterThanEqualAndDateBefore(int nbPointsDonne, LocalDate dateMax);

	Jour findByDate(LocalDate date);

	Jour findFirstByOrderByDateDesc();

}
