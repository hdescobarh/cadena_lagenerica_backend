package co.edu.unbosque.back_cadena_lagenerica.user;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
	// JpaRepository extends PagingAndSortingRepository that extends CrudRepository
	
	Optional<User> findByUsuario(String usuario);
	
}
