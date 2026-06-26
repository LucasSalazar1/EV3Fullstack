package SanosSalvos.example.Sanos_y_Salvos.service;

import SanosSalvos.example.Sanos_y_Salvos.model.Mascota;
import SanosSalvos.example.Sanos_y_Salvos.repository.MascotaRepository;
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
public class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @InjectMocks
    private MascotaService mascotaService;

    private Mascota mascota1;
    private Mascota mascota2;
    private Mascota mascota3;

    @BeforeEach
    void setUp() {
        mascota1 = new Mascota(1, "Firulais", "Perro", "Perro de color cafe", "UVV1", "Metropolitana", "http://image1.com", 5000, null);
        mascota2 = new Mascota(2, "Copito", "Gato", "Gato blanco esponjoso", "UVV2", "Valparaiso", "http://image2.com", 10000, null);
        mascota3 = new Mascota(3, "Luna", "Loro", "Loro verde que habla mucho", "UVV3", "Biobio", "http://image3.com", 15000, null);
    }

    @Test
    void testListarMascotas() {
        List<Mascota> listaSimulada = Arrays.asList(mascota1, mascota2, mascota3);
        when(mascotaRepository.findAll()).thenReturn(listaSimulada);

        List<Mascota> resultado = mascotaService.listarMascotas();

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Firulais", resultado.get(0).getNombre());
        assertEquals("Copito", resultado.get(1).getNombre());
        assertEquals("Luna", resultado.get(2).getNombre());
        verify(mascotaRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Existe() {
        when(mascotaRepository.findById(1)).thenReturn(Optional.of(mascota1));

        Optional<Mascota> resultado = mascotaService.buscarPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals("Firulais", resultado.get().getNombre());
        verify(mascotaRepository, times(1)).findById(1);
    }

    @Test
    void testBuscarPorId_NoExiste() {
        when(mascotaRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Mascota> resultado = mascotaService.buscarPorId(99);

        assertFalse(resultado.isPresent());
        verify(mascotaRepository, times(1)).findById(99);
    }

    @Test
    void testGuardarMascota() {
        when(mascotaRepository.save(mascota1)).thenReturn(mascota1);

        Mascota resultado = mascotaService.guardarMascota(mascota1);

        assertNotNull(resultado);
        assertEquals("Firulais", resultado.getNombre());
        verify(mascotaRepository, times(1)).save(mascota1);
    }
}
