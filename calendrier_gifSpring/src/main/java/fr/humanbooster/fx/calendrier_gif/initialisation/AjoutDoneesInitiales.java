package fr.humanbooster.fx.calendrier_gif.initialisation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import fr.humanbooster.fx.calendrier_gif.business.Emotion;
import fr.humanbooster.fx.calendrier_gif.business.Jour;
import fr.humanbooster.fx.calendrier_gif.business.Theme;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.dao.EmotionDao;
import fr.humanbooster.fx.calendrier_gif.dao.JourDao;
import fr.humanbooster.fx.calendrier_gif.dao.ThemeDao;
import fr.humanbooster.fx.calendrier_gif.dao.UtilisateurDao;
import lombok.AllArgsConstructor;

// grâce à cette annotation Spring va ajouter une instance de cette classe dans son conteneur
@Component
@AllArgsConstructor
public class AjoutDoneesInitiales implements CommandLineRunner {

	private final EmotionDao emotionDao;

	private List<Emotion> emotions = new ArrayList<>();

	private final ThemeDao themeDao;

	private List<Theme> themes = new ArrayList<>();

	private final JourDao jourDao;

	private List<Jour> jours = new ArrayList<>();

	private final UtilisateurDao utilisateurDao;

	private List<Utilisateur> utilisateurs = new ArrayList<>();

	private final Faker faker = new Faker(new Locale("fr-FR"));

	private final Random random = new Random();

	@Override
	public void run(String... args) throws Exception {

		Date dateDebut = new Date();

		ajouterJours();
		jourDao.saveAll(jours);

		ajouterThemes();
		themeDao.saveAll(themes);

		ajouterEmotions();
		emotionDao.saveAll(emotions);

		ajouterUtilisateurs();

		ajouterUtilisateurParDefaut();
		utilisateurDao.saveAll(utilisateurs);

		Date dateFin = new Date();
		System.out.println(dateFin.getTime() - dateDebut.getTime());
	}

	private void ajouterJours() {
		if (jourDao.count() == 0) {

			System.out.println("ajout jours:");
			int anneeEnCours = LocalDate.now().getYear();
			int moisEnCours = LocalDate.now().getMonthValue();
			LocalDate localDate = LocalDate.of(anneeEnCours, moisEnCours, 1);
			int nbJoursDuMoisEnCours = localDate.lengthOfMonth();
			for (int i = 1; i <= nbJoursDuMoisEnCours; i++) {
				jours.add(new Jour(localDate));
				localDate = localDate.plusDays(1);

			}
		}
	}

	private void ajouterThemes() {
		if (themeDao.count() == 0) {

			System.out.println("ajout themes:");
			themes.add(new Theme("Solitaire"));
			themes.add(new Theme("Dark"));
		}
	}

	private void ajouterEmotions() {
		if (emotionDao.count() == 0) {

			System.out.println("ajout emotions:");
			emotions.add(new Emotion("Souriant", "&#x1F600;"));
			emotions.add(new Emotion("Monocle", "&#x1F9D0;"));
			emotions.add(new Emotion("Bisous", "&#x1F618;"));
			emotions.add(new Emotion("Coeur", "&#x1F60D;"));
			emotions.add(new Emotion("PTDR", "&#x1F923;"));
		}
	}

	private void ajouterUtilisateurs() {
		if (utilisateurDao.count() == 0) {

			// Creation d'une boucle pour générer des utilisateurs aléatoires
			System.out.println("ajout utilisateurs:");
			for (int i = 0; i < 10000; i++) {
				// Utilisateurs possédant le thème 1
				utilisateurs.add(new Utilisateur(
						faker.date().past(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
								.toLocalDateTime(),
						faker.name().lastName(), faker.name().firstName(), faker.internet().emailAddress(),
						faker.internet().password(), themes.get(random.nextInt(themes.size()))));
			}
		}
	}

	private void ajouterUtilisateurParDefaut() {
		System.out.println("ajout utilisateur par défaut:");
		utilisateurs.add(new Utilisateur("GOMEZ", "Jeanne", "j.g@hb.com", "12345", themeDao.getById(1L)));

	}
}
