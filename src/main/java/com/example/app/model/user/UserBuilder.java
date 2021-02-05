package com.example.app.model.user;

public class UserBuilder {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserType userType;
	private UserStatus userStatus;
	private String address;

	public UserBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public UserBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder setUserType(UserType userType) {
		this.userType = userType;
		return this;
	}

	public UserBuilder setAddress(String address) {
		this.address = address;
		return this;
	}

	public User build() {
		return new User(id, firstName, lastName, email, password, userType, userStatus);
	}

	public UserBuilder setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
		return this;
	}
}
