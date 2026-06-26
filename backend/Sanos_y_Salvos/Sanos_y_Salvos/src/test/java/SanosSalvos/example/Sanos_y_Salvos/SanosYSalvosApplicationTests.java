package SanosSalvos.example.Sanos_y_Salvos;

import SanosSalvos.example.Sanos_y_Salvos.model.Mascota;
import SanosSalvos.example.Sanos_y_Salvos.model.Usuario;
import SanosSalvos.example.Sanos_y_Salvos.repository.MascotaRepository;
import SanosSalvos.example.Sanos_y_Salvos.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SanosYSalvosApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(SanosYSalvosApplicationTests.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MascotaRepository mascotaRepository;

	@Test
	void contextLoads() {
		log.info("=========================================================");
		log.info("========== INICIANDO PRUEBA DE INTEGRACIÓN LOCAL ========");
		log.info("=========================================================");

		log.info("--- LISTADO DE USUARIOS REGISTRADOS EN BD LOCAL ---");
		List<Usuario> usuarios = usuarioRepository.findAll();
		if (usuarios.isEmpty()) {
			log.info("No se encontraron usuarios en la base de datos MySQL local.");
		} else {
			usuarios.forEach(u -> log.info("  >> USUARIO - ID: {}, Nombre: {}, Correo: {}, Rol: {}", 
					u.getId(), u.getNombre(), u.getCorreo(), u.getRol()));
		}

		log.info("--- LISTADO DE MASCOTAS REGISTRADAS EN BD LOCAL ---");
		List<Mascota> mascotas = mascotaRepository.findAll();
		if (mascotas.isEmpty()) {
			log.info("No se encontraron mascotas en la base de datos MySQL local.");
		} else {
			mascotas.forEach(m -> log.info("  >> MASCOTA - ID: {}, Nombre: {}, Tipo: {}, Recompensa: {}", 
					m.getIdMascota(), m.getNombre(), m.getTipo_Mascota(), m.getRecompensa()));
		}

		log.info("=========================================================");
		log.info("========== FINALIZANDO PRUEBA DE INTEGRACIÓN LOCAL ======");
		log.info("=========================================================");
	}

}
