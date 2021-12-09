package co.edu.unbosque.back_cadena_lagenerica.supplier;

import java.util.List;
import java.util.Optional;


public interface SupplierService {
	
	public List<Supplier> findAll();
	
	public Supplier save(Supplier supplier);
	
	public Optional<Supplier> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public boolean hasBadValues(Supplier supplier);

}
