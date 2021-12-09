package co.edu.unbosque.back_cadena_lagenerica.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	  @Override
	  public EntityModel<User> toModel(User user) {

	    return EntityModel.of(user,
	        linkTo(methodOn(UserController.class).findOne(user.getCedula_usuario())).withSelfRel(),
	        linkTo(methodOn(UserController.class).findAll()).withRel("usuarios"));
	  }
	}
