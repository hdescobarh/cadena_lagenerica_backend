package co.edu.unbosque.back_cadena_lagenerica.sale;

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
public class SaleController {
	
	private final SaleService saleService;

	private final SaleModelAssembler saleAssembler;
	
	
	
	public SaleController(SaleService saleService, SaleModelAssembler saleAssembler) {
		this.saleService = saleService;
		this.saleAssembler = saleAssembler;
	}


	@GetMapping("/ventas/listar")
	
	ResponseEntity<CollectionModel<EntityModel<Sale>>> findAll() {
	  List<EntityModel<Sale>> ventas = saleService.findAll().stream()
	      .map(saleAssembler::toModel)
	      .collect(Collectors.toList());
	  return ResponseEntity.ok(
			  CollectionModel.of(ventas, linkTo(methodOn(SaleController.class).findAll()).withSelfRel())
			  );
	}
	

	@PostMapping("/ventas/guardar")
	ResponseEntity<?> create(@RequestBody Sale newSale) {
		
		if(saleService.hasBadValues(newSale)) {
			return ResponseEntity.badRequest().body("Faltan datos del usuario");
		}
		EntityModel<Sale> entityModel = saleAssembler.toModel(saleService.save(newSale));
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	

	@GetMapping("/ventas/{id}")
	ResponseEntity<?> findOne(@PathVariable Long id) {
		
		if (id <0) {
			return ResponseEntity.badRequest().body("Código de venta no valido");
		}
		
	  return saleService.findById(id)
			 .map(saleAssembler::toModel)
			 .map(ResponseEntity::ok)
			 .orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/ventas/actualizar")
	  ResponseEntity<?> replace(@RequestBody Sale newSale) {
		
		if(saleService.hasBadValues(newSale)) {
			return ResponseEntity.badRequest().body("Datos faltantes");
		}

		Sale updatedSale = saleService.findById(newSale.getCodigo_venta())
				.map(sale -> {
					sale.setCliente(newSale.getCliente());
					sale.setIvaventa(newSale.getIvaventa());
					sale.setTotal_venta(newSale.getTotal_venta());
					sale.setUsuario(newSale.getUsuario());
					sale.setValor_venta(newSale.getValor_venta());
					return saleService.save(sale);
				})
				.orElseGet( () -> {
					return saleService.save(newSale);
				});
		
		EntityModel<Sale> entityModel = saleAssembler.toModel(updatedSale);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);

	    }


	@DeleteMapping("/ventas/eliminar/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		if (saleService.findById(id).isPresent()) {
			saleService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}	
	}

}

