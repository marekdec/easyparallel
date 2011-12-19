package com.googlecode.easyparallel;

import java.io.IOException;

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
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		System.out.println(taskRepository);
	}

}
