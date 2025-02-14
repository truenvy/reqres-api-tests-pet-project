package org.truenvy.reqres.asserters;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.truenvy.reqres.rest.models.responses.users.get.GetSingleUserResponse;

/**
 * A utility class to assert properties of the response returned from
 * a "Get Single User" API call. It validates the structure and content
 * of the response, ensuring compliance with expected API behavior.
 * The assertions are presented in a fluent interface style for
 * better readability and chaining support.
 */
@Slf4j
public class SingleUserResponseAsserter {
    private final Response response;
    private final GetSingleUserResponse getSingleUserResponse;

    private SingleUserResponseAsserter(Response response) {
        this.response = response;
        if (response.getStatusCode() == 200) {
            this.getSingleUserResponse = response.as(GetSingleUserResponse.class);
        } else {
            this.getSingleUserResponse = null;
        }
    }

    public static SingleUserResponseAsserter assertThat(Response response) {
        return new SingleUserResponseAsserter(response);
    }

    @Step("API response should have status code: {statusCode}")
    public SingleUserResponseAsserter toHaveStatusCode(int statusCode) {
        log.info("API request should have status code: {}", statusCode);
        response.then().statusCode(statusCode);
        return this;
    }

    @Step("API response should have id: {id}}")
    public SingleUserResponseAsserter toHaveIdNotNull(String id) {
        log.info("API response should have id: {}", id);
        Assertions.assertThat(this.getSingleUserResponse.data().id()).isEqualTo(Integer.parseInt(id));
        return this;
    }

    @Step("API response should have email not null")
    public SingleUserResponseAsserter toHaveEmailNotNull() {
        log.info("API response should have email not null");
        Assertions.assertThat(this.getSingleUserResponse.data().email()).isNotNull();
        return this;
    }

    @Step("API response should have first name not null")
    public SingleUserResponseAsserter toHaveFirstNameNotNull() {
        log.info("API request should have first name not null");
        Assertions.assertThat(this.getSingleUserResponse.data().firstName()).isNotNull();
        return this;
    }

    @Step("API response should have last name not null")
    public SingleUserResponseAsserter toHaveLastNameNotNull() {
        log.info("API response should have last name not null");
        Assertions.assertThat(this.getSingleUserResponse.data().lastName()).isNotNull();
        return this;
    }
}
