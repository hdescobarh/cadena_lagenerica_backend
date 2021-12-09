package co.edu.unbosque.back_cadena_lagenerica.product;

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

import co.edu.unbosque.back_cadena_lagenerica.supplier.SupplierService;

@RestController
public class ProductController {
	
	private final ProductService productService;
	
	private final ProductModelAssembler productAssembler;
	
	private final SupplierService supplierService;
	

	public ProductController(ProductService productService, ProductModelAssembler productAssembler,
		SupplierService supplierService) {
	this.productService = productService;
	this.productAssembler = productAssembler;
	this.supplierService = supplierService;
}
	
	@GetMapping("/productos/listar")
	
	ResponseEntity<CollectionModel<EntityModel<Product>>> findAll() {
	  List<EntityModel<Product>> productos = productService.findAll().stream()
	      .map(productAssembler::toModel)
	      .collect(Collectors.toList());
	  return ResponseEntity.ok(
			  CollectionModel.of(productos, linkTo(methodOn(ProductController.class).findAll()).withSelfRel())
			  );
	}
	

	@PostMapping("/productos/guardar")
	ResponseEntity<?> create(@RequestBody Product newProduct) {
		
		if(productService.hasBadValues(newProduct)) {
			return ResponseEntity.badRequest().body("Faltan datos del producto");
		}
		
		if(supplierService.findById(newProduct.getProveedor().getNitproveedor()).isEmpty()){
			return ResponseEntity.badRequest().body("No existe el proveedor");	
		}

		return productService.findById(newProduct.getCodigo_producto())
				.map( product -> {

					return ResponseEntity.ok(productAssembler.toModel(product));
				})
				.orElseGet( () -> {
					EntityModel<Product> entityModel = productAssembler.toModel(productService.save(newProduct));
					return ResponseEntity
							.created(
									entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
							.body(entityModel);
				});
	}
	

	@GetMapping("/productos/{id}")
	ResponseEntity<EntityModel<Product>> findOne(@PathVariable Long id) {	
	  return productService.findById(id)
			 .map(productAssembler::toModel)
			 .map(ResponseEntity::ok)
			 .orElse(ResponseEntity.notFound().build());
	}


	@PutMapping("/productos/actualizar")
	  ResponseEntity<?> replace(@RequestBody Product newProduct) {
		
		if(productService.hasBadValues(newProduct)) {
			return ResponseEntity.badRequest().body("Datos faltantes");
		}
		
		if(supplierService.findById(newProduct.getProveedor().getNitproveedor()).isEmpty()){
			return ResponseEntity.badRequest().body("No existe el proveedor");	
		}

		Product updatedProduct = productService.findById(newProduct.getCodigo_producto())
				.map(product -> {
					product.setCodigo_producto(newProduct.getCodigo_producto());
					product.setIvacompra(newProduct.getIvacompra());
					product.setNombre_producto(newProduct.getNombre_producto());
					product.setPrecio_compra(newProduct.getPrecio_compra());
					product.setPrecio_venta(newProduct.getPrecio_venta());
					product.setProveedor(newProduct.getProveedor());
					return productService.save(product);
				})
				.orElseGet( () -> {
					return productService.save(newProduct);
				});
		
		EntityModel<Product> entityModel = productAssembler.toModel(updatedProduct);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);

	    }


	@DeleteMapping("/productos/eliminar/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		if (productService.findById(id).isPresent()) {
			productService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}	
	}

}
