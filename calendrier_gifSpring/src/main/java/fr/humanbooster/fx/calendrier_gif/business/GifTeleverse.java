package fr.humanbooster.fx.calendrier_gif.business;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class GifTeleverse extends Gif {

	@Column(name = "nom_fichier_original")
	private String nomFichierOriginal;

	public GifTeleverse(String nomFichierOriginal, String legende, Jour jour, Utilisateur utilisateur) {
		super(legende, jour, utilisateur);
		this.nomFichierOriginal = nomFichierOriginal;
	}
}
