package org.truenvy.reqres.rest.models.responses.users.get;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GetListOfUsersResponse(
        Integer page,
        @JsonProperty("per_page") Integer perPage,
        Integer total,
        @JsonProperty("total_pages") Integer totalPages,
        List<UserData> data,
        Support support
) {
}
