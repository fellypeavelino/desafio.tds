/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.utils;

import com.desafio.tds.Desafio.TDS.dtos.ShortUrlDTO;
import com.desafio.tds.Desafio.TDS.dtos.UsuarioDTO;
import com.desafio.tds.Desafio.TDS.entities.ShortUrl;
import com.desafio.tds.Desafio.TDS.entities.Usuarios;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Usuario
 */
@Component
public class ConvertUtil {
    public UsuarioDTO convertToDto(Usuarios usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuarios convertToEntity(UsuarioDTO usuarioDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioDto, Usuarios.class);
    }
    
    public ShortUrlDTO convertToDto(ShortUrl shortUrl) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shortUrl, ShortUrlDTO.class);
    }

    public ShortUrl convertToEntity(ShortUrlDTO shortUrlDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shortUrlDto, ShortUrl.class);
    } 
}

