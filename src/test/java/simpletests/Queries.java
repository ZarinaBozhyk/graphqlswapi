package simpletests;

import lombok.AllArgsConstructor;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 8/4/2021
 * #Comments:
 */
@AllArgsConstructor
public enum Queries {
    GET_ALL_ROCKETS_NAMES("{\n" +
            "  rockets {\n" +
            "    name\n" +
            "    type\n" +
            "  }\n" +
            "}"),

    CREATE_USER("mutation createUser($name: String!, $rocket: String!){\n" +
            "  insert_users(objects: {name: $name, rocket: $rocket}) {\n" +
            "    returning {\n" +
            "      id\n" +
            "    }\n" +
            "  }\n" +
            "}\n"),

    GET_ALL_USERS_NAMES("{\n" +
            "  users {\n" +
            "    name\n" +
            "    rocket\n" +
            "  }\n" +
            "}\n"),

    DELETE_USER("mutation deleteUser($name: String!) {\n" +
            "  delete_users(where: {name: {_eq: $name}}) {\n" +
            "    returning {\n" +
            "      timestamp\n" +
            "    }\n" +
            "  }\n" +
            "}\n"),

    GET_LIMITED_LANDPADS("query getLandPads($limit: Int!) {\n" +
            "  landpads(limit: $limit) {\n" +
            "    id\n" +
            "  }\n" +
            "}\n");

    private final String query;

    @Override
    public String toString() {
        return query;
    }
}
