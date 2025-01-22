package org.truenvy.reqres.data.factory;

import com.github.javafaker.Faker;
import org.truenvy.reqres.configs.ConfigProvider;
import org.truenvy.reqres.rest.models.requests.users.UserRequest;

public class ReqresTestDataFactory {
    private static final Faker faker = ConfigProvider.INSTANCE.getFaker();

    private ReqresTestDataFactory() {
    }

    public static UserRequest generateUserData() {
        return new UserRequest(
                faker.name().firstName(),
                faker.job().title()
        );
    }
}
