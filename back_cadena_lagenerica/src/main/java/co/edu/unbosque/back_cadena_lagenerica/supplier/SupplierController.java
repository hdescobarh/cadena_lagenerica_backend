package co.edu.unbosque.back_cadena_lagenerica.supplier;

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
public class SupplierController {
	
private final SupplierService supplierService;
	
	private final SupplierModelAssembler supplierAssembler;
	
	SupplierController(SupplierService supplierService, SupplierModelAssembler supplierAssembler) {
		this.supplierService = supplierService;
		this.supplierAssembler = supplierAssembler;
	}
	

	@GetMapping("/proveedores/listar")
	
	ResponseEntity<CollectionModel<EntityModel<Supplier>>> findAll() {
	  List<EntityModel<Supplier>> proveedores = supplierService.findAll().stream()
	      .map(supplierAssembler::toModel)
	      .collect(Collectors.toList());
	  return ResponseEntity.ok(
			  CollectionModel.of(proveedores, linkTo(methodOn(SupplierController.class).findAll()).withSelfRel())
			  );
	}
	

	@PostMapping("/proveedores/guardar")
	ResponseEntity<?> create(@RequestBody Supplier newSupplier) {
		
		if(supplierService.hasBadValues(newSupplier)) {
			return ResponseEntity.badRequest().body("Faltan datos del nitproveedor");
		}
		
		return supplierService.findById(newSupplier.getNitproveedor())
				.map( supplier -> {
					return ResponseEntity.ok(supplierAssembler.toModel(supplier));
					})
				.orElseGet( () -> {
					EntityModel<Supplier> entityModel = supplierAssembler.toModel(supplierService.save(newSupplier));
					return ResponseEntity
							.created(
									entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
							.body(entityModel);
				});
	}
	

	@GetMapping("/proveedores/{id}")
	ResponseEntity<EntityModel<Supplier>> findOne(@PathVariable Long id) {	
	  return supplierService.findById(id)
			 .map(supplierAssembler::toModel)
			 .map(ResponseEntity::ok)
			 .orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/proveedores/actualizar")
	  ResponseEntity<?> replace(@RequestBody Supplier newSupplier) {
		
		if(supplierService.hasBadValues(newSupplier)) {
			return ResponseEntity.badRequest().body("Datos faltantes");
		}

		Supplier updatedSupplier = supplierService.findById(newSupplier.getNitproveedor())
				.map(supplier -> {
					supplier.setNitproveedor(newSupplier.getNitproveedor());
					supplier.setCiudad_proveedor(newSupplier.getCiudad_proveedor());
					supplier.setDireccion_proveedor(newSupplier.getDireccion_proveedor());
					supplier.setNombre_proveedor(newSupplier.getNombre_proveedor());
					supplier.setTelefono_proveedor(newSupplier.getTelefono_proveedor());
					return supplierService.save(supplier);
				})
				.orElseGet( () -> {
					return supplierService.save(newSupplier);
				});
		
		EntityModel<Supplier> entityModel = supplierAssembler.toModel(updatedSupplier);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);

	    }


	@DeleteMapping("/proveedores/eliminar/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		if (supplierService.findById(id).isPresent()) {
			supplierService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}	
	}

}
