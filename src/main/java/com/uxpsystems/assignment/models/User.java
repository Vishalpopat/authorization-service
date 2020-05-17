package com.uxpsystems.assignment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "user_name")
	@Size(max=12)
	private String username;
//	@Size(max=50)
	private String password;
}
