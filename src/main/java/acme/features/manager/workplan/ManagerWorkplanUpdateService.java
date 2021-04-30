package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workplans.Workplan;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.administrator.word.AdministratorWordRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Manager, Workplan> {

	
	@Autowired
	protected ManagerWorkplanRepository repository;
	
	@Autowired
	protected AdministratorWordRepository wordSpamRepository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		return true;
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
		
		request.unbind(entity, model, "version", "startDate", "endDate", "workload", "visibility");

	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;
		return this.repository.findOneWorkplanFromId(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		entity.setWorkload(entity.calculateWorkload());
		this.repository.save(entity);
	}
	
	@Override
	public void onSuccess(final Request<Workplan> request, final Response<Workplan> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
