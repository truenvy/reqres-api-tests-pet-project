package org.truenvy.reqres.rest.models.responses.users.get;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserData(
	@JsonProperty("last_name") String lastName,
	Integer id,
	String avatar,
	@JsonProperty("first_name") String firstName,
	String email
) {
}
