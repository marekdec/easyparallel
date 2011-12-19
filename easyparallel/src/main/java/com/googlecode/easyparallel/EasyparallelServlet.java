package com.googlecode.easyparallel;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.easyparallel.task.TaskRepository;

@SuppressWarnings("serial")
@Singleton
public class EasyparallelServlet extends HttpServlet {

	@Inject
	TaskRepository taskRepository;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws IOException {

		req.setAttribute("tasks", Arrays.asList("test", "test1"));

		RequestDispatcher view = req.getRequestDispatcher("status.jsp");
		try {
			view.forward(req, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
