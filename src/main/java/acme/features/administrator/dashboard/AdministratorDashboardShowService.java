
/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"totalNumberOfPublicTasks", "totalNumberOfPrivateTasks", "totalNumberOfFinishedTasks", "totalNumberOfNonFinishedTasks",
			"averageTaskExecutionPeriod", "deviationTaskExecutionPeriod", "minTaskExecutionPeriod", "maxTaskExecutionPeriod",
			"averageTaskWorkload", "deviationTaskWorkload", "maxTaskWorkload", "minTaskWorkload",
			"totalNumberOfPublicWorkplans", "totalNumberOfPrivateWorkplans", "totalNumberOfFinishedWorkplans", "totalNumberOfNonFinishedWorkplans",
			"averageWorkplanExecutionPeriod", "deviationWorkplanExecutionPeriod", "minWorkplanExecutionPeriod", "maxWorkplanExecutionPeriod",
			"averageWorkplanWorkload", "deviationWorkplanWorkload", "maxWorkplanWorkload", "minWorkplanWorkload");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		final Integer totalNumberOfTasks;
		final Integer totalNumberOfPublicTasks;
		final Integer totalNumberOfPrivateTasks;
		final Integer totalNumberOfFinishedTasks;
		final Integer totalNumberOfNonFinishedTasks;
		
		final Long averageTaskExecutionPeriod;
		final Long deviationTaskExecutionPeriod;
		final Long minTaskExecutionPeriod;
		final Long maxTaskExecutionPeriod;
		
		final Double averageTaskWorkload;
		final Double deviationTaskWorkload;
		final Double minTaskWorkload;
		final Double maxTaskWorkload;
		
		final Integer totalNumberOfWorkplans;
		final Integer totalNumberOfPublicWorkplans;
		final Integer totalNumberOfPrivateWorkplans;
		final Integer totalNumberOfFinishedWorkplans;
		final Integer totalNumberOfNonFinishedWorkplans;
		
		final Long averageWorkplanExecutionPeriod;
		final Long deviationWorkplanExecutionPeriod;
		final Long minWorkplanExecutionPeriod;
		final Long maxWorkplanExecutionPeriod;
		
		final Double averageWorkplanWorkload;
		final Double deviationWorkplanWorkload;
		final Double minWorkplanWorkload;
		final Double maxWorkplanWorkload;
		
		
		totalNumberOfTasks =  this.repository.totalNumberOfTasks();
		totalNumberOfPublicTasks = this.repository.totalNumberOfPublicTasks();
		totalNumberOfPrivateTasks = totalNumberOfTasks - totalNumberOfPublicTasks;
		totalNumberOfFinishedTasks = this.repository.totalNumberOfFinishedTasks();
		totalNumberOfNonFinishedTasks = totalNumberOfTasks - totalNumberOfFinishedTasks;
		
		averageTaskExecutionPeriod = this.repository.averageTaskExecutionPeriod();
		deviationTaskExecutionPeriod = this.repository.deviationTaskExecutionPeriod();
		minTaskExecutionPeriod = this.repository.minTaskExecutionPeriod();
		maxTaskExecutionPeriod = this.repository.maxTaskExecutionPeriod();
		
		averageTaskWorkload = this.repository.averageTaskWorkload();
		deviationTaskWorkload = this.repository.deviationTaskWorkload();
		minTaskWorkload = this.repository.minTaskWorkload();
		maxTaskWorkload = this.repository.maxTaskWorkload();
		
		totalNumberOfWorkplans =  this.repository.totalNumberOfWorkplans();
		totalNumberOfPublicWorkplans = this.repository.totalNumberOfPublicWorkplans();
		totalNumberOfPrivateWorkplans = totalNumberOfWorkplans - totalNumberOfPublicWorkplans;
		totalNumberOfFinishedWorkplans = this.repository.totalNumberOfFinishedWorkplans();
		totalNumberOfNonFinishedWorkplans = totalNumberOfWorkplans - totalNumberOfFinishedWorkplans;
		
		averageWorkplanExecutionPeriod = this.repository.averageWorkplanExecutionPeriod();
		deviationWorkplanExecutionPeriod = this.repository.deviationWorkplanExecutionPeriod();
		minWorkplanExecutionPeriod = this.repository.minWorkplanExecutionPeriod();
		maxWorkplanExecutionPeriod = this.repository.maxWorkplanExecutionPeriod();
		
		averageWorkplanWorkload = this.repository.averageWorkplanWorkload();
		deviationWorkplanWorkload = this.repository.deviationWorkplanWorkload();
		minWorkplanWorkload = this.repository.minWorkplanWorkload();
		maxWorkplanWorkload = this.repository.maxWorkplanWorkload();
		
		
		result = new Dashboard();
		result.setTotalNumberOfPublicTasks(totalNumberOfPublicTasks);
		result.setTotalNumberOfPrivateTasks(totalNumberOfPrivateTasks);
		result.setTotalNumberOfFinishedTasks(totalNumberOfFinishedTasks);
		result.setTotalNumberOfNonFinishedTasks(totalNumberOfNonFinishedTasks);
		
		result.setAverageTaskExecutionPeriod(averageTaskExecutionPeriod);
		result.setDeviationTaskExecutionPeriod(deviationTaskExecutionPeriod);
		result.setMinTaskExecutionPeriod(minTaskExecutionPeriod);
		result.setMaxTaskExecutionPeriod(maxTaskExecutionPeriod);
		
		result.setAverageTaskWorkload(averageTaskWorkload);
		result.setDeviationTaskWorkload(deviationTaskWorkload);
		result.setMinTaskWorkload(minTaskWorkload);
		result.setMaxTaskWorkload(maxTaskWorkload);
		
		result.setTotalNumberOfPublicWorkplans(totalNumberOfPublicWorkplans);
		result.setTotalNumberOfPrivateWorkplans(totalNumberOfPrivateWorkplans);
		result.setTotalNumberOfFinishedWorkplans(totalNumberOfFinishedWorkplans);
		result.setTotalNumberOfNonFinishedWorkplans(totalNumberOfNonFinishedWorkplans);
		
		result.setAverageWorkplanExecutionPeriod(averageWorkplanExecutionPeriod);
		result.setDeviationWorkplanExecutionPeriod(deviationWorkplanExecutionPeriod);
		result.setMinWorkplanExecutionPeriod(minWorkplanExecutionPeriod);
		result.setMaxWorkplanExecutionPeriod(maxWorkplanExecutionPeriod);
		
		result.setAverageWorkplanWorkload(averageWorkplanWorkload);
		result.setDeviationWorkplanWorkload(deviationWorkplanWorkload);
		result.setMinWorkplanWorkload(minWorkplanWorkload);
		result.setMaxWorkplanWorkload(maxWorkplanWorkload);
		
		return result;
	}

}

