package sn.isi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Ibrahima
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 200)
	private String nom;
	@Column(nullable = false)
	private int qtStock;
	@ManyToOne
	private AppUserEntity appUser;
}
