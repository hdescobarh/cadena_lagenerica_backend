package co.edu.unbosque.back_cadena_lagenerica.consolidated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consolidado")
public class Consolidated {

	@Id
	private Long id;
	private String ciudad;
	private Double total_ventas;

	protected Consolidated() {

	}

	public Consolidated(Long id, String ciudad, Double total_ventas) {
		this.id = id;
		this.ciudad = ciudad;
		this.total_ventas = total_ventas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Double getTotal_ventas() {
		return total_ventas;
	}

	public void setTotal_ventas(Double total_ventas) {
		this.total_ventas = total_ventas;
	}

}
