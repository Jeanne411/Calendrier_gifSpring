package fr.humanbooster.fx.calendrier_gif.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.service.GifService;
import fr.humanbooster.fx.calendrier_gif.service.JourService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GifController {

	private final GifService gifService;
	private final JourService jourService;
	private final HttpSession httpSession;
	protected final static String DOSSIER_SEP = "src/main/Webapp/images/";

	@GetMapping("calendrier/gifDistant")
	public ModelAndView gifDistantGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("gifDistant");

		return mav;
	}

	@PostMapping("calendrier/gifDistant")
	public ModelAndView gifDistantPost(@RequestParam("url") String url, @RequestParam("legende") String legende,
			@RequestParam("date") String dateUrl) {

		LocalDate date = LocalDate.parse(dateUrl);

		Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

		// On vérifie que le solde de l'utilisateur soit suffisant
		if (utilisateur.getNbPoints() > jourService.recupererJour(date).getNbPoints()) {
			gifService.ajouterGifDistant(url, legende, jourService.recupererJour(date), utilisateur);
			return new ModelAndView("redirect:/calendrier");

		} else {
			return new ModelAndView("redirect:/calendrier");

		}

	}

	@GetMapping("calendrier/gifTeleverse")
	public ModelAndView gifTeleverseGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("gifTeleverse");

		return mav;
	}

	@PostMapping("calendrier/gifTeleverse")
	public ModelAndView gifTeleversePost(@RequestParam("legende") String legende, @RequestParam("date") String dateUrl,
			@RequestParam("nom_fichier_original") MultipartFile multipartFile) throws IOException {

		LocalDate date = LocalDate.parse(dateUrl);

		Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

		String nomFichierOriginal = multipartFile.getOriginalFilename();

		enregistrerFichier(nomFichierOriginal, multipartFile);
		
		if (utilisateur.getNbPoints() > jourService.recupererJour(date).getNbPoints()) {
			gifService.ajouterGifTeleverse(nomFichierOriginal, legende, jourService.recupererJour(date), utilisateur);
			return new ModelAndView("redirect:/calendrier");

		} else {
			return new ModelAndView("redirect:/calendrier");

		}

	}

	protected static void enregistrerFichier(String nom, MultipartFile multipartFile) throws IOException {
		Path chemin = Paths.get(DOSSIER_SEP);

		if (!Files.exists(chemin)) {
			Files.createDirectories(chemin);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path cheminFichier = chemin.resolve(nom);
			System.out.println(cheminFichier);
			Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Erreur d'écriture : " + nom, ioe);
		}
	}

}
