/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.dtos;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class UsuarioPaginadosDTO {
    private List<UsuarioDTO> usuarioDto;
    private RequestPageDTO param;
    private Long total;
}
