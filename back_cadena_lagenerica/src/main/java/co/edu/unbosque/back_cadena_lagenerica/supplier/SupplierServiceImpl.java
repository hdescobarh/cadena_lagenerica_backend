package co.edu.unbosque.back_cadena_lagenerica.supplier;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	private final SupplierRepository supplierRepository;
	
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier save(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		return supplierRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		supplierRepository.deleteById(id);	
	}
	
	@Override
	public boolean existsById(Long id) {
		return supplierRepository.existsById(id);
	}
	
	@Override
	public boolean hasBadValues(Supplier supplier) {
		
		String emptyString = "";
		Long badLongThreshold = -1L;
				
		boolean check_incorrect = (supplier.getNitproveedor() < badLongThreshold) ||
				Objects.equals(supplier.getCiudad_proveedor(), emptyString) ||
				Objects.equals(supplier.getDireccion_proveedor(), emptyString) ||
				Objects.equals(supplier.getNombre_proveedor(), emptyString) ||
				Objects.equals(supplier.getTelefono_proveedor(), emptyString);
		
		boolean check_nulls = Objects.isNull(supplier.getNitproveedor()) ||
				Objects.isNull(supplier.getCiudad_proveedor()) ||
				Objects.isNull(supplier.getDireccion_proveedor()) ||
				Objects.isNull(supplier.getNombre_proveedor()) ||
				Objects.isNull(supplier.getTelefono_proveedor());	
		
		return check_incorrect || check_nulls;
	}
}
