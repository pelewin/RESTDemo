package org.pelevin.model;

import org.springframework.data.annotation.Id;

/**
 * Created by dmitry on 29.08.15.
 */
abstract public class BaseVO {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public BaseVO() {
		//for JPA
	}
}
