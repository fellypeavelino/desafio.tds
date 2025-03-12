/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.dtos;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class ShortUrlDTO {
    private Long id;
    private String originalUrl;
    private String shortCode;
    private Long accessCount = 0L;
    private LocalDateTime createdAt;
    private Long usuario_id;
}
