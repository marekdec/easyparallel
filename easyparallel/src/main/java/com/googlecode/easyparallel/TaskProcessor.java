package com.googlecode.easyparallel;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;
import com.googlecode.easyparallel.task.TaskRepository;

@Singleton
public class TaskProcessor extends HttpServlet {

	private static final long serialVersionUID = 1200670970536384617L;

	private final TaskRepository taskRepository;

	@Inject
	public TaskProcessor(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws IOException {

		String id = req.getParameter("id");

		if (id != null) {
			taskRepository.removeWithRelatedExecutions(id);

			response.sendRedirect("/tasks");
		} else {
			response.sendError(SC_INTERNAL_SERVER_ERROR, "Task id missing.");
		}

	}
}
