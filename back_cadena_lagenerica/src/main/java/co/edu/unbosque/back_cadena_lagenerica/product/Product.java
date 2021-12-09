package co.edu.unbosque.back_cadena_lagenerica.product;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.edu.unbosque.back_cadena_lagenerica.supplier.Supplier;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "productos")
public class Product {
	

	@Id
	Long codigo_producto;
	
	Double ivacompra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nitproveedor")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Supplier proveedor;	
	
	private String nombre_producto;
	
	private Double precio_compra;
	
	private Double precio_venta;

	protected Product() {
		
	}

	public Product(Long codigo_producto, Double ivacompra, Supplier proveedor, String nombre_producto,
			Double precio_compra, Double precio_venta) {
		this.codigo_producto = codigo_producto;
		this.ivacompra = ivacompra;
		this.proveedor = proveedor;
		this.nombre_producto = nombre_producto;
		this.precio_compra = precio_compra;
		this.precio_venta = precio_venta;
	}
	

	public Long getCodigo_producto() {
		return codigo_producto;
	}





	public void setCodigo_producto(Long codigo_producto) {
		this.codigo_producto = codigo_producto;
	}





	public Double getIvacompra() {
		return ivacompra;
	}





	public void setIvacompra(Double ivacompra) {
		this.ivacompra = ivacompra;
	}





	public Supplier getProveedor() {
		return proveedor;
	}





	public void setProveedor(Supplier proveedor) {
		this.proveedor = proveedor;
	}





	public String getNombre_producto() {
		return nombre_producto;
	}





	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}





	public Double getPrecio_compra() {
		return precio_compra;
	}





	public void setPrecio_compra(Double precio_compra) {
		this.precio_compra = precio_compra;
	}





	public Double getPrecio_venta() {
		return precio_venta;
	}





	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
	}





	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Long))
	      return false;
	    Product product = (Product) o;
	    return Objects.equals(this.codigo_producto, product.getCodigo_producto())
	    		&& Objects.equals(this.ivacompra, product.getIvacompra())
	    		&& Objects.equals(this.proveedor.getNitproveedor(), product.getProveedor().getNitproveedor())
	    		&& Objects.equals(this.nombre_producto, product.getNombre_producto())
	    		&& Objects.equals(this.precio_compra, product.getPrecio_compra())
	    		&& Objects.equals(this.precio_venta, product.getPrecio_venta());
	  }


	  @Override
	  public int hashCode() {
	    return Objects.hash(this.codigo_producto, this.ivacompra, this.proveedor.getNitproveedor(),
	    		this.nombre_producto, this.precio_compra, this.precio_venta);
	  }

	  @Override
	  public String toString() {
	    return "Product{"
	    		+ "codigo_producto=" + this.codigo_producto
	    		+ ", ivacompra='" + this.ivacompra + '\'' 
	    		+ ", proveedor='" + this.proveedor.getNitproveedor() + '\''
	    		+ ", nombre_producto='" + this.nombre_producto + '\''
	    		+ ", precio_compra='" + this.precio_compra + '\''
	    		+ ", precio_venta='" + this.precio_venta + '\''
	    		+ '}';
	  }

}
