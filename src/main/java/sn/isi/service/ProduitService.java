/**
 * 
 */
package sn.isi.service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.isi.dao.IProduitRepository;
import sn.isi.dto.Produit;
import sn.isi.exception.RequestException;
import sn.isi.mapping.ProduitMapper;

/**
 * @author Ibrahima
 *
 */
@Service
public class ProduitService {
	
	private IProduitRepository iProduitRepository;
	private MessageSource messageSource;
	private ProduitMapper produitMapper;
	
	public ProduitService(IProduitRepository iProduitRepository, MessageSource messageSource,
			ProduitMapper produitMapper) {
		super();
		this.iProduitRepository = iProduitRepository;
		this.messageSource = messageSource;
		this.produitMapper = produitMapper;
	}
	
	@Transactional(readOnly = true)
	public List<Produit> getAppProduits() {
		return StreamSupport.stream(iProduitRepository.findAll().spliterator(), false)
                .map(produitMapper::toProduit)
                .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
    public Page<Produit> getUsers(Pageable pageable) {
        return iProduitRepository.findAll(pageable).map(produitMapper::toProduit);
    }
	
	@Transactional(readOnly = true)
    public Produit getProduit(int id) {
        return produitMapper.toProduit(iProduitRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id},
                Locale.getDefault()))));
    }
	
	@Transactional
    public Produit createProduit(Produit produit) {
        return produitMapper.toProduit(iProduitRepository.save(produitMapper.fromProduit(produit)));
    }
	
	@Transactional
    public Produit updateProduit(int id, Produit produit){
        return iProduitRepository.findById(id)
                .map(entity -> {
                	produit.setId(id);
                    return produitMapper.toProduit(iProduitRepository.save(produitMapper.fromProduit(produit)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("produit.notfound",
                new Object[]{id},
                Locale.getDefault())));
    }
	
	@Transactional
    public void deleteAppUser(int id) {
        try {
        	iProduitRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("produit.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
	
	
	
}
