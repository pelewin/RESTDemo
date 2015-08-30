package org.pelevin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by dmitry on 29.08.15.
 */
@MappedSuperclass
abstract public class BaseVO {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public BaseVO() {
		//for JPA
	}
}
