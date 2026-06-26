package SanosSalvos.example.Sanos_y_Salvos.controller;

import SanosSalvos.example.Sanos_y_Salvos.model.Comuna;
import SanosSalvos.example.Sanos_y_Salvos.service.ComunaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunas")
@CrossOrigin(origins = "*")
public class ComunaController {

    private final ComunaService comunaService;

    public ComunaController(ComunaService comunaService) {
        this.comunaService = comunaService;
    }

    @GetMapping
    public List<Comuna> listarComunas() {
        return comunaService.listarComunas();
    }

    @PostMapping("/{regionId}")
    public ResponseEntity<Comuna> crearComuna(
            @PathVariable Long regionId,
            @RequestBody Comuna comuna
    ) {
        Comuna nuevaComuna = comunaService.guardarComuna(regionId, comuna);

        if (nuevaComuna == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(nuevaComuna);
    }
}