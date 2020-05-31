package tests.APITests;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GitHubAPItests {

    private String date;
    private String uniqueID;
    private String token = "Basic aXZvbGtvZmY1NTY2OjIwMjBTT0ZUc2VydmUvb25l";
    private String email = "volkoff5566@gmail.com";
    private final String baseURI = "https://api.github.com";
    private RequestSpecification requestSpec;


    @BeforeClass
    public void settingUp() {
        Date d = new Date();
        long dateLong = d.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(dateLong);
        uniqueID = d.getTime() + "";

        requestSpec = given()
                .baseUri(baseURI)
                .header("Authorization", token);
    }

    @Test(description = "Verify user's login is as expected.")
    public void userLoginVerify() {
        requestSpec.when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("login", equalTo("ivolkoff5566"));
    }

    @Test(description = "Creating new repo using API.")
    public void createNewRepoWithAPI() {
        Map<String,Object> repoInfo = new HashMap<>();
        repoInfo.put("name", ("APITest" + uniqueID));
        repoInfo.put("auto_init", true);
        repoInfo.put("private", false);
        repoInfo.put("gitignore_template", "nanoc");

        requestSpec.contentType("application/json")
                .body(repoInfo)
                .post("/user/repos")
                .then()
                .statusCode(201)
                .body("name", equalTo("APITest" + uniqueID));
    }
}
