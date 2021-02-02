package com.uniovi.sdi;
import java.util.LinkedList; 
import java.util.List;
import com.db4o.Db4oEmbedded; 
import com.db4o.ObjectContainer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletProductos
 */
@WebServlet("/productos")
public class ServletProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProductos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Producto> productosTienda = new LinkedList<Producto>();
		ObjectContainer db = null;
		try {
			db = Db4oEmbedded.openFile("bdProductos");
			List<Producto> respuesta = db.queryByExample(Producto.class);
			productosTienda.addAll(respuesta);
		} finally { 
			db.close();
		}
		
		for(Producto p : productosTienda) {
			System.out.println(p.getNombre());
		}
		
		request.setAttribute("productosTienda", productosTienda);
		getServletContext().getRequestDispatcher("/vista-productos.jsp").forward(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
