package com.badplanner.model

class User {
	
	String userName
	String firstName
	String lastName
	String email
	
	static constraints = {
		userName(blank:false, unique:true)
		firstName(blank:false)
		lastName(blank:false)
		email(blank:false, email:true)
	}
}
