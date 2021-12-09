package co.edu.unbosque.back_cadena_lagenerica.sale_details;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SaleDetailsModelAssembler implements RepresentationModelAssembler<SaleDetails, EntityModel<SaleDetails>>{
	
	@Override
	  public EntityModel<SaleDetails> toModel(SaleDetails saleDetails) {

	    return EntityModel.of(saleDetails,
	        linkTo(methodOn(SaleDetailsController.class).findOne(saleDetails.getCodigo_detalle_venta())).withSelfRel(),
	        linkTo(methodOn(SaleDetailsController.class).findAll()).withRel("detallesVentas"));
	  }

}
