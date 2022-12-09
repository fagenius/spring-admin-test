/**
 * 
 */
package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.isi.entities.ProduitEntity;

/**
 * @author Ibrahima
 *
 */

public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer>{

}
