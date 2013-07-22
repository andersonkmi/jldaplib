package org.sharpsw.ldap;

import org.sharpsw.ldap.exception.InvalidParameterException;

public aspect GroupValidatorAspect {
	pointcut memberAdditionString(String name) : execution(public void Group.add(String)) && args(name);
	pointcut memberAdditionInstance(User user) : execution(public void Group.add(User)) && args(user);
	
	void around(String name) throws InvalidParameterException : memberAdditionString(name) {
		if(name == null) {
			throw new InvalidParameterException("Null user name");
		} else if(name.isEmpty()) {
			throw new InvalidParameterException("Empty name provided");
		} else {
			proceed(name);
		}
	}
	
	void around(User user) throws InvalidParameterException : memberAdditionInstance(user) {
		if(user == null) {
			throw new InvalidParameterException("Null user");
		} else {
			proceed(user);
		}
	}
}
