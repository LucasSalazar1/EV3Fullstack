package SanosSalvos.example.Sanos_y_Salvos.service;

import SanosSalvos.example.Sanos_y_Salvos.model.Usuario;
import SanosSalvos.example.Sanos_y_Salvos.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;

    @BeforeEach
    void setUp() {
        usuario1 = new Usuario(1L, "Juan Perez", "juan@gmail.com", "123456", "Usuario", null);
        usuario2 = new Usuario(2L, "Maria Lopez", "maria@gmail.com", "abcdef", "Administrador", null);
        usuario3 = new Usuario(3L, "Pedro Soto", "pedro@gmail.com", "qwerty", "Veterinario", null);
    }

    @Test
    void testListarUsuarios() {
        List<Usuario> listaSimulada = Arrays.asList(usuario1, usuario2, usuario3);
        when(usuarioRepository.findAll()).thenReturn(listaSimulada);

        List<Usuario> resultado = usuarioService.listarUsuarios();

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre());
        assertEquals("Maria Lopez", resultado.get(1).getNombre());
        assertEquals("Pedro Soto", resultado.get(2).getNombre());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Existe() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario1));

        Optional<Usuario> resultado = usuarioService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan Perez", resultado.get().getNombre());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NoExiste() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Usuario> resultado = usuarioService.buscarPorId(99L);

        assertFalse(resultado.isPresent());
        verify(usuarioRepository, times(1)).findById(99L);
    }

    @Test
    void testGuardarUsuario() {
        when(usuarioRepository.save(usuario1)).thenReturn(usuario1);

        Usuario resultado = usuarioService.guardarUsuario(usuario1);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
        verify(usuarioRepository, times(1)).save(usuario1);
    }
}
