
package acme.entities.compositions;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.entities.workplans.Workplan;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Composition extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Task				task;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Workplan			workplan;

}
