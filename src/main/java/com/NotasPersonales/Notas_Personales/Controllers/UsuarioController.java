package com.NotasPersonales.Notas_Personales.Controllers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioLoginDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioRespuestaDTO> filtrarPorEstado (@RequestParam (defaultValue = "TODOS", required = false) Estado estado) {
        return usuarioService.filtrarPorEstado(estado);
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioRespuestaDTO> create(@Valid @RequestBody UsuarioPostDTO dto){
        return usuarioService.registrarUsuario(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioRespuestaDTO> login(@Valid @RequestBody UsuarioLoginDTO dto){
        return usuarioService.login(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> update(@PathVariable UUID id, @Valid @RequestBody UsuarioUpdateDTO dto){
        return usuarioService.actualizar(id,dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> reactivate(@PathVariable UUID id){
        return usuarioService.reactivar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> delete(@PathVariable UUID id){
        return usuarioService.eliminar(id);
    }

}
