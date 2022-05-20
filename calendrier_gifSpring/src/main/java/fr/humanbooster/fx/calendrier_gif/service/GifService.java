package fr.humanbooster.fx.calendrier_gif.service;

import fr.humanbooster.fx.calendrier_gif.business.Jour;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;

import fr.humanbooster.fx.calendrier_gif.business.Gif;


public interface GifService {

	Gif ajouterGifDistant(String url, String legende, Jour jour, Utilisateur utilisateur);
	
	Gif ajouterGifTeleverse(String nomFichierOriginal, String legende, Jour jour, Utilisateur utilisateur);
	
	Gif enregistrerGif(Gif gif);
	
	Gif recupererGif(Long id);
	
	Gif recupererGif(Jour jour);
	
	Gif modifierLegende(Long id, String legende);
}
