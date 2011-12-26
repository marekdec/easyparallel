package com.googlecode.easyparallel;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.Integer.parseInt;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.easyparallel.task.Execution;
import com.googlecode.easyparallel.task.ExecutionRepository;
import com.googlecode.easyparallel.task.Task;
import com.googlecode.easyparallel.task.TaskRepository;

@SuppressWarnings("serial")
@Singleton
public class NewExecutionServlet extends HttpServlet {

	@Inject
	TaskRepository taskRepository;
	@Inject
	ExecutionRepository executionRepository;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (isNullOrEmpty(request.getParameter("requestedExecutors"))) {

			sendError("You have to specify the number of requested executors.",
					request, response);
		} else {
			try {
				int requestedExecutorsCount = parseInt(request
						.getParameter("requestedExecutors"));

				Task task = taskRepository.getById(request
						.getParameter("taskid"));

				if (task == null) {
					// TODO: log error
					response.sendError(SC_INTERNAL_SERVER_ERROR,
							"Task unexpectedly not found.");
				} else {

					Execution newExecution = new Execution(task,
							requestedExecutorsCount);

					executionRepository.save(newExecution);

					response.sendRedirect("/tasks");
				}
			} catch (NumberFormatException e) {
				sendError("You must enter a number.", request, response);
			}
		}
	}

	private void sendError(String error, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setAttribute("errors", error);
		request.setAttribute("id", request.getAttribute("taskid"));
		RequestDispatcher view = request
				.getRequestDispatcher("newexecution.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			// TODO; log error
			response.sendError(SC_INTERNAL_SERVER_ERROR);
		}
	}
}
