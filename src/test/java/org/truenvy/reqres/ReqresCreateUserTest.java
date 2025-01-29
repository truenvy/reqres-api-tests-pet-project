package org.truenvy.reqres;

import io.qameta.allure.Epic;
import io.qameta.allure.Epics;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.truenvy.reqres.asserters.CreateUserCustomAssert;
import org.truenvy.reqres.base.AbstractBaseTest;
import org.truenvy.reqres.data.factory.ReqresTestDataFactory;

@Epics({
        @Epic("Reqres")
})
@Stories({
        @Story("Create user")
})
public class ReqresCreateUserTest extends AbstractBaseTest {
    private static final int CREATED = 201;

    @Test
    @DisplayName("Reqres :: create user :: positive test")
    void createUser_positiveTest() {
        // given
        var userData = ReqresTestDataFactory.generateUserData();

        // when
        var updateUserResponse = reqresApiClient.createUser(userData);

        // then
        CreateUserCustomAssert.assertThat(updateUserResponse)
                .hasStatusCode(CREATED)
                .hasUserData(userData)
                .hasIdNotNull()
                .hasCreatedAtNotNull();
    }
}
