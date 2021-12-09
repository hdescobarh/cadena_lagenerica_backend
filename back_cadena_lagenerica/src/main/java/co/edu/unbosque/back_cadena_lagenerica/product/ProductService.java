package co.edu.unbosque.back_cadena_lagenerica.product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
	
	public List<Product> findAll();
	
	public Product save(Product product);
	
	public Optional<Product> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public boolean hasBadValues(Product product);

}
