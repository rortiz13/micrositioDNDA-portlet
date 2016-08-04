package la.netco.entities;

import java.io.Serializable;

public class selecItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public selecItem(String codigo, String nombre){
		this.codigo=codigo;
		this.nombre=nombre;
		
	}

}
