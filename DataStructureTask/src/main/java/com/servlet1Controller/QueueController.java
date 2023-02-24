package com.servlet1Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import customeException.EmptyQueueException;

import dataStructure.CustomeDynamicQueue;

@WebServlet("/Queue")
public class QueueController extends HttpServlet {
	CustomeDynamicQueue queue = new CustomeDynamicQueue();
	ObjectMapper objectMapper=new ObjectMapper();

	// ------------------------doGet Method-----------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Working Queue Get Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		switch (request.getParameter("op")) {
		case "display": {
			try {
				int[] queueArray = queue.display();
				String writeValueAsString = objectMapper.writeValueAsString(queueArray);
				out.print(writeValueAsString);

			} catch (EmptyQueueException e) {
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
		System.out.println("Working stack post method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		switch (request.getParameter("op")) {
		case "enQueue": {
			// System.out.println("push method");
			queue.enQueue(Integer.valueOf(request.getParameter("value")));
			String writeValueAsString = objectMapper.writeValueAsString("enQueue value: " + request.getParameter("value"));
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
		System.out.println("Working Queue Delete Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		switch (request.getParameter("op")) {
		case "deQueue": {
			try {
				int deQueue = queue.deQueue();
				String writeValueAsString = objectMapper.writeValueAsString("deQueue value: " + deQueue);
				out.print(writeValueAsString);
				// System.out.println(deQueue);
			} catch (EmptyQueueException e) {
				String writeValueAsString = objectMapper.writeValueAsString(e);
				out.print(writeValueAsString);
				e.printStackTrace();
			}
			break;
		}

		default:
			break;
		}

	}

}
