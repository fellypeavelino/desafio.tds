/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.dtos;

import com.desafio.tds.Desafio.TDS.entities.Usuarios;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class TokenDTO {
    private String sub;
    private UsuarioDTO usuarioDto;
}
