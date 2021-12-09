package co.edu.unbosque.back_cadena_lagenerica.product;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);;
	}

	@Override
	public boolean existsById(Long id) {
		return productRepository.existsById(id);
	}

	@Override
	public boolean hasBadValues(Product product) {
		String emptyString = "";
		Long badLongThreshold = -1L;
				
		boolean check_incorrect = (product.getCodigo_producto() < badLongThreshold) ||
				(product.getIvacompra() < badLongThreshold) ||
				(product.getPrecio_compra() < badLongThreshold) ||
				(product.getPrecio_venta() < badLongThreshold) ||
				Objects.equals(product.getNombre_producto(), emptyString);
		
		boolean check_nulls = Objects.isNull(product.getCodigo_producto()) ||
				Objects.isNull(product.getIvacompra()) ||
				Objects.isNull(product.getNombre_producto()) ||
				Objects.isNull(product.getPrecio_compra()) ||
				Objects.isNull(product.getProveedor()) ||
				Objects.isNull(product.getPrecio_venta());
		
		return check_incorrect || check_nulls;
	}

}
