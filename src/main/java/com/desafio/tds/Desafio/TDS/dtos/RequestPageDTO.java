/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class RequestPageDTO {
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
    @NotNull
    private String sortBy;
    @NotNull
    private String sortDir;
    private String filtro;
}
