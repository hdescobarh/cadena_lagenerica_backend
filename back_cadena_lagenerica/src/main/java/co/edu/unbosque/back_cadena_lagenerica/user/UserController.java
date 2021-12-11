package co.edu.unbosque.back_cadena_lagenerica.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // It's a convenient annotation that combines @Controller and @ResponseBody
public class UserController {

	private final UserService userService;
	
	private final UserModelAssembler userAssembler;
	
	private final PasswordEncoder passwordEnconder;
	
	UserController(UserService userService, UserModelAssembler userAssembler, PasswordEncoder passwordEnconder) {
		this.userService = userService;
		this.userAssembler = userAssembler;
		this.passwordEnconder = passwordEnconder;
	}
	
	@PostMapping("/usuarios/login")
	public ResponseEntity<?> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		var found_user = userService.findByUsuario(username);
		if(found_user.isPresent()) {
			if(passwordEnconder.matches(pwd, found_user.get().getPassword())) {
				String token = userService.getJwtToken(username);
				User user = new User();
				user.setUsuario(username);
				user.setCedula_usuario(found_user.get().getCedula_usuario());
				user.setToken(token);
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	

	@GetMapping("/usuarios/listar")	
	ResponseEntity<CollectionModel<EntityModel<User>>> findAll() {
	  List<EntityModel<User>> usuarios = userService.findAll().stream()
	      .map(userAssembler::toModel)
	      .collect(Collectors.toList());
	  return ResponseEntity.ok(
			  CollectionModel.of(usuarios, linkTo(methodOn(UserController.class).findAll()).withSelfRel())
			  );
	}
	

	@PostMapping("/usuarios/guardar")
	ResponseEntity<?> create(@RequestBody User newUser) {
		
		if(userService.hasBadValues(newUser)) {
			return ResponseEntity.badRequest().body("Faltan datos del usuario");
		}
		
		return userService.findById(newUser.getCedula_usuario())
				.map( user -> {
					return ResponseEntity.ok(userAssembler.toModel(user));
					})
				.orElseGet( () -> {
					EntityModel<User> entityModel = userAssembler.toModel(userService.save(newUser));
					return ResponseEntity
							.created(
									entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
							.body(entityModel);
				});
	}
	

	@GetMapping("/usuarios/{id}")
	ResponseEntity<?> findOne(@PathVariable Long id) {
		
		if (id <0) {
			return ResponseEntity.badRequest().body("Valor en campo cédula no es valido");
		}
		
	  return userService.findById(id)
			 .map(userAssembler::toModel)
			 .map(ResponseEntity::ok)
			 .orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/usuarios/actualizar")
	  ResponseEntity<?> replace(@RequestBody User newUser) {
		
		if(userService.hasBadValues(newUser)) {
			return ResponseEntity.badRequest().body("Datos faltantes");
		}

		User updatedUser = userService.findById(newUser.getCedula_usuario())
				.map(user -> {
					user.setCedula_usuario(newUser.getCedula_usuario());
					user.setEmail_usuario(newUser.getEmail_usuario());
					user.setNombre_usuario(newUser.getNombre_usuario());
					user.setPassword(newUser.getPassword());
					user.setUsuario(newUser.getUsuario());
					return userService.save(user);
				})
				.orElseGet( () -> {
					return userService.save(newUser);
				});
		
		EntityModel<User> entityModel = userAssembler.toModel(updatedUser);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);

	    }


	@DeleteMapping("/usuarios/eliminar/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		if (userService.findById(id).isPresent()) {
			userService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}	
	}
	


}
