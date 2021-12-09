package co.edu.unbosque.back_cadena_lagenerica.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	
	@Override
	public User save(User user) {
		User newUser = new User();
		newUser.setCedula_usuario(user.getCedula_usuario());
		newUser.setEmail_usuario(user.getEmail_usuario());
		newUser.setNombre_usuario(user.getNombre_usuario());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setUsuario(user.getUsuario());
		return userRepository.save(newUser);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}


	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);	
	}
	
	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}
	
	@Override
	public Optional<User> findByUsuario(String usuario) {
		return userRepository.findByUsuario(usuario);
	}
	
	@Override
	public boolean hasBadValues(User user) {
		
		String emptyString = "";
		Long badLongThreshold = -1L;
				
		boolean check_incorrect = (user.getCedula_usuario() < badLongThreshold) ||
				Objects.equals(user.getEmail_usuario(), emptyString) ||
				Objects.equals(user.getNombre_usuario(), emptyString) ||
				Objects.equals(user.getPassword(), emptyString) ||
				Objects.equals(user.getUsuario(), emptyString);
		
		boolean check_nulls = Objects.isNull(user.getCedula_usuario()) ||
				Objects.isNull(user.getEmail_usuario()) ||
				Objects.isNull(user.getNombre_usuario()) ||
				Objects.isNull(user.getPassword()) ||
				Objects.isNull(user.getUsuario());	
		
		return check_incorrect || check_nulls;
	}
	
	
	// creates token setting expiration date and authorities
	@Override
	public String getJwtToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("LaGenericaJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
