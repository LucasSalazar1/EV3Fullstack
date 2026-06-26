package SanosSalvos.example.Sanos_y_Salvos.controller;

import SanosSalvos.example.Sanos_y_Salvos.model.Mascota;
import SanosSalvos.example.Sanos_y_Salvos.service.MascotaService;
import SanosSalvos.example.Sanos_y_Salvos.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public ApiResponse<List<Mascota>> listarMascotas() {
        return new ApiResponse<>(mascotaService.listarMascotas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Mascota>> buscarPorId(@PathVariable Integer id) {
        return mascotaService.buscarPorId(id)
                .map(mascota -> ResponseEntity.ok(new ApiResponse<>(mascota)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        return mascotaService.guardarMascota(mascota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(
            @PathVariable Integer id,
            @RequestBody Mascota mascota
    ) {
        Mascota actualizada = mascotaService.actualizarMascota(id, mascota);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Integer id) {
        boolean eliminada = mascotaService.eliminarMascota(id);

        if (!eliminada) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}