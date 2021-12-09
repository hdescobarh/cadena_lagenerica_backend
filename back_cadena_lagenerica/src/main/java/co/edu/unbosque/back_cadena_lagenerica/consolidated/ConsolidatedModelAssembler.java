package co.edu.unbosque.back_cadena_lagenerica.consolidated;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ConsolidatedModelAssembler
		implements RepresentationModelAssembler<Consolidated, EntityModel<Consolidated>> {

	@Override
	public EntityModel<Consolidated> toModel(Consolidated consolidated) {

		return EntityModel.of(consolidated,
				linkTo(methodOn(ConsolidatedController.class).findOne(consolidated.getId())).withSelfRel(),
				linkTo(methodOn(ConsolidatedController.class).findAll()).withRel("consolidado"));
	}
}
