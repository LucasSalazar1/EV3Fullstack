package SanosSalvos.example.Sanos_y_Salvos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "avistamientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avistamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvistamiento;

    @Column(nullable = false)
    private String lugar;

    @Column(nullable = false)
    private String fecha;

    private String comentario;

    private String nombreReportante;

    private String contactoReportante;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;
}