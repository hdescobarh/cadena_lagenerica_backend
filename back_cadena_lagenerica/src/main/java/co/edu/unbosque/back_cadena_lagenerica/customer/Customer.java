package co.edu.unbosque.back_cadena_lagenerica.customer;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "clientes")
public class Customer {
	
	@Id
	private Long cedula_cliente;
	private String direccion_cliente;
	private String email_cliente;
	private String nombre_cliente;
	private String telefono_cliente;
    
    protected Customer() {
    	
    }
    
    
	public Customer(Long cedula_cliente, String direccion_cliente, String email_cliente, String nombre_cliente,
			String telefono_cliente) {
		super();
		this.cedula_cliente = cedula_cliente;
		this.direccion_cliente = direccion_cliente;
		this.email_cliente = email_cliente;
		this.nombre_cliente = nombre_cliente;
		this.telefono_cliente = telefono_cliente;
	}


	public Long getCedula_cliente() {
		return cedula_cliente;
	}
	public void setCedula_cliente(Long cedula_cliente) {
		this.cedula_cliente = cedula_cliente;
	}
	public String getDireccion_cliente() {
		return direccion_cliente;
	}
	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}
	public String getEmail_cliente() {
		return email_cliente;
	}
	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getTelefono_cliente() {
		return telefono_cliente;
	}
	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}
    
	 @Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Customer))
	      return false;
	    Customer cliente = (Customer) o;
	    return Objects.equals(this.cedula_cliente, cliente.getCedula_cliente() )
	    		&& Objects.equals(this.email_cliente, cliente.getEmail_cliente())
	    		&& Objects.equals(this.nombre_cliente, cliente.getNombre_cliente())
	    		&& Objects.equals(this.telefono_cliente, cliente.getTelefono_cliente())
	    		&& Objects.equals(this.direccion_cliente, cliente.getDireccion_cliente());
	  }


	  @Override
	  public int hashCode() {
	    return Objects.hash(this.cedula_cliente, this.email_cliente,
	    		this.nombre_cliente, this.telefono_cliente, this.direccion_cliente);
	  }

	  @Override
	  public String toString() {
	    return "Customer{"
	    		+ "cedula_usuario=" + this.cedula_cliente
	    		+ ", email_usuario='" + this.email_cliente + '\'' 
	    		+ ", nombre_usuario='" + this.nombre_cliente + '\''
	    		+ ", telefono_cliente='" + this.telefono_cliente + '\''
	    		+ ", telefono_cliente='" + this.direccion_cliente + '\''
	    		+ '}';
	  }
    
}
