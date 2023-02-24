package com.servlet1Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import customeException.EmptyArrayException;
import dataStructure.CustomeDynamicArray;


@WebServlet("/array")
public class ArrayController extends HttpServlet {

	CustomeDynamicArray array = new CustomeDynamicArray();
	ObjectMapper objectMapper = new ObjectMapper();

	// ------------------------doGet Method-----------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Working Array Get Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		switch (request.getParameter("op")) {
		case "display":
			try {
				int[] newArray = array.display();
				
				//String jsonArray = this.gson.toJson(newArray);
				//out.print(jsonArray);
				String writeValueAsString = objectMapper.writeValueAsString(newArray);
				out.print(writeValueAsString);
				

			} catch (EmptyArrayException e) {
				String writeValueAsString = objectMapper.writeValueAsString(e);
				out.print(writeValueAsString);
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		
		out.flush();

	}

	
	// -------------------------doPost--------------------------------------

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Working Array post method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		switch (request.getParameter("op")) {
		case "append":
			System.out.println("append method");
			array.append(Integer.valueOf(request.getParameter("value")));
			String writeValueAsString = objectMapper.writeValueAsString("append value: " + request.getParameter("value"));
			out.print(writeValueAsString);
			break;

		default:
			break;
		}
		
		out.flush();


	

	}

	// --------------------------doDelete----------------------------------------------

	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Working Array Delete Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		switch (request.getParameter("op")) {
		case "delete":{
			try {
				array.delete();
				String writeValueAsString = objectMapper.writeValueAsString("delete value Successfully...");
				out.print(writeValueAsString);
				System.out.println("delete value");
			} catch (EmptyArrayException e) {
				String writeValueAsString = objectMapper.writeValueAsString(e);
				out.print(writeValueAsString);
				
				e.printStackTrace();
			}
			break;
		}
		case "deleteByIndex":{
			try {
				if (request.getParameter("index") != null) {
					array.delete(Integer.valueOf(request.getParameter("index")));
					String writeValueAsString = objectMapper.writeValueAsString("delete value Successfully...");
					out.print(writeValueAsString);
					System.out.println("delete value   ..");
				}

			} catch (Exception e) {
				String writeValueAsString = objectMapper.writeValueAsString(e);
				out.print(writeValueAsString);
				e.printStackTrace();
			}
			break;
		}			
		default:
			break;
		}
		
		out.flush();

	}

}
