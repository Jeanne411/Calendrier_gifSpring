package fr.humanbooster.fx.calendrier_gif.service;

import java.util.List;

import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.util.NbInscrits;

public interface UtilisateurService {

	Utilisateur enregistrerUtilisateur(Utilisateur utilisateur);

	Utilisateur recupererUtilisateur(String email, String motDePasse);

	Utilisateur recupererUtilisateur(Long id);

	List<NbInscrits> recupererNbInscrits();

	long compterUtilisateurs();

}
