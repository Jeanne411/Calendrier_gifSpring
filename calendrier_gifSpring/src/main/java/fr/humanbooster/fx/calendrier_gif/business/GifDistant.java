package fr.humanbooster.fx.calendrier_gif.business;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GifDistant extends Gif {

	@NotNull(message = "Merci de saisir une URL")
	@URL(message = "Merci de saisir une URL valide, elle doit se terminer par .gif, .Gif ou .GIF")
	private String url;
	
	public GifDistant(String url, String legende, Jour jour, Utilisateur utilisateur) {
		super(legende, jour, utilisateur);
		this.url = url;
	}

	@Override
	public String toString() {
		return "GifDistant: url: " + url + ", Id:" + getId() + ", DateHeureAjout: " + getDateHeureAjout()
				+ ", Legende: " + getLegende() + ", jour: " + getJour().getDate() + "]";
	}

}