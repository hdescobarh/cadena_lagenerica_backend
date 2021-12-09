package co.edu.unbosque.back_cadena_lagenerica.sale;

import java.util.List;
import java.util.Optional;


public interface SaleService {
	
	public List<Sale> findAll();
	
	public Sale save(Sale sale);
	
	public Optional<Sale> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public boolean hasBadValues(Sale sale);

}
