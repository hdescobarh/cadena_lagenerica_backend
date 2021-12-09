package co.edu.unbosque.back_cadena_lagenerica.consolidated;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consolidado")
public class ConsolidatedController {

	private final ConsolidatedService consolidatedService;

	private final ConsolidatedModelAssembler consolidatedAssembler;

	ConsolidatedController(ConsolidatedService consolidatedService, ConsolidatedModelAssembler consolidatedAssembler) {
		this.consolidatedAssembler = consolidatedAssembler;
		this.consolidatedService = consolidatedService;
	}

	@GetMapping("/listar")
	ResponseEntity<CollectionModel<EntityModel<Consolidated>>> findAll() {
		List<EntityModel<Consolidated>> clientes = consolidatedService.findAll().stream()
				.map(consolidatedAssembler::toModel).collect(Collectors.toList());
		return ResponseEntity.ok(
				CollectionModel.of(clientes, linkTo(methodOn(ConsolidatedController.class).findAll()).withSelfRel()));
	}

	@PostMapping("/guardar")
	ResponseEntity<?> create(@RequestBody Consolidated newConsolidated) {

		if (consolidatedService.hasBadValues(newConsolidated)) {
			return ResponseEntity.badRequest().body("Faltan datos");
		}

		return consolidatedService.findById(newConsolidated.getId()).map(consolidated -> {
			return ResponseEntity.ok(consolidatedAssembler.toModel(consolidated));
		}).orElseGet(() -> {
			EntityModel<Consolidated> entityModel = consolidatedAssembler
					.toModel(consolidatedService.save(newConsolidated));
			return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
					.body(entityModel);
		});
	}

	@GetMapping("/{id}")
	ResponseEntity<EntityModel<Consolidated>> findOne(@PathVariable Long id) {
		return consolidatedService.findById(id).map(consolidatedAssembler::toModel).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/actualizar")
	ResponseEntity<?> replace(@RequestBody Consolidated newConsolidated) {

		if (consolidatedService.hasBadValues(newConsolidated)) {
			return ResponseEntity.badRequest().body("Datos faltantes");
		}

		Consolidated updatedCustomer = consolidatedService.findById(newConsolidated.getId()).map(consolidated -> {
			consolidated.setId(newConsolidated.getId());
			consolidated.setCiudad(newConsolidated.getCiudad());
			consolidated.setTotal_ventas(newConsolidated.getTotal_ventas());
			return consolidatedService.save(consolidated);
		}).orElseGet(() -> {
			return consolidatedService.save(newConsolidated);
		});

		EntityModel<Consolidated> entityModel = consolidatedAssembler.toModel(updatedCustomer);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

	}

	@DeleteMapping("/eliminar/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		if (consolidatedService.findById(id).isPresent()) {
			consolidatedService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
