package com.NotasPersonales.Notas_Personales.Controllers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.CuadernoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cuadernos")
public class CuadernoController {
    @Autowired
    CuadernoService cuadernoService;

    @GetMapping
    public List<CuadernoRespuestaDTO> filtraPorEstado (@RequestParam(defaultValue = "TODOS", required = false)Estado estado){
        return cuadernoService.filtrarPorEstado(estado);
    }

    @GetMapping("/{id}")
    public List<CuadernoRespuestaDTO> filtrarPorUsuario(@PathVariable UUID id, @RequestParam(defaultValue = "TODOS",required = false)Estado estado){
        return cuadernoService.filtrarPorUsuario(id,estado);
    }

    @PostMapping("/create")
    public ResponseEntity<CuadernoRespuestaDTO> create (@Valid @RequestBody CuadernoPostDTO dto){
        return cuadernoService.registrar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuadernoRespuestaDTO> update (@PathVariable UUID id, @Valid @RequestBody CuadernoUpdateDTO dto){
        return cuadernoService.actualizar(id,dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CuadernoRespuestaDTO> reactivate (@PathVariable UUID id){
        return cuadernoService.reactivar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CuadernoRespuestaDTO> delete (@PathVariable UUID id){
        return cuadernoService.eliminar(id);
    }
}
