package la.netco.admin.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
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
import org.primefaces.model.UploadedFile;

import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsf.common.FacesMessageUtil;


@ManagedBean(name="BeanRepositorioAdm")
@ViewScoped
public class RepositorioAdmBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables de la clase
	private String tipoarchivo;
	private String categoria;
	private String ubicacion;
	private String palabras;
	private String fecha;
	private String nombre;
	private String ext;	
	private UploadedFile filesUpload;
	private List<selecItem> categorias;
	private List<selecItem> tipoArchivos;
	private Date dsd;
	private Date hst;
	private static List<listadoDocumentos> listado;
	static FileUploadEvent archivo;
//	private StreamedContent file;  
	private String tipovideo;
	
	public String getTipovideo() {
		return tipovideo;
	}


	public void setTipovideo(String tipovideo) {
		this.tipovideo = tipovideo;
	}


	public String getExt() {
		return ext;
	}


	public void setExt(String ext) {
		this.ext = ext;
	}
	public List<listadoDocumentos> getListado() {
		return listado;
	}


	public void setListado(List<listadoDocumentos> listado) {
		RepositorioAdmBean.listado = listado;
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
	public RepositorioAdmBean() throws SQLException{
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
	 	tipovideo="2";
		
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
	public UploadedFile getFilesUpload() {
		return filesUpload;
	}


	public void setFilesUpload(UploadedFile filesUpload) {
		this.filesUpload = filesUpload;
	}


	public void setTipoArchivos(List<selecItem> tipoArchivos) {
		this.tipoArchivos = tipoArchivos;
	}
	public void uploadFile(FileUploadEvent event) {
		try {			
			 setFilesUpload(event.getFile());
			 archivo=event;
		} catch (Exception e) {

			FacesMessageUtil.error(FacesContext.getCurrentInstance(),"Error al Subir Archivo");
			e.printStackTrace();
		}
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
	
	
	public String subirArchivo(){
		String page=null;
//		FacesMessage msg = new FacesMessage("Archivo cargado exitosamente! ", archivo.getFile().getFileName() + " is uploaded.");  
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file    
		 if(tipoarchivo==null || tipoarchivo.equals("")){
	        	FacesMessageUtil.error(FacesContext.getCurrentInstance(),"Debe seleccionar un tipo de archivo");
				return page;
	        	
	     }
		if(categoria==null || categoria.equals("")){
        	FacesMessageUtil.error(FacesContext.getCurrentInstance(),"Debe seleccionar una categoria");
			return page;
        }
       
        if(archivo==null && tipovideo.equals("2")){
        	FacesMessageUtil.error(FacesContext.getCurrentInstance(),"Debe seleccionar un archivo");
			return page;
        	
        }
        if((ubicacion==null || ubicacion.equals("")) && tipovideo.equals("1")){
        	FacesMessageUtil.error(FacesContext.getCurrentInstance(),"Debe ingresar URL del video");
			return page;
        	
        }
	        try {
	        	if(tipovideo.equals("1")){
	        		 ControllerMicrositio.subirArchivo("", fecha, tipoarchivo, categoria, palabras, "", ubicacion, "null", "");
			            limpiar();
				        FacesContext.getCurrentInstance().addMessage("Succesful", new FacesMessage(" URL cargada con exito"));
	        	}else{
		            copyFile(archivo.getFile().getFileName(), archivo.getFile().getInputstream(),categoria+"/"+fecha);
		            ControllerMicrositio.subirArchivo("", fecha, tipoarchivo, categoria, palabras, "", "null", archivo.getFile().getFileName(), "");
		            limpiar();
			        FacesContext.getCurrentInstance().addMessage("Succesful", new FacesMessage(archivo.getFile().getFileName() + " is uploaded."));
	        	}
	            return page;
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        return page;
		
	}
	public void copyFile(String fileName, InputStream in,String carpeta) {
        try {
          

        	File folder = new File("Archivos/"+carpeta);
        	folder.mkdirs();
             // write the inputStream to a FileOutputStream
             OutputStream out = new FileOutputStream(new File("Archivos/"+carpeta+"/"+fileName));
          
             int read = 0;
             byte[] bytes = new byte[1024];
          
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
          
             in.close();
             out.flush();
             out.close();
          
             System.out.println("Archivo en el repositorio...¡¡¡");
             } catch (IOException e) {
             System.out.println(e.getMessage());
             }
 }
	public void cargar(){
		
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
	 public void limpiar(){
		 SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		 categoria=null;
		 tipoarchivo=null;
		 archivo=null;
		 Date fec;
		 fec=new Date();
		 fecha=df1.format(fec).toString();
		 ubicacion=null;
		 filesUpload=null;
//		 file=null;
		 palabras=null;
		 tipovideo=null;
		 
	 }
	
	 public void actExt(){
		 
		 if(tipoarchivo.equals("6")){
			 filesUpload=null;
			 ext="/(\\.|\\/)(pdf|xls?x|doc?x|ppt?x)$/";
		 }
		if(tipoarchivo.equals("7")){
			filesUpload=null;
			 ext="/(\\.|\\/)(wav|mp3)$/";		 
		}
		if(tipoarchivo.equals("8")){
			filesUpload=null;
			ext="/(\\.|\\/)(wmv)$/";
		}
	 }
	 public boolean isVideo() {
			try {			
				if (tipoarchivo.equals("8")) {				
					return true;

			}	} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	 public boolean isVideolink() {
			try {			
				if (!tipovideo.equals("1") || !tipoarchivo.equals("8") ) {				
					return true;

			}	} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	 public boolean isUrl() {
			try {			
				if (tipovideo.equals("1")  ) {				
					return true;

			}	} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}

}
