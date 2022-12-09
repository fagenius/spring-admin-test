package sn.isi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Ibrahima
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
	private int id;
	@NotNull(message = "Le nom du produit ne doit pas être vide")
	private String nom;
	@NotNull(message = "La quantité de stock ne doit pas être vide")
	private int qtStock;
}
