package com.googlecode.easyparallel;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.easyparallel.task.ExecutionRepository;
import com.googlecode.easyparallel.task.TaskRepository;

@Singleton
public class StatusServlet extends HttpServlet {

	private static final long serialVersionUID = -3377748971950283642L;

	@Inject
	TaskRepository taskRepository;

	@Inject
	ExecutionRepository executionRepository;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws IOException {

		req.setAttribute("tasks", taskRepository.getAll());
		req.setAttribute("executions", executionRepository.getAll());

		RequestDispatcher view = req.getRequestDispatcher("status.jsp");
		try {
			view.forward(req, response);
		} catch (ServletException e) {
			e.printStackTrace();
			response.sendError(SC_INTERNAL_SERVER_ERROR);
		}
	}

}
