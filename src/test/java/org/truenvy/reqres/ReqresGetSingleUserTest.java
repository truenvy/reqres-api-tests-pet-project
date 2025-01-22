package org.truenvy.reqres;

import io.qameta.allure.Epic;
import io.qameta.allure.Epics;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.truenvy.reqres.asserters.SingleUserResponseAsserter;
import org.truenvy.reqres.base.AbstractBaseTest;

@Epics({
        @Epic("Reqres")
})
@Stories({
        @Story("Get single user")
})
public class ReqresGetSingleUserTest extends AbstractBaseTest {
    private static final int OK = 200;
    private static final int NOT_FOUND = 404;

    @Test
    @DisplayName("Reqres :: get single user :: positive test")
    void getSingleUser_positiveTest() {
        // given
        var userId = "2";

        // when
        var singleUserResponse = reqresApiClient.getUserById(userId);

        // then
        new SingleUserResponseAsserter(singleUserResponse)
                .toHaveStatusCode(OK)
                .toHaveIdNotNull(userId)
                .toHaveEmailNotNull()
                .toHaveFirstNameNotNull()
                .toHaveLastNameNotNull();
    }

    @Test
    @DisplayName("Reqres :: get single user :: user not found")
    void getSingleUser_userNotFound_errorTest() {
        // given
        var userId = "23";

        // when
        var singleUserResponse = reqresApiClient.getUserById(userId);

        // then
        new SingleUserResponseAsserter(singleUserResponse)
                .toHaveStatusCode(NOT_FOUND);
    }
}
