package SanosSalvos.example.Sanos_y_Salvos.service;

import SanosSalvos.example.Sanos_y_Salvos.model.Mascota;
import SanosSalvos.example.Sanos_y_Salvos.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> buscarPorId(Integer id) {
        return mascotaRepository.findById(id);
    }

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizarMascota(Integer id, Mascota mascotaActualizada) {
        return mascotaRepository.findById(id).map(mascota -> {
            mascota.setNombre(mascotaActualizada.getNombre());
            mascota.setTipo_Mascota(mascotaActualizada.getTipo_Mascota());
            mascota.setDescripcion(mascotaActualizada.getDescripcion());
            mascota.setUVV(mascotaActualizada.getUVV());
            mascota.setRegion_usuario(mascotaActualizada.getRegion_usuario());
            mascota.setImagenUrl(mascotaActualizada.getImagenUrl());
            mascota.setRecompensa(mascotaActualizada.getRecompensa());
            return mascotaRepository.save(mascota);
        }).orElse(null);
    }

    public boolean eliminarMascota(Integer id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}