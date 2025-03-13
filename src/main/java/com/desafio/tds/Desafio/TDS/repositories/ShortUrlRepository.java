/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.repositories;

import com.desafio.tds.Desafio.TDS.entities.ShortUrl;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortCode(String shortCode);
    
    @Query(
        "SELECT s FROM ShortUrl s WHERE s.usuario.id = :usuarioId AND " +
        "( LOWER(s.originalUrl) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(s.shortCode) LIKE LOWER(CONCAT('%', :term, '%')) ) " +
        "ORDER BY s.id DESC " 
    )
    Page<ShortUrl> findPageByFiltro(@Param("term") String term, @Param("usuarioId") Long usuarioId, Pageable pageable);

    @Query(
        "SELECT s FROM ShortUrl s WHERE s.usuario.id = :usuarioId AND " +
        "( LOWER(s.originalUrl) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
        "LOWER(s.shortCode) LIKE LOWER(CONCAT('%', :term, '%')) ) " +
        "ORDER BY s.id DESC "
    )
    List<ShortUrl> findFiltro(@Param("term") String term, @Param("usuarioId") Long usuarioId);
    
    @Query("SELECT s FROM ShortUrl s WHERE s.usuario.id = :usuarioId")
    Page<ShortUrl> findPage(Pageable pageable, @Param("usuarioId") Long usuarioId);
}
