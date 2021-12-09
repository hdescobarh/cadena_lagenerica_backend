package co.edu.unbosque.back_cadena_lagenerica.supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class SupplierModelAssembler implements RepresentationModelAssembler<Supplier, EntityModel<Supplier>>{
	
	@Override
	  public EntityModel<Supplier> toModel(Supplier supplier) {

	    return EntityModel.of(supplier,
	        linkTo(methodOn(SupplierController.class).findOne(supplier.getNitproveedor())).withSelfRel(),
	        linkTo(methodOn(SupplierController.class).findAll()).withRel("proveedores"));
	  }

}
