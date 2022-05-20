package fr.humanbooster.fx.calendrier_gif.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.service.ThemeService;
import fr.humanbooster.fx.calendrier_gif.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Controller
@SessionAttributes
@AllArgsConstructor
public class SessionController {

	private final UtilisateurService utilisateurService;
	private final ThemeService themeService;
	private final HttpSession httpSession;

	@GetMapping("inscription")
	public ModelAndView inscriptionGet(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("inscription");

		mav.addObject("themes", themeService.recupererThemes());

		return mav;
	}

	@PostMapping("inscription")
	public ModelAndView inscriptionPost(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
			BindingResult result) {

		utilisateurService.enregistrerUtilisateur(utilisateur);

		return new ModelAndView("redirect:connexion");
	}

	@GetMapping("connexion")
	public ModelAndView connexionGet() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("connexion");

		return mav;

	}

	@PostMapping("connexion")
	public ModelAndView connexionPost(@RequestParam(name = "EMAIL") String email,
			@RequestParam(name = "MOT_DE_PASSE") String motDePasse) {

		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(email, motDePasse);

		System.out.println(utilisateurService.recupererUtilisateur(email, motDePasse));
		if (utilisateur == null) {
			ModelAndView mav = new ModelAndView("redirect:index");
			return mav;
		} else {
			httpSession.setAttribute("utilisateur", utilisateur);
			ModelAndView mav = new ModelAndView("redirect:calendrier");
			return mav;
		}

	}

	@GetMapping("deconnexion")
	public ModelAndView deconnexionGet() {

		httpSession.invalidate();

		return new ModelAndView("redirect:/index");
	}
}
