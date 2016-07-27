package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilitario.ByteToPdf;
import utilitario.MailerClass;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class ReportesCtlr
 */
@WebServlet("/ReportesCtlr")
public class ReportesCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OutputStream out = null;
	Connection con = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportesCtlr() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		byte[] bytes=null;
		String id = request.getParameter("id");
		System.out.println("entra al servlet reporte " + id);
		//System.out.println(System.getProperty("user.dir"));
		//System.out.println("JBoss Home: "+ReportesCtlr.class.getResourceAsStream("reportes"+ File.separator + "cliente_epn.jasper")); 
		
		//System.out.println(this.getClass().getResource("cliente_epn.jasper").toString());

		
		try {

			Connection con = null;
			//
			String reportPath = "";
			String realPath = System.getProperty("user.dir") + File.separator +"reportes";
			String inputStream = null;
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			if (id.equals("cliente")) {
				//reportPath = realPath + File.separator + "cliente_epn.jasper";
				inputStream = getClass().getClassLoader().getResource(File.separator + "reportes" + File.separator + "cliente_epn.jasper").getPath();
				parametros.put("cliente", "%%");
				System.out.println(inputStream);
			} else if (id.equals("producto")) {
				reportPath = realPath + File.separator + "producto_epn.jasper";
				parametros.put("producto", "%%");
			}

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/inventarioepn",
							"root", "root");

			if (con != null) {
				System.out
						.println("Conexi�n Realizada Correctamenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				// return con;
			} else {
				System.out.println("error en la conexion");
			}

			System.out.println("ruta " + reportPath);
			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			
			
			
			FileInputStream is = null;
			is = new FileInputStream(inputStream);

			bytes = JasperRunManager.runReportToPdf(is, parametros, con);

			try {

				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				servletOutputStream.write(bytes, 0, bytes.length);
				servletOutputStream.flush();
				servletOutputStream.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean estadoEnvio;
		
		// DESCOMENTAR ESTO ANTES DE PRESENTAR EL PROYECTO
		/*try {
			
//			ByteToPdf.byteArrayToFile(bytes);
			MailerClass mailerClass = new MailerClass();
			List<String> listaEnvio = new ArrayList<String>();
			listaEnvio.add("darwinvinicio14_11@hotmail.com");
			estadoEnvio = mailerClass.sendMail(listaEnvio,
					"Prueba de mail de aplicaciones web 2016, se ha generado un reporte", "",
					"Presentacion del proyecto", "nombreArchivo.jpg");
			
			if(estadoEnvio){
				System.out.println("enviado con exito");
			}else{
				System.out.println("no se enviooooooo");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
