/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.controllers;

import com.desafio.tds.Desafio.TDS.dtos.RequestPageDTO;
import com.desafio.tds.Desafio.TDS.dtos.TokenDTO;
import com.desafio.tds.Desafio.TDS.dtos.UsuarioDTO;
import com.desafio.tds.Desafio.TDS.dtos.UsuarioPaginadosDTO;
import com.desafio.tds.Desafio.TDS.services.UsuariosService;
import com.desafio.tds.Desafio.TDS.utils.JwtUtil;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    
    private final UsuariosService usuarioService;
    
    public UsuariosController(UsuariosService usuarioService) { 
        this.usuarioService = usuarioService; 
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UsuarioDTO usuario) {
        TokenDTO tokenDTO = new TokenDTO();
        try {
            UsuarioDTO usuarioDto = usuarioService.encontrarPorLoguinESenhaDTO(usuario);
            tokenDTO.setUsuarioDto(usuarioDto);
            String tds = (new JwtUtil()).generateToken("tds");
            tokenDTO.setSub(tds);
        } catch (Exception e) {
            return new ResponseEntity<>(tokenDTO, HttpStatus.BAD_GATEWAY);
        }
        return ResponseEntity.ok(tokenDTO);
    }
    
    @GetMapping 
    public List<UsuarioDTO> getAll() { 
        return usuarioService.findDTOAll(); 
    }
    
    @PostMapping 
    public UsuarioDTO create(@RequestBody @Valid UsuarioDTO usuario) { 
        return usuarioService.saveDTO(usuario); 
    }

    @PutMapping("/{id}")  
    public UsuarioDTO update(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuario) { 
        return usuarioService.updateDTO(id, usuario); 
    }
    
    @DeleteMapping("/{id}")  
    public void delete(@PathVariable Long id) { 
        usuarioService.delete(id); 
    }
    
    @GetMapping("/{id}")
    public UsuarioDTO findDTOById(@PathVariable Long id){
        return usuarioService.findDTOById(id);
    }
    
    @PostMapping("/paginacao")
    public UsuarioPaginadosDTO getUsuariosPaginadosEOrdenadosPorQuery(@Valid @RequestBody RequestPageDTO dto) {
        return usuarioService.getUsuariosPaginadosEOrdenadosPorQuery(dto);
    }
}
