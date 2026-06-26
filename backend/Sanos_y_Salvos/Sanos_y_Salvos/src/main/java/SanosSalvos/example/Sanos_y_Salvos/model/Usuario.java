package SanosSalvos.example.Sanos_y_Salvos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String correo;

    private String password;

    private String rol;
    @ManyToOne
@JoinColumn(name = "comuna_id")
private Comuna comuna;
}