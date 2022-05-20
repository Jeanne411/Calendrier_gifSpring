package fr.humanbooster.fx.calendrier_gif.service;

import fr.humanbooster.fx.calendrier_gif.business.Reaction;
import fr.humanbooster.fx.calendrier_gif.business.Emotion;
import fr.humanbooster.fx.calendrier_gif.business.Gif;
import fr.humanbooster.fx.calendrier_gif.business.Utilisateur;

public interface ReactionService {

	Reaction ajouterReaction(Gif gif, Utilisateur urilisateur, Emotion emotion);
}
