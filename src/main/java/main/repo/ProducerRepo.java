package main.repo;

import main.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProducerRepo extends JpaRepository<Producer, UUID> {
}
