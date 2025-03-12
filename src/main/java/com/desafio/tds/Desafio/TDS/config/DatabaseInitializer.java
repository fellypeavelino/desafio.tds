/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.desafio.tds.Desafio.TDS.config;

import com.desafio.tds.Desafio.TDS.entities.Usuarios;
import com.desafio.tds.Desafio.TDS.repositories.UsuariosRepository;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Usuario
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    
    @Autowired
    private UsuariosRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
                Usuarios defaultUsuario = new Usuarios();
                defaultUsuario.setLoguin("admin");
                defaultUsuario.setSenha("admin123");
                usuarioRepository.save(defaultUsuario);
                logger.info("Usuário padrão inserido no banco de dados.");
        } else {
                logger.warn("Usuários já existentes no banco de dados.");
        }
    }
}
