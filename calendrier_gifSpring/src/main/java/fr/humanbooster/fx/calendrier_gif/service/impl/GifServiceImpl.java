package fr.humanbooster.fx.calendrier_gif.service.impl;

import org.springframework.stereotype.Service;

import fr.humanbooster.fx.calendrier_gif.business.Gif;
import fr.humanbooster.fx.calendrier_gif.business.GifDistant;
import fr.humanbooster.fx.calendrier_gif.business.GifTeleverse;
import fr.humanbooster.fx.calendrier_gif.business.Jour;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.dao.GifDao;
import fr.humanbooster.fx.calendrier_gif.dao.GifDistantDao;
import fr.humanbooster.fx.calendrier_gif.dao.GifTeleverseDao;
import fr.humanbooster.fx.calendrier_gif.dao.UtilisateurDao;
import fr.humanbooster.fx.calendrier_gif.service.GifService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GifServiceImpl implements GifService {

	private final GifDistantDao gifDistantDao;
	private final GifTeleverseDao gifTeleverseDao;
	private final UtilisateurDao utilisateurDao;
	private final GifDao gifDao;
	
	@Override
	public Gif ajouterGifDistant(String url, String legende, Jour jour, Utilisateur utilisateur) {
	       GifDistant gifDistant = gifDistantDao.save(new GifDistant(url, legende, jour, utilisateur));
	        utilisateur.setNbPoints(utilisateur.getNbPoints() - gifDistant.getJour().getNbPoints());
	        utilisateurDao.save(utilisateur);
	        return gifDistant;
	}
	
	@Override
	public Gif ajouterGifTeleverse(String nomFichierOriginal, String legende, Jour jour, Utilisateur utilisateur) {
		GifTeleverse gifTeleverse = gifTeleverseDao.save(new GifTeleverse(nomFichierOriginal, legende, jour, utilisateur));
		utilisateur.setNbPoints(utilisateur.getNbPoints() - gifTeleverse.getJour().getNbPoints());
        utilisateurDao.save(utilisateur);
        return gifTeleverse;
	}
	
	@Override
    public Gif enregistrerGif(Gif gif) {
        return gifDao.save(gif);
    }
	
	@Override
	public Gif recupererGif(Long id) {
		return gifDao.findById(id).orElse(null);
	}
	
	@Override
	public Gif recupererGif(Jour jour) {
		return gifDao.findByJour(jour);
	}

	@Override
	public Gif modifierLegende(Long id, String legende) {
		    Gif gif = this.recupererGif(id);
	        gif.setLegende(legende);
	        return gifDao.save(gif);
	}

}
