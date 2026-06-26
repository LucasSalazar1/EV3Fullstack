package SanosSalvos.example.Sanos_y_Salvos.service;

import SanosSalvos.example.Sanos_y_Salvos.model.Comuna;
import SanosSalvos.example.Sanos_y_Salvos.model.Region;
import SanosSalvos.example.Sanos_y_Salvos.repository.ComunaRepository;
import SanosSalvos.example.Sanos_y_Salvos.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunaService {

    private final ComunaRepository comunaRepository;
    private final RegionRepository regionRepository;

    public ComunaService(ComunaRepository comunaRepository, RegionRepository regionRepository) {
        this.comunaRepository = comunaRepository;
        this.regionRepository = regionRepository;
    }

    public List<Comuna> listarComunas() {
        return comunaRepository.findAll();
    }

    public Comuna guardarComuna(Long regionId, Comuna comuna) {
        Region region = regionRepository.findById(regionId).orElse(null);

        if (region == null) {
            return null;
        }

        comuna.setRegion(region);
        return comunaRepository.save(comuna);
    }
}