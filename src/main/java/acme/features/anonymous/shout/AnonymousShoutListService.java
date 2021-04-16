/*
 * AnonymousShoutListService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.shout;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository repository;


	// AbstractListService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}

	

	@Override
	public Collection<Shout> findMany(final Request<Shout> request) {
		assert request != null;

		Collection<Shout> result;
		final List<Shout> list = new ArrayList<Shout>();
		result = this.repository.findMany().stream().collect(Collectors.toList());
		
		for(final Shout s: result) {			
			if (Period.between(this.convertToLocalDateViaMilisecond(s.getMoment()),LocalDate.now() ).getMonths()<1 && Period.between(this.convertToLocalDateViaMilisecond(s.getMoment()),LocalDate.now() ).getYears()<1) {
				list.add(s);
				
			}		
			}

		return list;
		
			
			
	
	}
	public LocalDate convertToLocalDateViaMilisecond(final Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

}
