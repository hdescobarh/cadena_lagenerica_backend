package co.edu.unbosque.back_cadena_lagenerica.sale_details;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class SaleDetailsServiceImpl implements SaleDetailsService {
	
	private final SaleDetailsRepository saleDetailsRepository;

	public SaleDetailsServiceImpl(SaleDetailsRepository saleDetailsRepository) {
		this.saleDetailsRepository = saleDetailsRepository;
	}

	@Override
	public List<SaleDetails> findAll() {
		return saleDetailsRepository.findAll();
	}

	@Override
	public SaleDetails save(SaleDetails saleDetails) {
		return saleDetailsRepository.save(saleDetails);
	}

	@Override
	public Optional<SaleDetails> findById(Long id) {
		return saleDetailsRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		saleDetailsRepository.deleteById(id);	
	}
	
	@Override
	public boolean existsById(Long id) {
		return saleDetailsRepository.existsById(id);
	}
	
	@Override
	public boolean hasBadValues(SaleDetails saleDetails) {
		
		Long badLongThreshold = -1L;
				
		boolean check_incorrect = (saleDetails.getCantidad_producto() <= badLongThreshold) ||
				(saleDetails.getValor_total() <= badLongThreshold) ||
				(saleDetails.getValor_venta()<= badLongThreshold)||
				(saleDetails.getValoriva() <= badLongThreshold);
		
		boolean check_nulls = Objects.isNull(saleDetails.getCantidad_producto()) ||
				Objects.isNull(saleDetails.getProducto()) ||
				Objects.isNull(saleDetails.getValor_total()) ||
				Objects.isNull(saleDetails.getValor_venta()) ||
				Objects.isNull(saleDetails.getValoriva()) ||
				Objects.isNull(saleDetails.getVenta());	
		
		return check_incorrect || check_nulls;
	}

}
