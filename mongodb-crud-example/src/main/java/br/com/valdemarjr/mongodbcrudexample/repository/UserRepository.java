package br.com.valdemarjr.mongodbcrudexample.repository;

import br.com.valdemarjr.mongodbcrudexample.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findById(String id);
}
