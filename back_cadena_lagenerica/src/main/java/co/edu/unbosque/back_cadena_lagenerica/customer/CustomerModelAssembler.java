package co.edu.unbosque.back_cadena_lagenerica.customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>>{

	@Override
	  public EntityModel<Customer> toModel(Customer customer) {

	    return EntityModel.of(customer,
	        linkTo(methodOn(CustomerController.class).findOne(customer.getCedula_cliente())).withSelfRel(),
	        linkTo(methodOn(CustomerController.class).findAll()).withRel("clientes"));
	  }
}
