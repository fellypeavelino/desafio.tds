/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.controllers;

import com.desafio.tds.Desafio.TDS.dtos.RequestPageDTO;
import com.desafio.tds.Desafio.TDS.dtos.ShortUrlDTO;
import com.desafio.tds.Desafio.TDS.dtos.ShortUrlPaginadoDTO;
import com.desafio.tds.Desafio.TDS.services.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class ShortUrlController {
    
    private final ShortUrlService service;
    
    @PostMapping
    public ResponseEntity<String> createShortUrl(@RequestBody @Valid ShortUrlDTO shortUrlDto) {
        String shortCode = service.shortenUrl(shortUrlDto);
        return ResponseEntity.ok(shortCode);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String originalUrl = service.getOriginalUrl(shortCode);
        response.sendRedirect(originalUrl);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable String shortCode) {
        return ResponseEntity.ok(service.getStats(shortCode));
    }
    
    @PostMapping("/paginacao/{usuario_id}")
    public ShortUrlPaginadoDTO getShortUrlPaginadosEOrdenadosPorQuery(
        @Valid @RequestBody RequestPageDTO dto, @PathVariable Long usuario_id
    ) {
        return service.getShortUrlPaginadosEOrdenadosPorQuery(dto, usuario_id);
    }
}
