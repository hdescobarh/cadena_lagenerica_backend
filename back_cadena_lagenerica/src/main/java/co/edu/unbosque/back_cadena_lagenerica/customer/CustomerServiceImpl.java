package co.edu.unbosque.back_cadena_lagenerica.customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}


	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);	
	}
	
	@Override
	public boolean existsById(Long id) {
		return customerRepository.existsById(id);
	}

	@Override
	public boolean hasBadValues(Customer customer) {
		
		String emptyString = "";
		Long badLongThreshold = -1L;
				
		boolean check_incorrect = (customer.getCedula_cliente() < badLongThreshold) ||
				Objects.equals(customer.getEmail_cliente(), emptyString) ||
				Objects.equals(customer.getNombre_cliente(), emptyString) ||
				Objects.equals(customer.getDireccion_cliente(), emptyString) ||
				Objects.equals(customer.getTelefono_cliente(), emptyString);
		
		boolean check_nulls = Objects.isNull(customer.getCedula_cliente()) ||
				Objects.isNull(customer.getEmail_cliente()) ||
				Objects.isNull(customer.getNombre_cliente()) ||
				Objects.isNull(customer.getDireccion_cliente()) ||
				Objects.isNull(customer.getTelefono_cliente());	
		
		return check_incorrect || check_nulls;
	}

}
