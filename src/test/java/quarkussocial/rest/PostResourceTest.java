package quarkussocial.rest;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quarkussocial.persistence.dto.CreatePostRequest;
import quarkussocial.persistence.model.User;
import quarkussocial.persistence.model.repository.UserRepository;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(PostResource.class)
class PostResourceTest {

    @Inject
    UserRepository userRepository;
    Long userId;

    @BeforeEach
    @Transactional
    public void setUp(){
        var user = new User();
        user.setName("Fulano");
        user.setAge(30);

        userRepository.persist(user);
        userId = user.getId();
    }

    @Test
    @DisplayName("Deve criar um post para o usuário")
    public void createPostTest(){

        var postRequest = new CreatePostRequest();
        postRequest.setText("Txt");

        var userId = 1;

        given().contentType(ContentType.JSON).body(postRequest).pathParam("userId", userId).when().post().then().statusCode(201);

    }


}