import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonPlaceholderApiTests {

  @BeforeAll
  public static void setup() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
  }

  //[test 1]: when a GET request is sent to /albums endpoint, verify that an entry with the title "omnis laborum odio" exists.
  @Test
  public void testAlbumsContainsOmnisLaborumOdio() {
    given()
            .when()
            .get("/albums").then()
            .body("title", hasItem("omnis laborum odio"));
  }
  //[test 2]: when a GET request is sent to /comments endpoint, verify that the returned JSON response contains at least 200 comments.
  @Test
  public void testCommentsMinimumCount() {
    Response response = given()
            .when()
            .get("/comments")
            .then()
            .extract().response();

    List<Object> comments = response.jsonPath().getList("$");
    assertTrue(comments.size() >= 200, "Expected at least 200 comments, but found " + comments.size());
  }

  //[test 3]: when a GET request is sent to /users endpoint, verify that a user named "Ervin Howell" with username "Antonette" and zipcode of "90566-7771" exists.
  @Test
  public void testUserErvinHowellExists() {
    given()
            .when()
            .get("/users")
            .then()
            .body("find { it.name == 'Ervin Howell' }.username", equalTo("Antonette"))
            .body("find { it.name == 'Ervin Howell' }.address.zipcode", equalTo("90566-7771"));
  }

  //[test 4]: when a GET request is sent to /comments endpoint, verify that there are comments from people whose email address end in .biz.
  @Test
  public void testCommentsWithBizEmail() {
    Response response = given()
            .when()
            .get("/comments")
            .then()
            .extract().response();

    List<String> emails = response.jsonPath().getList("email");
    long bizCount = emails.stream()
            .filter(email -> email.endsWith(".biz"))
            .count();

    assertTrue(bizCount > 0, "No comments found with .biz email addresses");
  }

  //[test 5]: Create a new post by sending a http POST request to /posts endpoint with this info:
  @Test
  public void testCreateNewPost() {
    String requestBody = "{\n" +
            "  \"userId\": 11,\n" +
            "  \"id\": 101,\n" +
            "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
            "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
            "}";

    given()
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("userId", equalTo(11))
            .body("id", equalTo(101))
            .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
            .body("body", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
  }
}