package io.getarrays.userservice.repo;

import io.getarrays.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
