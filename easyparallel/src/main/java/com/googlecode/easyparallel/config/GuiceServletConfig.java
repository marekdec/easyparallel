package com.googlecode.easyparallel.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.googlecode.easyparallel.NewExecutionServlet;
import com.googlecode.easyparallel.NewTaskServlet;
import com.googlecode.easyparallel.RemoveTaskServlet;
import com.googlecode.easyparallel.StatusServlet;
import com.googlecode.easyparallel.task.ExecutionRepository;
import com.googlecode.easyparallel.task.TaskRepository;
import com.googlecode.easyparallel.task.googledatastore.GoogleDatastoreExecutionRepository;
import com.googlecode.easyparallel.task.googledatastore.GoogleDatastoreTaskRepository;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {

			@Override
			protected void configureServlets() {
				serve("/tasks").with(StatusServlet.class);
				serve("/newtask").with(NewTaskServlet.class);
				serve("/task/remove").with(RemoveTaskServlet.class);
				serve("/newexecution").with(NewExecutionServlet.class);

				bind(TaskRepository.class).to(
						GoogleDatastoreTaskRepository.class);
				bind(ExecutionRepository.class).to(
						GoogleDatastoreExecutionRepository.class);
			}

		});
	}
}
