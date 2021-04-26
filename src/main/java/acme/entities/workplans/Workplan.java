/*
 * Shout.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.workplans;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.entities.tasks.Visibility;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Workplan extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date			startDate;

	@Temporal(TemporalType.TIMESTAMP)

	@NotNull
	protected Date			endDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "workplan_task", joinColumns = @JoinColumn(name = "workplan_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
	protected Set<Task>			tasks;

	@Digits(integer = 2, fraction = 2)
	protected Double			workload;

	@NotNull
	protected Visibility		visibility;
	
	protected Long executionPeriod;


	public Double calculateWorkload() {

		final Set<Task> tasks = this.getTasks();

		if (tasks.isEmpty()) {
			return 0.0;
		} else {
			return tasks.stream().mapToDouble(x -> x.getWorkload()).sum();
		}

	}

}
