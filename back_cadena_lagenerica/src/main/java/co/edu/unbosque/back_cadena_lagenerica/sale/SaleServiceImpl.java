package co.edu.unbosque.back_cadena_lagenerica.sale;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class SaleServiceImpl implements SaleService{
	
	private final SaleRepository saleRepository;
	
	public SaleServiceImpl(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
		}
	
	@Override
	public List<Sale> findAll() {
		return saleRepository.findAll();
	}

	@Override
	public Sale save(Sale sale) {
		return saleRepository.save(sale);
	}

	@Override
	public Optional<Sale> findById(Long id) {
		return saleRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		saleRepository.deleteById(id);	
	}
	
	@Override
	public boolean existsById(Long id) {
		return saleRepository.existsById(id);
	}
	
	@Override
	public boolean hasBadValues(Sale sale) {
		
		Long badLongThreshold = -1L;
				
		boolean check_incorrect = (sale.getCliente().getCedula_cliente() < badLongThreshold) ||
				(sale.getIvaventa() < badLongThreshold) ||
				(sale.getValor_venta() < badLongThreshold) ||
				(sale.getTotal_venta() < badLongThreshold) ||
				(sale.getUsuario().getCedula_usuario() < badLongThreshold);
		
		boolean check_nulls = Objects.isNull(sale.getCliente()) ||
				Objects.isNull(sale.getIvaventa()) ||
				Objects.isNull(sale.getTotal_venta()) ||
				Objects.isNull(sale.getUsuario()) ||
				Objects.isNull(sale.getValor_venta());	
		
		return check_incorrect || check_nulls;
	}

}
