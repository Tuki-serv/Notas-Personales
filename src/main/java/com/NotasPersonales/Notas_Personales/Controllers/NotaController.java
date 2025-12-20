package com.NotasPersonales.Notas_Personales.Controllers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.ContenidoNotaDTOs.ContenidoNotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.ContenidoNotaDTOs.ContenidoNotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.NotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    NotaService notaService;

    @GetMapping
    public List<NotaRespuestaDTO> filtrarPorEstado(@RequestParam(defaultValue = "TODOS", required = false)Estado estado){
        return notaService.filtrarPorEstado(estado);
    }

    @GetMapping("/{id}")
    public List<NotaRespuestaDTO> filtraPorCuaderno(@PathVariable UUID id, @RequestParam(defaultValue = "TODOS",required = false) Estado estado){
        return notaService.filtraPorCuaderno(id,estado);
    }

    @GetMapping("/id")
    public ResponseEntity<ContenidoNotaRespuestaDTO> obtenerContenido (@PathVariable UUID id){
        return notaService.obtenerContenido(id);
    }

    @PostMapping("/create")
    public ResponseEntity<NotaRespuestaDTO> create (@Valid @RequestBody NotaPostDTO dto){
        return notaService.registrar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaRespuestaDTO> update (@PathVariable UUID id, @Valid @RequestBody NotaUpdateDTO dto){
        return notaService.actualizar(id,dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContenidoNotaRespuestaDTO> actualizarContenido (@PathVariable UUID id, @Valid @RequestBody ContenidoNotaUpdateDTO dto){
        return notaService.actualizarContenido(id,dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NotaRespuestaDTO> reactivate (@PathVariable UUID id){
        return notaService.reactivar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NotaRespuestaDTO> delete (@PathVariable UUID id){
        return notaService.eliminar(id);
    }

}
