package co.edu.unbosque.back_cadena_lagenerica.user;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;

@Entity
@Table(name = "usuarios",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "usuario"),
		@UniqueConstraint(columnNames = "email_usuario") 
	})
public class User {

	@Id
	private Long cedula_usuario;
	
	private String email_usuario;
	
	private String nombre_usuario;
	
	private String password;
	
	private String usuario;
	
	private String token;

	protected User() {

	}

	public User(Long cedula_usuario, String email_usuario, String nombre_usuario, String password, String usuario) {
		this.cedula_usuario = cedula_usuario;
		this.email_usuario = email_usuario;
		this.nombre_usuario = nombre_usuario;
		this.password = password;
		this.usuario = usuario;
	}

	public Long getCedula_usuario() {
		return cedula_usuario;
	}

	public void setCedula_usuario(Long cedula_usuario) {
		this.cedula_usuario = cedula_usuario;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	  public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof User))
	      return false;
	    User user = (User) o;
	    return Objects.equals(this.cedula_usuario, user.getCedula_usuario())
	    		&& Objects.equals(this.email_usuario, user.getEmail_usuario())
	    		&& Objects.equals(this.nombre_usuario, user.getNombre_usuario())
	    		&& Objects.equals(this.password, user.getPassword())
	    		&& Objects.equals(this.usuario, user.getUsuario());
	  }


	  @Override
	  public int hashCode() {
	    return Objects.hash(this.cedula_usuario, this.email_usuario,
	    		this.nombre_usuario, this.password, this.usuario);
	  }

	  @Override
	  public String toString() {
	    return "User{"
	    		+ "cedula_usuario=" + this.cedula_usuario
	    		+ ", email_usuario='" + this.email_usuario + '\'' 
	    		+ ", nombre_usuario='" + this.nombre_usuario + '\''
	    		+ ", password='" + this.password + '\''
	    		+ ", usuario='" + this.usuario + '\''
	    		+ '}';
	  }

}
