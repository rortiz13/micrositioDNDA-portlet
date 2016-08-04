

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MiServlet
 */

public class MiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private String BasePath = "C:/docs/Archivos";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Aqui se hace si es por metodo POST
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//		String folder = request.getParameter("folder");
//		String date = request.getParameter("date");
		String fileName = request.getParameter("file");
		
		if(fileName!=null){

				
				ServletOutputStream stream = null;
				BufferedInputStream buf = null;
				File file = new File("Archivos/"+fileName.replace("-", ""));				
					try {
					  stream = response.getOutputStream();
					  //set response headers//			
					  response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
					  response.addHeader("Content-Disposition", "inline; filename=" + fileName);
					  response.setContentLength((int) file.length());
					  FileInputStream input = new FileInputStream(file);
					  buf = new BufferedInputStream(input);
					  int readBytes = 0;
					  //read from the file; write to the ServletOutputStream
					  while ((readBytes = buf.read()) != -1)
					    stream.write(readBytes);
					} catch (IOException ioe) {
					  throw new ServletException(ioe.getMessage());
					} finally {
					  if (stream != null)
					    stream.close();
					  if (buf != null)
					    buf.close();
					}
				

		}
		else{
			mostrarMensaje("Error en el URL", response);
		}        
    }

	
	
	public void mostrarMensaje(String msj, HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title>Hello World</title></title>");
		pw.println("<body>");
		pw.println("<h1>"+msj+"</h1>");
		pw.println("</body></html>");
		pw.close();
	}


	
}
