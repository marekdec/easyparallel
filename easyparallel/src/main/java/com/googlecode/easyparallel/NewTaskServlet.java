package com.googlecode.easyparallel;

import static com.google.common.base.Strings.isNullOrEmpty;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.easyparallel.task.Task;
import com.googlecode.easyparallel.task.TaskRepository;

@SuppressWarnings("serial")
@Singleton
public class NewTaskServlet extends HttpServlet {

	@Inject
	TaskRepository taskRepository;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (isNullOrEmpty(request.getParameter("performFunction"))
				|| isNullOrEmpty(request.getParameter("mergeFunction"))
				|| isNullOrEmpty(request.getParameter("name"))) {

			request.setAttribute("errors",
					"All fields are required. Please fix the errors.");
			RequestDispatcher view = request
					.getRequestDispatcher("newtask.jsp");
			try {
				view.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
				response.sendError(SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			Task newTask = new Task(request.getParameter("name"),
					request.getParameter("performFunction"),
					request.getParameter("mergeFunction"));

			taskRepository.save(newTask);

			response.sendRedirect("/tasks");
		}
	}
}
