package la.netco.controller;


import java.sql.ResultSet;
import java.sql.SQLException;

import la.netco.persistence.PersistenceUtil;


public class ControllerMicrositio {

	public static ResultSet selecDocumento(){
		
		String sql ="select * from TYPE_DOCUMENT " ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("no ahy  registro cargadas en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}
	public static ResultSet selecCategoria(){
		
		String sql ="select * from CATEGORY_DOCUMENT " ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("no ahy  registro cargadas en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}
	
	public static ResultSet listado(String filtros){
		
		String sql ="select documento,detalle_document,detalle_category ,fecha_publicacion,palabras_claves,ID_CATEGORY,ID_TIPO, ruta from DOCUMENT " +
				"inner join TYPE_DOCUMENT on ID_TIPO=TIPO_DOCUMENTO " +
				"inner join CATEGORY_DOCUMENT on CATEGORIA=ID_CATEGORY where id>'1' "+filtros ;
		try {
			
			ResultSet result;			
			result=PersistenceUtil.realizaConsulta(sql);				
			if(result!=null){			
			       return result;
			}else{				
				System.out.println("no ahy  registro cargadas en la base de datos");
			}			
			PersistenceUtil.terminaOperacion();			
		} catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Error de conexion a la bd  "+ex.getMessage());		
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error  excepcion  "+ex.getMessage());		
		}
		return null;		
	}
	public static void subirArchivo(String detalle, String fecha, String tipo, String categoria, String palabras, String estado, String ruta, String documento, String ruta_rep)
    {
        String sql = "";
        sql = "insert into DOCUMENT (DETALLE,FECHA_PUBLICACION,TIPO_DOCUMENTO,CATEGORIA,PALABRAS_CLAVES,ESTADO,RUTA,DOCUMENTO,RUTAL_REPOSITORIO) " +
              "values('"+detalle+"','"+fecha+"','"+tipo+"','"+categoria+"','"+palabras+"','"+estado+"','"+ruta+"','"+documento+"','"+ruta_rep+"')";       
        try
        {
            PersistenceUtil.ejecutaSentencia(sql);
            PersistenceUtil.terminaOperacion();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
	


}
