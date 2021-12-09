package co.edu.unbosque.back_cadena_lagenerica.consolidated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ConsolidatedServiceImpl implements ConsolidatedService {

	private final ConsolidatedRepository consolidatedRepository;

	public ConsolidatedServiceImpl(ConsolidatedRepository consolidatedRepository) {
		this.consolidatedRepository = consolidatedRepository;
	}

	@Override
	public List<Consolidated> findAll() {
		return consolidatedRepository.findAll();
	}

	@Override
	public Consolidated save(Consolidated consolidated) {
		Consolidated newConsolidated = new Consolidated();
		newConsolidated.setId(consolidated.getId());
		newConsolidated.setCiudad(consolidated.getCiudad());
		newConsolidated.setTotal_ventas(consolidated.getTotal_ventas());
		return consolidatedRepository.save(newConsolidated);
	}

	@Override
	public Optional<Consolidated> findById(Long id) {
		return consolidatedRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		consolidatedRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return consolidatedRepository.existsById(id);
	}

	@Override
	public boolean hasBadValues(Consolidated consolidated) {

		String emptyString = "";
		Long badLongThreshold = -1L;

		boolean check_incorrect = (consolidated.getId() < badLongThreshold)
				|| (consolidated.getTotal_ventas() < badLongThreshold)
				|| Objects.equals(consolidated.getCiudad(), emptyString);

		boolean check_nulls = Objects.isNull(consolidated.getId()) || Objects.isNull(consolidated.getCiudad())
				|| Objects.isNull(consolidated.getTotal_ventas());

		return check_incorrect || check_nulls;
	}

}
