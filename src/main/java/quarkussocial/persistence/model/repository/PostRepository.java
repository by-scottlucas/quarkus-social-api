package quarkussocial.persistence.model.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import quarkussocial.persistence.model.Post;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {}
