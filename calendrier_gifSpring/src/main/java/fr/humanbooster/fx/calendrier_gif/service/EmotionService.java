package fr.humanbooster.fx.calendrier_gif.service;

import java.util.List;

import fr.humanbooster.fx.calendrier_gif.business.Emotion;

public interface EmotionService {

	void ajouterEmotion(String nom, String code);

	List<Emotion> recupererEmotions();

	Emotion recupererEmotion(Long id);

	Emotion modifierEmotion(Long id, String nom, String code);

	void supprimerEmotion(Emotion emotion);

}
