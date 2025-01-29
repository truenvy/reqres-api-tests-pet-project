package org.truenvy.reqres.rest.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.truenvy.reqres.configs.ConfigProvider;
import org.truenvy.reqres.configs.ReqresUrlConfig;
import org.truenvy.reqres.rest.configuration.RestApiClientSpec;
import org.truenvy.reqres.rest.models.requests.users.UserRequest;

/**
 * ReqresApiClient provides methods to interact with the Reqres API.
 * This class handles API requests such as retrieving a user by ID, retrieving a list of users,
 * creating a user, updating a user using PUT or PATCH methods, and deleting a user by ID.
 * <p>
 * The class extends functionality from RestApiClientSpec, allowing integration with
 * predefined request specifications and configurations.
 * <p>
 * Key features:
 * - Automates API interaction with the Reqres service.
 * - Supports GET, POST, PUT, PATCH, and DELETE methods.
 * - Provides logging for API interactions.
 */
@Slf4j
public class ReqresApiClient extends RestApiClientSpec {
    private static final ReqresUrlConfig reqresUrlConfig = ConfigProvider.INSTANCE.getReqresUrlConfig();

    @Step("Get user by ID: {id}")
    public Response getUserById(String id) {
        log.info("Get user by ID: {}", id);
        return getRequestSpec().get("%s/%s".formatted(reqresUrlConfig.users(), id));
    }

    @Step("Get list of users")
    public Response getUsers(String pageParam) {
        log.info("Get list of users");
        return getRequestSpec().pathParam("page", pageParam).get(reqresUrlConfig.users());
    }

    @Step("POST :: Create user: {body}")
    public Response createUser(UserRequest body) {
        log.info("POST :: Create user: {}", body);
        if (body == null) {
            return getRequestSpec().post(reqresUrlConfig.users());
        }
        return getRequestSpec().body(body).post(reqresUrlConfig.users());
    }

    @Step("PUT :: Update user: {body}")
    public Response putUpdateUser(UserRequest body) {
        log.info("PUT :: Update user: {}", body);
        return getRequestSpec().body(body).put(reqresUrlConfig.users());
    }

    @Step("PATCH :: Update user: {body}")
    public Response patchUpdateUser(UserRequest body) {
        log.info("PATCH :: Update user: {}", body);
        return getRequestSpec().body(body).patch(reqresUrlConfig.users());
    }

    @Step("Delete user by ID: {id}")
    public Response deleteUserById(String id) {
        log.info("Delete user by ID: {}", id);
        return getRequestSpec().delete("%s/%s".formatted(reqresUrlConfig.users(), id));
    }
}
