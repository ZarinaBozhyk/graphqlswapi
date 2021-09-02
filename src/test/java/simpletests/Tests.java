package simpletests;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 8/4/2021
 * #Comments:
 */
public class Tests {
    private static final String GRAPHQL_ENDPOINT = "https://api.spacex.land/graphql/";
    private final GraphQLQuery query = new GraphQLQuery();
    private final UserData userData = new UserData();
    private final QueryLimit queryLimit = new QueryLimit();

    @Test
    public void getAllRocketNames() {
        query.setQuery(Queries.GET_ALL_ROCKETS_NAMES.toString());
        given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when().log().all()
                .post(GRAPHQL_ENDPOINT)
                .then().log().all()
                .assertThat().statusCode(200)
                .and().body("data.rockets[0].name", equalTo("Falcon 1"))
                .and().body("data.rockets[3].type", equalTo("rocket"));
    }

    @Test
    public void insertUser() {
        query.setQuery(Queries.CREATE_USER.toString());
        userData.setName("Ian Gillan");
        userData.setRocket("Deep Purple");
        query.setVariables(userData);
        given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when().log().all()
                .post(GRAPHQL_ENDPOINT)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void getAllUsers() {
        query.setQuery(Queries.GET_ALL_USERS_NAMES.toString());
        given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when().log().all()
                .post(GRAPHQL_ENDPOINT)
                .then().log().all()
                .assertThat().statusCode(200)
                .and().body("data.users[8].name", equalTo("Ian Gillan"))
                .and().body("data.users[8].rocket", equalTo("Deep Purple"));
    }

    @Test
    public void deleteUser() {
        query.setQuery(Queries.DELETE_USER.toString());
        userData.setName("Ian Gillan");
        query.setVariables(userData);
        given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when().log().all()
                .post(GRAPHQL_ENDPOINT)
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void getLimitedLanpads() {
        query.setQuery(Queries.GET_LIMITED_LANDPADS.toString());
        queryLimit.setLimit(3);
        query.setVariables(queryLimit);
        given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when().log().all()
                .post(GRAPHQL_ENDPOINT)
                .then().log().all()
                .assertThat().statusCode(200)
                .and().body("data.landpads[2].id", equalTo("LZ-4"));
    }

    ///////////////Example with DataProvider
    @DataProvider
    public Object[][] getMissions() {
        return new Object[][]{{"3"}};
    }

    @Test(dataProvider = "getMissions")
    public void getAllMissions(String limit) {
        query.setQuery("{\n" +
                "  missions(limit: " + limit + ") {\n" +
                "    name\n" +
                "    wikipedia\n" +
                "  }\n" +
                "}\n");
        given().log().all()
                .contentType(ContentType.JSON)
                .body(query)
                .when().log().all()
                .post(GRAPHQL_ENDPOINT)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}