package main.repo;

import main.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepo extends JpaRepository<Game, UUID> {
}
