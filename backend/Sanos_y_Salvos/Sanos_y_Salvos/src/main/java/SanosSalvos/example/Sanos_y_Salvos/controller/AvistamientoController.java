package SanosSalvos.example.Sanos_y_Salvos.controller;

import SanosSalvos.example.Sanos_y_Salvos.model.Avistamiento;
import SanosSalvos.example.Sanos_y_Salvos.service.AvistamientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avistamientos")
@CrossOrigin(origins = "*")
public class AvistamientoController {

    private final AvistamientoService avistamientoService;

    public AvistamientoController(AvistamientoService avistamientoService) {
        this.avistamientoService = avistamientoService;
    }

    @GetMapping
    public List<Avistamiento> listarAvistamientos() {
        return avistamientoService.listarAvistamientos();
    }

    @PostMapping("/{mascotaId}")
    public ResponseEntity<Avistamiento> crearAvistamiento(
            @PathVariable Integer mascotaId,
            @RequestBody Avistamiento avistamiento
    ) {
        Avistamiento nuevo = avistamientoService.guardarAvistamiento(mascotaId, avistamiento);

        if (nuevo == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAvistamiento(@PathVariable Integer id) {
        boolean eliminado = avistamientoService.eliminarAvistamiento(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}