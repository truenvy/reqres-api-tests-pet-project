package org.truenvy.reqres.rest.models.responses.users;

public record UserUpdateResponse(
	String createdAt,
	String name,
	String id,
	String job
) {
}
