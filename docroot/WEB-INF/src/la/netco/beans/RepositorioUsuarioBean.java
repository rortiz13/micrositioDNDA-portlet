package la.netco.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletResponse;

import la.netco.controller.ControllerMicrositio;
import la.netco.entities.listadoDocumentos;
import la.netco.entities.selecItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import com.liferay.portal.util.PortalUtil;


@ManagedBean(name="BeanRepositorioUser")
@ViewScoped
public class RepositorioUsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//Variables de la clase
		private String tipoarchivo;
		private String categoria;
		private String ubicacion;
		private String palabras;
		private String fecha;
		private String nombre;
		private List<selecItem> categorias;
		private List<selecItem> tipoArchivos;
		private Date dsd;
		private Date hst;
		private List<listadoDocumentos> listado;
		static FileUploadEvent archivo;		
		static listadoDocumentos rep;
		


		public listadoDocumentos getRep() {
			return rep;
		}


		public void setRep(listadoDocumentos rep) {
			RepositorioUsuarioBean.rep = rep;
		}


		public List<listadoDocumentos> getListado() {
			return listado;
		}


		public void setListado(List<listadoDocumentos> listado) {
			this.listado = listado;
		}


		public Date getDsd() {
			return dsd;
		}


		public void setDsd(Date dsd) {
			this.dsd = dsd;
		}


		public Date getHst() {
			return hst;
		}


		public void setHst(Date hst) {
			this.hst = hst;
		}


		//Constructor
		public RepositorioUsuarioBean() throws SQLException{
			SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");	
			categorias = new ArrayList<selecItem>();
			ResultSet rs1 = ControllerMicrositio.selecCategoria();
			selecCateg(rs1);
			tipoArchivos = new ArrayList<selecItem>();
		    ResultSet rs = ControllerMicrositio.selecDocumento();
		    selecDoc(rs);
		    Date fec;
		    fec=new Date();
		    fecha=df1.format(fec).toString();
			
		}


		public String getTipoarchivo() {
			return tipoarchivo;
		}


		public void setTipoarchivo(String tipoarchivo) {
			this.tipoarchivo = tipoarchivo;
		}


		public String getCategoria() {
			return categoria;
		}


		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}


		public String getUbicacion() {
			return ubicacion;
		}


		public void setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
		}


		public String getPalabras() {
			return palabras;
		}


		public void setPalabras(String palabras) {
			this.palabras = palabras;
		}


		public String getFecha() {
			return fecha;
		}


		public void setFecha(String fecha) {
			this.fecha = fecha;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		


		public List<selecItem> getCategorias() {
			return categorias;
		}


		public void setCategorias(List<selecItem> categorias) {
			this.categorias = categorias;
		}


		public List<selecItem> getTipoArchivos() {
			return tipoArchivos;
		}
		
		public void setTipoArchivos(List<selecItem> tipoArchivos) {
			this.tipoArchivos = tipoArchivos;
		}
	
		private void selecCateg(ResultSet result)
	    {
	        try
	        {
	            if(result != null)
	            {
	                for(; result.next(); categorias.add(new selecItem(result.getString(1), result.getString(2)))) { }
	            } else
	            {
	                System.out.println("no ahy coincidencias de busqueda");
	            }
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
		private void selecDoc(ResultSet result)
	    {
	        try
	        {
	            if(result != null)
	            {
	                for(; result.next(); tipoArchivos.add(new selecItem(result.getString(1), result.getString(2)))) { }
	            } else
	            {
	                System.out.println("no ahy coincidencias de busqueda");
	            }
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
		
		
		
		public String cargar() throws SQLException{
			SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
			String page=null;
			String filtros="";
			if(dsd!=null){
				filtros+=" AND FECHA_PUBLICACION>'"+df1.format(dsd).toString()+"'";
			}
			if(hst!=null){
				filtros+=" AND FECHA_PUBLICACION<'"+df1.format(hst).toString()+"'";
			}
			if(categoria!=null && categoria!=""){
				filtros+=" AND ID_CATEGORY='"+categoria+"'";
			}
			if(tipoarchivo!=null && tipoarchivo!=""){
				filtros+=" AND ID_TIPO='"+tipoarchivo+"'";
			}
			if(palabras!=null && palabras!=""){
				String vec[] = palabras.split(" ");
	            for(int i = 0; i < vec.length; i++)
	            {
	                filtros+=" AND contains(PALABRAS_CLAVES,'"+vec[i]+"') ";
	            }
			}
			ResultSet rs2 = ControllerMicrositio.listado(filtros);
			llenarListado(rs2);
			return page;
		}
		public void llenarListado(ResultSet result)
		        throws SQLException
		    {
		        listado = new ArrayList<listadoDocumentos>();
		        if(result != null)
		        {
		            while(result.next()) 
		            {
		            	
		            	listado.add(new listadoDocumentos(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5),result.getString(6),result.getString(7),result.getString(8)));
		            }
		        }
		    }
		
		 public StreamedContent getFile(listadoDocumentos rec) throws Exception {  
			 SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
			 Date aux=df1.parse(rec.getFecha());
			 
			// 1. initialize the fileInputStream
	    	 File localfile = new File("Archivos/"+rec.getIdCategoria()+"/"+df2.format(aux).toString()+"/"+rec.getNombre());
	    	 FileInputStream fis = new FileInputStream(localfile);

	    	 // 2. get Liferay's ServletResponse
	    	 PortletResponse portletResponse = (PortletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    	 HttpServletResponse res = PortalUtil.getHttpServletResponse(portletResponse);
	    	 res.setHeader("Content-Disposition", "attachment; filename=\""+"DNDA"+rec.getNombre() + "\"");
	    	 res.setHeader("Content-Transfer-Encoding", "binary");
	    	 res.setContentType("application/octet-stream");
	    	 res.flushBuffer();

	    	 // 3. write the file into the outputStream
	    	 OutputStream out = res.getOutputStream();
	    	 byte[] buffer = new byte[4096];
	    	 int bytesRead;
	    	 while ((bytesRead = fis.read(buffer)) != -1) {
	    	  out.write(buffer, 0, bytesRead);
	    	  buffer = new byte[4096];
	    	 }

	    	 // 4. return null to this method
	    	 return null;		    	
			

		    } 
		 
		 public String repAudio(listadoDocumentos row){
			 rep=row;
			 
			 return "reproductor";
		 }
		
		
		
		

}



