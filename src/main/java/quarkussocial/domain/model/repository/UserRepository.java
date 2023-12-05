package quarkussocial.domain.model.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import quarkussocial.domain.model.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
