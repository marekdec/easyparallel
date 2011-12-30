package com.googlecode.easyparallel;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;
import com.googlecode.easyparallel.task.ExecutionRepository;

@Singleton
public class RemoveExecutionServlet extends HttpServlet {

	private static final long serialVersionUID = 1200670970536384617L;

	private final ExecutionRepository executionRepository;

	@Inject
	public RemoveExecutionServlet(ExecutionRepository executionRepository) {
		super();
		this.executionRepository = executionRepository;
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws IOException {

		String id = req.getParameter("id");

		if (id != null) {
			executionRepository.remove(id);

			response.sendRedirect("/tasks");
		} else {
			response.sendError(SC_INTERNAL_SERVER_ERROR,
					"Execution id missing.");
		}

	}
}
