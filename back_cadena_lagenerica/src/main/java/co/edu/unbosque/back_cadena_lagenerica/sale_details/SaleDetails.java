package co.edu.unbosque.back_cadena_lagenerica.sale_details;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.edu.unbosque.back_cadena_lagenerica.product.Product;
import co.edu.unbosque.back_cadena_lagenerica.sale.Sale;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name= "detalle_ventas")
public class SaleDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long codigo_detalle_venta;
	
	Integer cantidad_producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_producto")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Product producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_venta")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Sale venta;
	
	Double valor_total;
	
	Double valor_venta;
	
	Double valoriva;
	
	protected SaleDetails() {
		
	}

	public SaleDetails(Long codigo_detalle_venta, Integer cantidad_producto, Product producto, Sale venta,
			Double valor_total, Double valor_venta, Double valoriva) {
		this.codigo_detalle_venta = codigo_detalle_venta;
		this.cantidad_producto = cantidad_producto;
		this.producto = producto;
		this.venta = venta;
		this.valor_total = valor_total;
		this.valor_venta = valor_venta;
		this.valoriva = valoriva;
	}

	public Long getCodigo_detalle_venta() {
		return codigo_detalle_venta;
	}

	public void setCodigo_detalle_venta(Long codigo_detalle_venta) {
		this.codigo_detalle_venta = codigo_detalle_venta;
	}

	public Integer getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto(Integer cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}

	public Product getProducto() {
		return producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}

	public Sale getVenta() {
		return venta;
	}

	public void setVenta(Sale venta) {
		this.venta = venta;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(Double valor_venta) {
		this.valor_venta = valor_venta;
	}

	public Double getValoriva() {
		return valoriva;
	}

	public void setValoriva(Double valoriva) {
		this.valoriva = valoriva;
	}
	

}
