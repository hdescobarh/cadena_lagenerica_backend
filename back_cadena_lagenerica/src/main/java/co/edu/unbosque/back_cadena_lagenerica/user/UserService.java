package co.edu.unbosque.back_cadena_lagenerica.user;

import java.util.List;
import java.util.Optional;


public interface UserService {
	
	
	public List<User> findAll();
	
	public User save(User user);
	
	//public User getById(Supplier id);
	
	public Optional<User> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public Optional<User> findByUsuario(String usuario);
	
	public String getJwtToken(String username);
	
	public boolean hasBadValues(User user);
		 

}
