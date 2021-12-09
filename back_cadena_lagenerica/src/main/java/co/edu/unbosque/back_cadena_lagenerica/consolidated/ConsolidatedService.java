package co.edu.unbosque.back_cadena_lagenerica.consolidated;

import java.util.List;
import java.util.Optional;


public interface ConsolidatedService {
	
	public List<Consolidated> findAll();
	
	public Consolidated save(Consolidated consolidated);
	
	public Optional<Consolidated> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public boolean hasBadValues(Consolidated consolidated);

}
