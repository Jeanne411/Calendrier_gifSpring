package fr.humanbooster.fx.calendrier_gif.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.dao.UtilisateurDao;
import fr.humanbooster.fx.calendrier_gif.service.UtilisateurService;
import fr.humanbooster.fx.calendrier_gif.util.NbInscrits;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

	private final UtilisateurDao utilisateurDao;

	@Override
	public Utilisateur enregistrerUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.save(utilisateur);
	}

	@Override
	public Utilisateur recupererUtilisateur(String email, String motDePasse) {
		return utilisateurDao.findByEmailAndMotDePasse(email, motDePasse);
	}

	@Override
	public Utilisateur recupererUtilisateur(Long id) {
		return utilisateurDao.findById(id).orElse(null);
	}

	@Override
	public List<NbInscrits> recupererNbInscrits() {
		return utilisateurDao.findNbInscrits();
	}

	@Override
	public long compterUtilisateurs() {
		return utilisateurDao.count();
	}

}
