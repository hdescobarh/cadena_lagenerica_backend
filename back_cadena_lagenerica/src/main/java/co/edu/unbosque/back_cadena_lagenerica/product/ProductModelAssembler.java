package co.edu.unbosque.back_cadena_lagenerica.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>>{

	@Override
	  public EntityModel<Product> toModel(Product product) {

	    return EntityModel.of(product,
	        linkTo(methodOn(ProductController.class).findOne(product.getCodigo_producto())).withSelfRel(),
	        linkTo(methodOn(ProductController.class).findAll()).withRel("productos"));
	  }
}
