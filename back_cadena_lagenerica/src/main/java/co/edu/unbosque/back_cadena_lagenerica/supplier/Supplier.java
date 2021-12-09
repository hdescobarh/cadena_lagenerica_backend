package co.edu.unbosque.back_cadena_lagenerica.supplier;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name = "proveedores")
public class Supplier {
	
	@Id
	private Long nitproveedor;
	private String ciudad_proveedor;
	private String direccion_proveedor;
	private String nombre_proveedor;
	private String telefono_proveedor;
	
	protected Supplier() {
		
	}

	public Supplier(Long nitproveedor, String ciudad_proveedor, String direccion_proveedor, String nombre_proveedor,
			String telefono_proveedor) {
		this.nitproveedor = nitproveedor;
		this.ciudad_proveedor = ciudad_proveedor;
		this.direccion_proveedor = direccion_proveedor;
		this.nombre_proveedor = nombre_proveedor;
		this.telefono_proveedor = telefono_proveedor;
	}

	public Long getNitproveedor() {
		return nitproveedor;
	}

	public void setNitproveedor(Long nitproveedor) {
		this.nitproveedor = nitproveedor;
	}

	public String getCiudad_proveedor() {
		return ciudad_proveedor;
	}

	public void setCiudad_proveedor(String ciudad_proveedor) {
		this.ciudad_proveedor = ciudad_proveedor;
	}

	public String getDireccion_proveedor() {
		return direccion_proveedor;
	}

	public void setDireccion_proveedor(String direccion_proveedor) {
		this.direccion_proveedor = direccion_proveedor;
	}

	public String getNombre_proveedor() {
		return nombre_proveedor;
	}

	public void setNombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}

	public String getTelefono_proveedor() {
		return telefono_proveedor;
	}

	public void setTelefono_proveedor(String telefono_proveedor) {
		this.telefono_proveedor = telefono_proveedor;
	}
	
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Long))
	      return false;
	    Supplier supplier = (Supplier) o;
	    return Objects.equals(this.nitproveedor, supplier.getNitproveedor())
	    		&& Objects.equals(this.ciudad_proveedor, supplier.getCiudad_proveedor())
	    		&& Objects.equals(this.direccion_proveedor, supplier.getDireccion_proveedor())
	    		&& Objects.equals(this.nombre_proveedor, supplier.getNombre_proveedor())
	    		&& Objects.equals(this.telefono_proveedor, supplier.getTelefono_proveedor());
	  }


	  @Override
	  public int hashCode() {
	    return Objects.hash(this.nitproveedor, this.ciudad_proveedor,
	    		this.direccion_proveedor, this.nombre_proveedor, this.telefono_proveedor);
	  }

	  @Override
	  public String toString() {
	    return "Supplier{"
	    		+ "nitproveedor=" + this.nitproveedor
	    		+ ", ciudad_proveedor='" + this.ciudad_proveedor + '\'' 
	    		+ ", direccion_proveedor='" + this.direccion_proveedor + '\''
	    		+ ", nombre_proveedor='" + this.nombre_proveedor + '\''
	    		+ ", telefono_proveedor='" + this.telefono_proveedor + '\''
	    		+ '}';
	  }
	
	

}
