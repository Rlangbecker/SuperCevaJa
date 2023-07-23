package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.controller.documentationInterface.HealthControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supercevaja/api/v1/health")
@CrossOrigin("*")
public class HealthController implements HealthControllerInterface {

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Serviço rodando!");
    }
}
