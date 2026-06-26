package SanosSalvos.example.Sanos_y_Salvos.controller;

import SanosSalvos.example.Sanos_y_Salvos.model.Region;
import SanosSalvos.example.Sanos_y_Salvos.service.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regiones")
@CrossOrigin(origins = "*")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> listarRegiones() {
        return regionService.listarRegiones();
    }

    @PostMapping
    public Region crearRegion(@RequestBody Region region) {
        return regionService.guardarRegion(region);
    }
}