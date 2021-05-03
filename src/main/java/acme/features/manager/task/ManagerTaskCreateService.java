package acme.features.manager.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamComponent;
import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.tasks.Visibility;
import acme.entities.words.Word;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.administrator.word.AdministratorWordRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task>{
		
		// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerTaskRepository repository;
		
		@Autowired
		protected AdministratorWordRepository wordSpamRepository;
		
		@Autowired
		protected AdministratorSpamRepository spamRepository;

		// AbstractCreateService<Manager, Task> interface --------------

		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			return true;
		}

		@Override
		public void bind(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "startDate", "endDate", "workload", "description", "optionalLink", "visibility", "executionPeriod");
		}

		@Override
		public Task instantiate(final Request<Task> request) {
			assert request != null;

			Task result;
			Date moment;

			moment = new Date(System.currentTimeMillis() - 1);

			result = new Task();
			result.setTitle("Example task 00");
			result.setStartDate(moment);
			result.setEndDate(moment);
			result.setWorkload(10.0);
			result.setDescription("This is the oldest announcement in the system.");
			result.setOptionalLink("http://www.sample-app.com");
			result.setVisibility(Visibility.PUBLIC);
			result.setExecutionPeriod(12l);
			result.setFinished(true);
			
			final Manager manager= this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
			result.setManager(manager);

			return result;
		}

		@Override
		public void validate(final Request<Task> request, final Task entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			assert entity.getStartDate().before(entity.getEndDate());
			assert entity.getWorkload()>=0;
			
		final Double threshold = this.spamRepository.findUniqueSpamModule().getThreshold();
		final List<Word> spamWords= this.wordSpamRepository.findMany();
		assert !SpamComponent.containSpam(entity.getTitle(),spamWords, threshold);
		assert !SpamComponent.containSpam(entity.getDescription(),spamWords, threshold);
		assert !SpamComponent.containSpam(entity.getOptionalLink(),spamWords, threshold);
		
		}

		@Override
		public void create(final Request<Task> request, final Task entity) {
			assert request != null;
			assert entity != null;
			
			final Manager manager= this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
			entity.setManager(manager);
			
			final Boolean finished = false;
			entity.setFinished(finished);

			this.repository.save(entity);
		}

}
