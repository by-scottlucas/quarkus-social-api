package quarkussocial.rest;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quarkussocial.persistence.dto.CreatePostRequest;
import quarkussocial.persistence.model.Follower;
import quarkussocial.persistence.model.Post;
import quarkussocial.persistence.model.User;
import quarkussocial.persistence.model.repository.FollowerRepository;
import quarkussocial.persistence.model.repository.PostRepository;
import quarkussocial.persistence.model.repository.UserRepository;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(PostResource.class)
class PostResourceTest {

    @Inject
    PostRepository postRepository;
    FollowerRepository followerRepository;
    UserRepository userRepository;
    Long userId;
    Long userNotFollowerId;
    Long userFollowerId;

    @BeforeEach
    @Transactional
    public void setUp(){
        //Usuário Padrão de Testes
        var user = new User();
        user.setName("Fulano");
        user.setAge(30);
        userId = user.getId();
        userRepository.persist(user);

        // Criada a Postagem para o Usuario
        Post post = new Post();
        post.setText("Hello Man");
        post.setUser(user);
        postRepository.persist(post);

        // Usuario não seguidor
        var userNotFollower = new User();
        userNotFollower.setName("Ciclano");
        userNotFollower.setAge(30);
        userNotFollowerId = userNotFollower.getId();
        userRepository.persist(userNotFollower);

        //Usuario seguidor
        var userFollower = new User();
        userFollower.setName("Ciclano");
        userFollower.setAge(30);
        userFollowerId = userFollower.getId();
        userRepository.persist(userFollower);

        Follower follower = new Follower();
        follower.setUser(user);
        follower.setFollower(userFollower);
        followerRepository.persist(follower);

    }

    @Test
    @DisplayName("Deve criar um post para o usuário")
    public void createPostTest(){

        var postRequest = new CreatePostRequest();
        postRequest.setText("Txt");

        given().contentType(ContentType.JSON).body(postRequest).pathParam("userId", userId).when().post().then().statusCode(201);

    }

    @Test
    @DisplayName("Deve retornar um erro 404 ao tentar criar um post para um usuário inexistente.")
    public void postForANInexistentUserTest(){

        var postRequest = new CreatePostRequest();
        postRequest.setText("Txt");

        var inexistentUserId = 1000;

        given().contentType(ContentType.JSON).body(postRequest).pathParam("userId", inexistentUserId).when().post().then().statusCode(404);

    }

    @Test
    @DisplayName("Deve retornar 404 quando um usuário não existe.")
    public void listPostUserNotFoundTest(){
        var inexistentUserId = 1000;

        given().pathParam("userId", inexistentUserId).when().get().then().statusCode(404);
    }

    @Test
    @DisplayName("Deve retornar 400 quando um id de seguidor não está presente no header.")
    public void listPostFollowerHeaderNotSendTest(){

        given().pathParam("userId", userId).when().get().then().statusCode(400).body(Matchers.is("Você esqueceu o Header followerId"));
    }

    @Test
    @DisplayName("Deve retornar 400 quando um seguidor não existe.")
    public void listPostFollowerNotFoundTest(){
        var inexistentFollowerId = 1000;

        given().pathParam("userId", userId).header("followerId", inexistentFollowerId).when().get().then().statusCode(400).body(Matchers.is("followerId inexistente."));
    }

    @Test
    @DisplayName("Deve retornar 403 quando um seguidor não está seguindo.")
    public void listPostNotAFollowerTest(){

        given().pathParam("userId", userId).header("followerId", userNotFollowerId).when().get().then().statusCode(403).body(Matchers.is("Você não pode ver essas postagens"));

    }

    @Test
    @DisplayName("Deve retornar os posts criados.")
    public void listPostsTest(){

        given().pathParam("userId", userId).header("followerId", userFollowerId).when().get().then().statusCode(200).body("size()", Matchers.is(0));

    }
}