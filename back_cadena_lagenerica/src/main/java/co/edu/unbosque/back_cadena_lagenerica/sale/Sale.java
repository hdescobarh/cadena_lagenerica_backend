package co.edu.unbosque.back_cadena_lagenerica.sale;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.edu.unbosque.back_cadena_lagenerica.customer.Customer;
import co.edu.unbosque.back_cadena_lagenerica.user.User;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "ventas")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long codigo_venta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_cliente")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Customer cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_usuario")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	User usuario;
	
	Double ivaventa;
	
	Double total_venta;
	
	Double valor_venta;
	
	protected Sale() {
		
	}

	public Sale(Long codigo_venta, Customer cliente, User usuario, Double ivaventa, Double total_venta,
			Double valor_venta) {
		this.codigo_venta = codigo_venta;
		this.cliente = cliente;
		this.usuario = usuario;
		this.ivaventa = ivaventa;
		this.total_venta = total_venta;
		this.valor_venta = valor_venta;
	}

	public Long getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(Long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}

	public Customer getCliente() {
		return cliente;
	}

	public void setCliente(Customer cliente) {
		this.cliente = cliente;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Double getIvaventa() {
		return ivaventa;
	}

	public void setIvaventa(Double ivaventa) {
		this.ivaventa = ivaventa;
	}

	public Double getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(Double total_venta) {
		this.total_venta = total_venta;
	}

	public Double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(Double valor_venta) {
		this.valor_venta = valor_venta;
	}
	
	
}
