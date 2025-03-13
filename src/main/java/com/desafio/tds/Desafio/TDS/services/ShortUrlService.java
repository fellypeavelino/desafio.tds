/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.services;

import com.desafio.tds.Desafio.TDS.dtos.RequestPageDTO;
import com.desafio.tds.Desafio.TDS.dtos.ShortUrlDTO;
import com.desafio.tds.Desafio.TDS.dtos.ShortUrlPaginadoDTO;
import com.desafio.tds.Desafio.TDS.entities.ShortUrl;
import com.desafio.tds.Desafio.TDS.entities.Usuarios;
import com.desafio.tds.Desafio.TDS.repositories.ShortUrlRepository;
import com.desafio.tds.Desafio.TDS.repositories.UsuariosRepository;
import com.desafio.tds.Desafio.TDS.utils.ConvertUtil;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@RequiredArgsConstructor
public class ShortUrlService {
    
    private final ConvertUtil convertUtil;
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
    
    public ShortUrlPaginadoDTO getShortUrlPaginadosEOrdenadosPorQuery(RequestPageDTO dto, Long usuario_id) {
        ShortUrlPaginadoDTO result = new ShortUrlPaginadoDTO();
        List<ShortUrlDTO> shortsUrlDto = new ArrayList<>();
        result.setParam(dto);

        String sortBy = dto.getSortBy();
        String sortDir = dto.getSortDir();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), sort);

        Page<ShortUrl> page = null;
        long total = 0;
        if (dto.getFiltro() != null && !dto.getFiltro().isEmpty()) {
            page = repository.findPageByFiltro(dto.getFiltro(), usuario_id, pageable);
            total = repository.findFiltro(dto.getFiltro(), usuario_id).size();
        } else {
            page = repository.findPage(pageable, usuario_id);
            total = repository.count();
        }

        shortsUrlDto = this.convertToListDto(page.getContent());
        result.setShortUrlDto(shortsUrlDto);
        result.setTotal(total);
        return result;
    }
    
    private List<ShortUrlDTO> convertToListDto(List<ShortUrl> shortUrls) {
        List<ShortUrlDTO> listResult = new ArrayList<>();
        shortUrls.forEach(c -> listResult.add(convertUtil.convertToDto(c)));
        return listResult;
    }
    
}
