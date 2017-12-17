package se.woolrich.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.woolrich.demo.entities.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {


     public List<User> findByUuid(String uuid);

}
