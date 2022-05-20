package fr.humanbooster.fx.calendrier_gif.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "emotion")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Emotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nom;

	@NotBlank
	private String code;

	public Emotion(String nom, String code) {
		this.nom = nom;
		this.code = code;
	}
}