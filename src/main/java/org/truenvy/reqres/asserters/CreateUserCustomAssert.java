package org.truenvy.reqres.asserters;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractAssert;
import org.truenvy.reqres.rest.models.requests.users.UserRequest;
import org.truenvy.reqres.rest.models.responses.users.UserUpdateResponse;

import java.util.Objects;

/**
 * A custom assertion class for validating the response of the Create User API call.
 * This class extends {@link AbstractAssert}, providing a custom implementation for
 * asserting specific response details.
 */
@Slf4j
public class CreateUserCustomAssert extends AbstractAssert<CreateUserCustomAssert, Response> {

    protected CreateUserCustomAssert(Response userUpdateResponse) {
        super(userUpdateResponse, CreateUserCustomAssert.class);
    }

    public static CreateUserCustomAssert assertThat(Response userUpdateResponse) {
        return new CreateUserCustomAssert(userUpdateResponse);
    }

    @Step("Assert that API response should have status code: {statusCode}")
    public CreateUserCustomAssert hasStatusCode(int statusCode) {
        log.info("Assert that API response should have status code: {}", statusCode);
        isNotNull();
        if (actual.getStatusCode() != statusCode) {
            failWithMessage("Expected status code <%s> but was <%s>", statusCode, actual.getStatusCode());
        }
        return this;
    }

    @Step("Assert that API response should have user data: {userRequest}")
    public CreateUserCustomAssert hasUserData(UserRequest userRequest) {
        log.info("Assert that API response should have user data: {}", userRequest);
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

    @Step("Assert that API response should have id not null")
    public CreateUserCustomAssert hasIdNotNull() {
        log.info("Assert that API response should have id not null");
        isNotNull();
        var userResponse = actual.as(UserUpdateResponse.class);
        if (Objects.isNull(userResponse.id())) {
            failWithMessage("Expected id is null");
        }
        return this;
    }

    @Step("Assert that API response should have createdAt not null")
    public CreateUserCustomAssert hasCreatedAtNotNull() {
        log.info("Assert that API response should have createdAt not null");
        isNotNull();
        var userResponse = actual.as(UserUpdateResponse.class);
        if (Objects.isNull(userResponse.createdAt())) {
            failWithMessage("Expected createdAt is null");
        }
        return this;
    }
}
