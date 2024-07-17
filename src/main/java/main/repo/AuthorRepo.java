package main.repo;

import main.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepo extends JpaRepository<Author, UUID> {
}
