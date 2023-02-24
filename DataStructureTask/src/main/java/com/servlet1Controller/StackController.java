package com.servlet1Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


import customeException.EmptyStackException;
import dataStructure.CustomeStack;

@WebServlet("/stack")
public class StackController extends HttpServlet {

	CustomeStack stack = new CustomeStack();
	ObjectMapper objectMapper=new ObjectMapper();


	// ------------------------doGet Method-----------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// System.out.println("Working stack Get Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		switch (request.getParameter("op")) {
		case "peek": {
			try {
				int peek = stack.peek();
				System.out.println("peek value:" + peek);
				String writeValueAsString = objectMapper.writeValueAsString("peek value:" + peek);
				out.print(writeValueAsString);
			} catch (EmptyStackException e) {
				String writeValueAsString = objectMapper.writeValueAsString(e);
				out.print(writeValueAsString);
				e.printStackTrace();
			}
			break;
		}
		case "display": {
			try {
				int[] stackArray = stack.display();
				String writeValueAsString = objectMapper.writeValueAsString(stackArray);
				out.print(writeValueAsString);
			} catch (EmptyStackException e) {
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
		case "push": {
			System.out.println("push method");
			stack.push(Integer.valueOf(request.getParameter("value")));
			String writeValueAsString = objectMapper.writeValueAsString("push value: " + request.getParameter("value"));
			out.print(writeValueAsString);

			break;

		}

		default:
			break;
		}

		out.flush();

	}

	// -------------------------doDelete--------------------------------------

	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// System.out.println("Working stack Delete Method");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		switch (request.getParameter("op")) {
		case "pop": {
			try {
				int pop = stack.pop();
				String writeValueAsString = objectMapper.writeValueAsString("pop value: " + pop);
				out.print(writeValueAsString);
			} catch (EmptyStackException e) {
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
