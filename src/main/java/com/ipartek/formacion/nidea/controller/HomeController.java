package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import UASI.WS_GRAL_wsdl.ClaseNoticias;
import UASI.WS_GRAL_wsdl.Pub_gralSoapProxy;



/**
 * Servlet implementation class HomeController
 */
@WebServlet( urlPatterns={"/","/noticias"} )
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
System.out.println("Llamando al Web Service de SOAP");
		
	Pub_gralSoapProxy client =new Pub_gralSoapProxy();	
	ClaseNoticias[] noticiasSOAP= client.wsnoticias("C", "2015-05-05");	
	
			
		System.out.println("Respuesta: ");
		System.out.println(noticiasSOAP);
		
		
		//cliente para ws UA
		
		//conseguir noticias
		
		//pasar como atributo en request noticias
		
		ArrayList<String> noticias = new ArrayList<>();
		for (ClaseNoticias n:noticiasSOAP) {
			noticias.add(n.getContenido()); 
	    }
		
		noticias.add("UA gana en Ruby");
		noticias.add("UA 500");
		noticias.add("UA gana en Futbol");
		
		request.setAttribute("noticias", noticias );
		
		request.getRequestDispatcher("noticias.jsp").forward(request, response);	
		
	}

}
