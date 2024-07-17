package main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "producers")
@NoArgsConstructor
@AllArgsConstructor
public class Producer {

    @Id
    @Column(name = "producer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
}
