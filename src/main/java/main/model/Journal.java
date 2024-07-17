package main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "journals")
@NoArgsConstructor
@AllArgsConstructor
public class Journal {

    @Id
    @Column(name = "journal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
}
