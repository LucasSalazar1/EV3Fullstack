package SanosSalvos.example.Sanos_y_Salvos.repository;

import SanosSalvos.example.Sanos_y_Salvos.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}