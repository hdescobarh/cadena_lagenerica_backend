package co.edu.unbosque.back_cadena_lagenerica.sale_details;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleDetailsController {
	
private final SaleDetailsService saleDetailsService;

private final SaleDetailsModelAssembler saleDetailsAssembler;

	public SaleDetailsController(SaleDetailsService saleDetailsService, SaleDetailsModelAssembler saleDetailsAssembler) {
	this.saleDetailsService = saleDetailsService;
	this.saleDetailsAssembler = saleDetailsAssembler;
}


	@GetMapping("/detalleventas/listar")
	
	ResponseEntity<CollectionModel<EntityModel<SaleDetails>>> findAll() {
	  List<EntityModel<SaleDetails>> detalleventas = saleDetailsService.findAll().stream()
	      .map(saleDetailsAssembler::toModel)
	      .collect(Collectors.toList());
	  return ResponseEntity.ok(
			  CollectionModel.of(detalleventas, linkTo(methodOn(SaleDetailsController.class).findAll()).withSelfRel())
			  );
	}
	

	@PostMapping("/detalleventas/guardar")
	ResponseEntity<?> create(@RequestBody SaleDetails newSaleDetails) {
		
		if(saleDetailsService.hasBadValues(newSaleDetails)) {
			return ResponseEntity.badRequest().body("Faltan datos del usuario");
		}
		EntityModel<SaleDetails> entityModel = saleDetailsAssembler.toModel(saleDetailsService.save(newSaleDetails));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
		}
	

	@GetMapping("/detalleventas/{id}")
	ResponseEntity<?> findOne(@PathVariable Long id) {
		
		if (id <0) {
			return ResponseEntity.badRequest().body("Valor en campo cédula no es valido");
		}
		
	  return saleDetailsService.findById(id)
			 .map(saleDetailsAssembler::toModel)
			 .map(ResponseEntity::ok)
			 .orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/detalleventas/actualizar")
	  ResponseEntity<?> replace(@RequestBody SaleDetails newSaleDetails) {
		
		if(saleDetailsService.hasBadValues(newSaleDetails)) {
			return ResponseEntity.badRequest().body("Datos faltantes");
		}

		SaleDetails updatedSaleDetails = saleDetailsService.findById(newSaleDetails.getCodigo_detalle_venta())
				.map(saleDetails -> {
					saleDetails.setCantidad_producto(newSaleDetails.getCantidad_producto());
					saleDetails.setCodigo_detalle_venta(newSaleDetails.getCodigo_detalle_venta());
					saleDetails.setProducto(newSaleDetails.getProducto());
					saleDetails.setValor_total(newSaleDetails.getValor_total());
					saleDetails.setValor_venta(newSaleDetails.getValor_venta());
					saleDetails.setValoriva(newSaleDetails.getValoriva());
					saleDetails.setVenta(newSaleDetails.getVenta());
					return saleDetailsService.save(saleDetails);
				})
				.orElseGet( () -> {
					return saleDetailsService.save(newSaleDetails);
				});
		
		EntityModel<SaleDetails> entityModel = saleDetailsAssembler.toModel(updatedSaleDetails);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);

	    }


	@DeleteMapping("/detalleventas/eliminar/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		if (saleDetailsService.findById(id).isPresent()) {
			saleDetailsService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}	
	}

}

