package fr.humanbooster.fx.calendrier_gif.service.impl;

import org.springframework.stereotype.Service;

import fr.humanbooster.fx.calendrier_gif.business.Emotion;
import fr.humanbooster.fx.calendrier_gif.business.Gif;
import fr.humanbooster.fx.calendrier_gif.business.Reaction;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;
import fr.humanbooster.fx.calendrier_gif.dao.ReactionDao;
import fr.humanbooster.fx.calendrier_gif.service.ReactionService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReactionServiceImpl implements ReactionService {

	private final ReactionDao reactionDao;

	@Override
	public Reaction ajouterReaction(Gif gif, Utilisateur utilisateur, Emotion emotion) {
		return reactionDao.save(new Reaction(gif, utilisateur, emotion));
	}

}
