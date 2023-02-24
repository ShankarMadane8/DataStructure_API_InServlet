package com.servlet1Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


import customeException.EmptyLinkedListException;
import customeException.EmptyQueueException;
import customeException.LinkedListIndexOutOfBoundException;
import dataStructure.CustomeLinkedList;

@WebServlet("/linkedlist")
public class LinkedListController extends HttpServlet {

	CustomeLinkedList linkedList = new CustomeLinkedList();
	ObjectMapper objectMapper = new ObjectMapper();

	// ------------------------doGet Method-----------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Working linkedList Get Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		switch (request.getParameter("op")) {
		case "display":{
			try {
				int[] linkedlistArray = linkedList.display();

				String writeValueAsString = objectMapper.writeValueAsString(linkedlistArray);
				out.print(writeValueAsString);

			} catch (EmptyLinkedListException e) {
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

	// -------------------------doPost--------------------------------------

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Working LinkedList post method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		switch (request.getParameter("op")) {
		case "insert":{
			System.out.println("insert method");
			linkedList.insert(Integer.valueOf(request.getParameter("value")));
			String writeValueAsString = objectMapper.writeValueAsString("insert value: " + request.getParameter("value"));
			out.print(writeValueAsString);
			break;	
		}

		default:
			break;
		}

		out.flush();

	}

	// --------------------------doDelete----------------------------------------------

	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Working LinkedList Delete Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		switch (request.getParameter("op")) {
		case "delete":{
			try {
				linkedList.delete();
				String writeValueAsString = objectMapper.writeValueAsString("delete value Successfully...");
				out.print(writeValueAsString);
				// System.out.println(deQueue);
			} catch (EmptyLinkedListException e) {
				out.print(e);
				e.printStackTrace();
			}
			break;
		}
		case "deleteByIndex":{
			try {
				if (request.getParameter("index") != null) {
					linkedList.delete(Integer.valueOf(request.getParameter("index")));
					String writeValueAsString = objectMapper.writeValueAsString("delete value Successfully...");
					out.print(writeValueAsString);
				}

			} catch (EmptyLinkedListException e) {
				String writeValueAsString = objectMapper.writeValueAsString(e);
				out.print(writeValueAsString);
				e.printStackTrace();
			} catch (LinkedListIndexOutOfBoundException e) {
				out.print(e);
				e.printStackTrace();
			}
			break;
		}
			
			

		default:
			break;
		}

	}

}
