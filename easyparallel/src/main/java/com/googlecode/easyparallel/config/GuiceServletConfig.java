package com.googlecode.easyparallel.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.googlecode.easyparallel.EasyparallelServlet;
import com.googlecode.easyparallel.NewTaskServlet;
import com.googlecode.easyparallel.TaskProcessor;
import com.googlecode.easyparallel.task.TaskRepository;
import com.googlecode.easyparallel.task.googledatastore.GoogleDatastoreTaskRepository;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {

			@Override
			protected void configureServlets() {
				serve("/tasks").with(EasyparallelServlet.class);
				serve("/newtask").with(NewTaskServlet.class);
				serve("/task/remove").with(TaskProcessor.class);

				bind(TaskRepository.class).to(
						GoogleDatastoreTaskRepository.class);
			}

		});
	}
}
