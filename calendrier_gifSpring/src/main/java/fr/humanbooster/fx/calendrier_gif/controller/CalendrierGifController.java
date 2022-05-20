package fr.humanbooster.fx.calendrier_gif.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.fx.calendrier_gif.business.Emotion;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.service.EmotionService;
import fr.humanbooster.fx.calendrier_gif.service.GifService;
import fr.humanbooster.fx.calendrier_gif.service.JourService;
import fr.humanbooster.fx.calendrier_gif.service.ReactionService;
import fr.humanbooster.fx.calendrier_gif.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CalendrierGifController {

	private final GifService gifService;
	private final UtilisateurService utilisateurService;
	private final EmotionService emotionService;
	private final ReactionService reactionService;
	private final JourService jourService;
	private final HttpSession httpSession;
	private final static int NB_JOURS_PAR_PAGE = 7;

	// Toutes les méthodes de ce conrôleur devront renvoyer un objet de type
	// ModelAndView
	// (métaphore de la Danette Pop)
	// On indique sur quelle(s) URL(s) la méthode va écouter
	// Autrement dit : quelle(s) sont la ou les URLs que la méthode prend en charge
	// Equivalent de @WebServlet
	@RequestMapping({ "/index", "/" })
	public ModelAndView index() {
		// On déclare et on instancie un objet de type ModelAndView
		ModelAndView mav = new ModelAndView();

		// On définit le nom de la vue (== crème dessert)
		// Equivalent de request.getRequestDispatcher("WEB-INF/index.jsp")
		mav.setViewName("index");

		// On ajoute dans le modèle la liste des nbs d'inscrits
		// Equivalent de request.setAttribute("nbInscrits",
		// utilisateurService.recupererNbInscrits())
		mav.addObject("nbInscrits", utilisateurService.recupererNbInscrits());
		mav.getModel().put("nbTotalInscrits", utilisateurService.compterUtilisateurs());

		return mav;

	}

	@GetMapping("calendrier")
	public ModelAndView calendrierGet(@PageableDefault(size = NB_JOURS_PAR_PAGE, sort = "date") Pageable pageable) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("pages", jourService.recupererJours(pageable));
		mav.addObject("jours", jourService.recupererJours());

		mav.setViewName("calendrier");

		return mav;

	}

	@GetMapping("calendrier/emotions")
	public ModelAndView emotionsGet() {
		// On déclare et on instancie un objet de type ModelAndView
		ModelAndView mav = new ModelAndView();

		// On définit le nom de la vue (== crème dessert)
		// Equivalent de request.getRequestDispatcher("WEB-INF/emotions.jsp")
		mav.setViewName("emotions");

		mav.addObject("emotions", emotionService.recupererEmotions());

		return mav;
	}

	// Méthode invoquée lorsque qq1 se rend sur l'URL :
	// http://localhost:8080/emotion
	@GetMapping("calendrier/emotion")
	public ModelAndView emotionGet(@RequestParam(name = "ID", required = false, defaultValue = "0") Long id) {

		// On déclare et on instancie un objet de type ModelAndView
		ModelAndView mav = new ModelAndView();

		mav.setViewName("emotion");
		// On ajoute dans le mav l'objet emotion (si un id d'émotion a été précisé dans
		// l'URL
		mav.addObject("emotion", emotionService.recupererEmotion(id));

		return mav;
	}

	// Méthode invoquée lorsque qq1 clique sur le bouton Ajouter du formulaire HTML
	// de la page emotion.jsp
	@PostMapping("calendrier/emotion")
	public ModelAndView emotionPost(@RequestParam(name = "ID", required = false) Long id,
			@RequestParam("NOM") String nom, @RequestParam("CODE") String code) {

		if (id == null) {
			emotionService.ajouterEmotion(nom, code);
		} else {
			emotionService.modifierEmotion(id, nom, code);
		}

		return new ModelAndView("redirect:/calendrier/emotions");
	}

	@GetMapping("calendrier/reaction")
	public ModelAndView reactionGet(@RequestParam(name = "gif") Long id) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("gif", gifService.recupererGif(id));
		System.out.println(gifService.recupererGif(id));
		mav.addObject("emotions", emotionService.recupererEmotions());
		mav.setViewName("reaction");

		return mav;
	}

	@PostMapping("calendrier/reaction")
	public ModelAndView reactionPost(@RequestParam("emotion_id") Emotion emotion, @RequestParam("gif") Long id) {

		Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

		reactionService.ajouterReaction(gifService.recupererGif(id), utilisateur, emotion);

		return new ModelAndView("redirect:/calendrier");
	}

}