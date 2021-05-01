package acme.features.anonymous.workplan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workplans.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkplanRepository extends AbstractRepository {
	
	@Query("select w from Workplan w where w.id = ?1")
	Workplan findOneWorkPlanFromId(int id);


}
