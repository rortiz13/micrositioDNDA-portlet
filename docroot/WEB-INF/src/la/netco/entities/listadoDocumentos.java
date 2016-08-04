package la.netco.entities;

public class listadoDocumentos {
	private String nombre;
	private String tipodocumento; 
	private String categoria;
	private String fecha;
	private String palabras;
	private String idCategoria;
	private String ubicacion;
	private String ruta;
	
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPalabras() {
		return palabras;
	}
	public void setPalabras(String palabras) {
		this.palabras = palabras;
	}
	
	public listadoDocumentos(String nombre, String tipodocumento, String categoria, String fecha, String palabras, String idCategoria, String ubicacion, String ruta ){
		this.nombre=nombre;
		this.tipodocumento=tipodocumento.trim();
		this.categoria=categoria;
		this.fecha=fecha;
		this.palabras=palabras;
		this.idCategoria=idCategoria;
		this.ubicacion=ubicacion;
		this.ruta=ruta;
		
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}
