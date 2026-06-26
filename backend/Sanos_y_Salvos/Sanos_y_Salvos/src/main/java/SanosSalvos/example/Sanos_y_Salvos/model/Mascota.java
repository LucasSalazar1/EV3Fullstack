package SanosSalvos.example.Sanos_y_Salvos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMascota;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo_Mascota;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String UVV;

    @Column(nullable = false)
    private String region_usuario;
    private String imagenUrl;

    @Column(nullable = false)
    private Integer recompensa;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;
}