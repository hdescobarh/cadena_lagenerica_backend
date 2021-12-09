package co.edu.unbosque.back_cadena_lagenerica.sale_details;

import java.util.List;
import java.util.Optional;

public interface SaleDetailsService {
	
	public List<SaleDetails> findAll();
	
	public SaleDetails save(SaleDetails saleDetails);
	
	public Optional<SaleDetails> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public boolean hasBadValues(SaleDetails saleDetails);

}
