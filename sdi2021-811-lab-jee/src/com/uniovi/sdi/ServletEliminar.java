package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletEliminar
 */
@WebServlet("/eliminarDeCarrito")
public class ServletEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String,Integer> carrito = (HashMap<String,Integer>) request.getSession().getAttribute("carrito");

		//Caso en el que no hay un carrito previo, se crea y se inserta en la sesión
		if(carrito == null) {
			carrito = new HashMap<String,Integer>();
			request.getSession().setAttribute("carrito", carrito);
		}
		
		String producto = request.getParameter("producto");

		if(producto != null) {
			eliminarDeCarrito(carrito,producto);
		}
		
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void eliminarDeCarrito(Map<String,Integer> carrito, String claveProducto) {
		carrito.remove(claveProducto); 
	}

}
