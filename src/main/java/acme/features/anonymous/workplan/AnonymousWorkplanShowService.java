package acme.features.anonymous.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplans.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousWorkplanShowService implements AbstractShowService<Anonymous, Workplan>{

	@Autowired
	AnonymousWorkplanRepository repository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
	/*
		final boolean result;
		final boolean isFinished;
		final boolean isPublic;
		Workplan workPlan;
		int id;

		id = request.getModel().getInteger("id");
		workPlan = this.repository.findOneWorkPlanFromId(id);
		isFinished= workPlan.getFinished();
		isPublic= workPlan.getVisibility().equals(Visibility.PUBLIC);
		return isPublic && !isFinished;*/
		return true;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "startDate", "endDate", "workload", "visibility","id");
	
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		Workplan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanFromId(id);

		return result;
	}

}
