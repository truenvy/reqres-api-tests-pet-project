package org.truenvy.reqres.asserters;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.truenvy.reqres.rest.models.requests.users.UserRequest;
import org.truenvy.reqres.rest.models.responses.users.UserUpdateResponse;

import java.util.Objects;

public class CreateUserCustomAssert extends AbstractAssert<CreateUserCustomAssert, Response> {

    protected CreateUserCustomAssert(Response userUpdateResponse) {
        super(userUpdateResponse, CreateUserCustomAssert.class);
    }

    public static CreateUserCustomAssert assertThat(Response userUpdateResponse) {
        return new CreateUserCustomAssert(userUpdateResponse);
    }

    public CreateUserCustomAssert hasStatusCode(int statusCode) {
        isNotNull();
        if (actual.getStatusCode() != statusCode) {
            failWithMessage("Expected status code <%s> but was <%s>", statusCode, actual.getStatusCode());
        }
        return this;
    }

    public CreateUserCustomAssert hasUserData(UserRequest userRequest) {
        isNotNull();
        var userResponse = actual.as(UserUpdateResponse.class);
        if (
                !Objects.equals(userRequest.name(), userResponse.name()) &&
                !Objects.equals(userRequest.job(), userResponse.job())
        ) {
            failWithMessage("Expected name <%s> but was <%s>", userRequest.name(), userResponse.name());
            failWithMessage("Expected job <%s> but was <%s>", userRequest.job(), userResponse.job());
        }
        return this;
    }

    public CreateUserCustomAssert hasIdNotNull() {
        isNotNull();
        var userResponse = actual.as(UserUpdateResponse.class);
        if (Objects.isNull(userResponse.id())) {
            failWithMessage("Expected id is null");
        }
        return this;
    }

    public CreateUserCustomAssert hasCreatedAtNotNull() {
        isNotNull();
        var userResponse = actual.as(UserUpdateResponse.class);
        if (Objects.isNull(userResponse.createdAt())) {
            failWithMessage("Expected createdAt is null");
        }
        return this;
    }
}
