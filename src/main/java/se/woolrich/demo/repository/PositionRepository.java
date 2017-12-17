package se.woolrich.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.woolrich.demo.entities.Position;

public interface PositionRepository extends MongoRepository<Position, String> {

}
