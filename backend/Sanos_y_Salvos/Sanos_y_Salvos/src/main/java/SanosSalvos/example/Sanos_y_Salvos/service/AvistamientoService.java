package SanosSalvos.example.Sanos_y_Salvos.service;

import SanosSalvos.example.Sanos_y_Salvos.model.Avistamiento;
import SanosSalvos.example.Sanos_y_Salvos.model.Mascota;
import SanosSalvos.example.Sanos_y_Salvos.repository.AvistamientoRepository;
import SanosSalvos.example.Sanos_y_Salvos.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvistamientoService {

    private final AvistamientoRepository avistamientoRepository;
    private final MascotaRepository mascotaRepository;

    public AvistamientoService(
            AvistamientoRepository avistamientoRepository,
            MascotaRepository mascotaRepository
    ) {
        this.avistamientoRepository = avistamientoRepository;
        this.mascotaRepository = mascotaRepository;
    }

    public List<Avistamiento> listarAvistamientos() {
        return avistamientoRepository.findAll();
    }

    public Avistamiento guardarAvistamiento(Integer mascotaId, Avistamiento avistamiento) {
        Mascota mascota = mascotaRepository.findById(mascotaId).orElse(null);

        if (mascota == null) {
            return null;
        }

        avistamiento.setMascota(mascota);
        return avistamientoRepository.save(avistamiento);
    }

    public boolean eliminarAvistamiento(Integer id) {
        if (avistamientoRepository.existsById(id)) {
            avistamientoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}