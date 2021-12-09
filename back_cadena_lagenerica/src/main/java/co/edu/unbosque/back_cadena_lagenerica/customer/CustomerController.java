package co.edu.unbosque.back_cadena_lagenerica.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	
	private final CustomerService customerService;
	
	private final CustomerModelAssembler customerAssembler;
	
	CustomerController(CustomerService customerService, CustomerModelAssembler customerAssembler) {
		this.customerService = customerService;
		this.customerAssembler = customerAssembler;
	}
	

	@GetMapping("/clientes/listar")
	
	ResponseEntity<CollectionModel<EntityModel<Customer>>> findAll() {
	  List<EntityModel<Customer>> clientes = customerService.findAll().stream()
	      .map(customerAssembler::toModel)
	      .collect(Collectors.toList());
	  return ResponseEntity.ok(
			  CollectionModel.of(clientes, linkTo(methodOn(CustomerController.class).findAll()).withSelfRel())
			  );
	}
	

	@PostMapping("/clientes/guardar")
	ResponseEntity<?> create(@RequestBody Customer newCustomer) {
		
		if(customerService.hasBadValues(newCustomer)) {
			return ResponseEntity.badRequest().body("Faltan datos del usuario");
		}
		
		return customerService.findById(newCustomer.getCedula_cliente())
				.map( customer -> {
					return ResponseEntity.ok(customerAssembler.toModel(customer));
					})
				.orElseGet( () -> {
					EntityModel<Customer> entityModel = customerAssembler.toModel(customerService.save(newCustomer));
					return ResponseEntity
							.created(
									entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
							.body(entityModel);
				});
	}
	

	@GetMapping("/clientes/{id}")
	ResponseEntity<EntityModel<Customer>> findOne(@PathVariable Long id) {	
	  return customerService.findById(id)
			 .map(customerAssembler::toModel)
			 .map(ResponseEntity::ok)
			 .orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/clientes/actualizar")
	  ResponseEntity<?> replace(@RequestBody Customer newCustomer) {
		
		if(customerService.hasBadValues(newCustomer)) {
			return ResponseEntity.badRequest().body("Datos faltantes");
		}

		Customer updatedCustomer = customerService.findById(newCustomer.getCedula_cliente())
				.map(customer -> {
					customer.setCedula_cliente(newCustomer.getCedula_cliente());
					customer.setEmail_cliente(newCustomer.getEmail_cliente());
					customer.setNombre_cliente(newCustomer.getNombre_cliente());
					customer.setDireccion_cliente(newCustomer.getDireccion_cliente());
					customer.setTelefono_cliente(newCustomer.getTelefono_cliente());
					return customerService.save(customer);
				})
				.orElseGet( () -> {
					return customerService.save(newCustomer);
				});
		
		EntityModel<Customer> entityModel = customerAssembler.toModel(updatedCustomer);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);

	    }


	@DeleteMapping("/clientes/eliminar/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		if (customerService.findById(id).isPresent()) {
			customerService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}	
	}
}
