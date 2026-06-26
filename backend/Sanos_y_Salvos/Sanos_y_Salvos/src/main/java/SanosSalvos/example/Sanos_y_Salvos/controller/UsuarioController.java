package SanosSalvos.example.Sanos_y_Salvos.controller;

import SanosSalvos.example.Sanos_y_Salvos.model.Mascota;
import SanosSalvos.example.Sanos_y_Salvos.model.Usuario;
import SanosSalvos.example.Sanos_y_Salvos.service.MascotaService;
import SanosSalvos.example.Sanos_y_Salvos.service.UsuarioService;
import SanosSalvos.example.Sanos_y_Salvos.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final MascotaService mascotaService;

    public UsuarioController(UsuarioService usuarioService, MascotaService mascotaService) {
        this.usuarioService = usuarioService;
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public ApiResponse<List<Usuario>> listarUsuarios() {
        return new ApiResponse<>(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(new ApiResponse<>(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuario
    ) {

        Usuario actualizado =
                usuarioService.actualizarUsuario(id, usuario);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {

        boolean eliminado =
                usuarioService.eliminarUsuario(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{usuarioId}/mascotas")
    public ResponseEntity<Mascota> agregarMascotaAlUsuario(
            @PathVariable Long usuarioId,
            @RequestBody Mascota mascota
    ) {
        return usuarioService.buscarPorId(usuarioId).map(usuario -> {
            mascota.setUsuario(usuario);
            Mascota guardada = mascotaService.guardarMascota(mascota);
            return ResponseEntity.ok(guardada);
        }).orElse(ResponseEntity.notFound().build());
    }
}