package co.edu.unbosque.back_cadena_lagenerica.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
	
	public List<Customer> findAll();
	
	public Customer save(Customer customer);

	public Optional<Customer> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public boolean hasBadValues(Customer customer);
	
}
