/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.services;

import com.desafio.tds.Desafio.TDS.dtos.ShortUrlDTO;
import com.desafio.tds.Desafio.TDS.entities.ShortUrl;
import com.desafio.tds.Desafio.TDS.entities.Usuarios;
import com.desafio.tds.Desafio.TDS.repositories.ShortUrlRepository;
import com.desafio.tds.Desafio.TDS.repositories.UsuariosRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@RequiredArgsConstructor
public class ShortUrlService {
    
    private final ShortUrlRepository repository;
    private final UsuariosRepository usuariosRepository;
    
    public String shortenUrl(ShortUrlDTO shortUrlDto) {
        String shortCode = generateShortCode();
        Optional<Usuarios> usuario = usuariosRepository.findById(shortUrlDto.getUsuario_id());
        if(usuario.isPresent()){
            ShortUrl shortUrl = new ShortUrl(null, shortUrlDto.getOriginalUrl(), shortCode, 0L, LocalDateTime.now(), usuario.get());
            repository.save(shortUrl);
        }

        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));
        shortUrl.setAccessCount(shortUrl.getAccessCount() + 1);
        repository.save(shortUrl);
        return shortUrl.getOriginalUrl();
    }
    
    public Map<String, Object> getStats(String shortCode) {
        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));
        
        long daysSinceCreation = ChronoUnit.DAYS.between(shortUrl.getCreatedAt(), LocalDateTime.now());
        double averagePerDay = daysSinceCreation > 0 ? (double) shortUrl.getAccessCount() / daysSinceCreation : shortUrl.getAccessCount();

        return Map.of(
            "totalAccesses", shortUrl.getAccessCount(),
            "averageAccessPerDay", averagePerDay
        );
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
