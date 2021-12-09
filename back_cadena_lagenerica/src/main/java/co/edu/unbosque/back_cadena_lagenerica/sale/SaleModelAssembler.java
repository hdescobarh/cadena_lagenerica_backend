package co.edu.unbosque.back_cadena_lagenerica.sale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SaleModelAssembler implements RepresentationModelAssembler<Sale, EntityModel<Sale>>{

	@Override
	  public EntityModel<Sale> toModel(Sale sale) {

	    return EntityModel.of(sale,
	        linkTo(methodOn(SaleController.class).findOne(sale.getCodigo_venta())).withSelfRel(),
	        linkTo(methodOn(SaleController.class).findAll()).withRel("ventas"));
	  }
}