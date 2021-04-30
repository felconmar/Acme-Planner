/*
 * AdministratorAnnouncementCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.shouts.Shout;
import acme.entities.tasks.Task;
import acme.entities.tasks.Visibility;
import acme.entities.workplans.Workplan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.UserRole;
import acme.framework.repositories.AuthenticationRepository;
import acme.framework.services.AbstractCreateService;
  
@Service
public class ManagerWorkplanCreateService implements AbstractCreateService<Manager, Workplan> {
 
	@Autowired
	protected ManagerWorkplanRepository repository;
	
	@Autowired
	protected AuthenticationRepository authRepository;
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
 

	 
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
		
		 
		final int id = request.getPrincipal().getAccountId();
		final Collection<UserRole> l = this.authRepository.findOne(id).getRoles();
		if (this.authRepository.findOne(id).getRoles().stream().map(x->x.getAuthorityName()).collect(Collectors.toList()).contains("Manager")) {
			return true;
		}
		else {
			return false;
		}
	}

	
	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	 
	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final Set<Task> tasks = this.taskRepository.findMany().stream().collect(Collectors.toSet());
		model.setAttribute("tasks", tasks);
		request.unbind(entity, model, "version", "startDate", "endDate", "workload", "visibility", "tasks");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}

	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request != null;

		Workplan result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result = new Workplan();
		final Shout shout = new Shout();
		shout.setAuthor("sssssssssss");
		shout.setId(111);
		shout.setInfo("ssssssssssssss");
		shout.setMoment(null);
		shout.setText("ssssssssssssssssssssssssssssssss");
		shout.setVersion(4);
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("prueba", shout.getAuthor());
		map.put("prueba2", "Patata2");
		map.put("prueba3", "Prueba3");
		map.put("prueba4", "Patata4");
		map.put("prueb5", "Prueba5");
		map.put("prueba6", "Patata6");
		request.getModel().append(map);
		result.setEndDate(moment);
		result.setStartDate(moment);
		result.setVersion(1);
		result.setWorkload(0.0);
		result.setVisibility(Visibility.PRIVATE);

		return result;
	}

	 
	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final boolean confirmation; //comprobar restriccion de crear workplans con tareas publicas o privadas
		
//		confirmation = request.getModel().getBoolean("confirmation");
//		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	 
	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
		entity.setWorkload(entity.calculateWorkload());
 
		 
		this.repository.save(entity);
	}


 
	 

}
