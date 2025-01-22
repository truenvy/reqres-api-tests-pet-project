package org.truenvy.reqres.rest.models.responses.users.get;

public record GetSingleUserResponse(
	UserData data,
	Support support
) {
}
