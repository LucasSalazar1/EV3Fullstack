package SanosSalvos.example.Sanos_y_Salvos.service;

import SanosSalvos.example.Sanos_y_Salvos.model.Region;
import SanosSalvos.example.Sanos_y_Salvos.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> listarRegiones() {
        return regionRepository.findAll();
    }

    public Region guardarRegion(Region region) {
        return regionRepository.save(region);
    }
}