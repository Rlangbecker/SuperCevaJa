package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.service.CervejaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supercevaja/api/v1/cerveja")
public class CervejaController {

    private final CervejaService cervejaService;

    @PostMapping
    public ResponseEntity<Cerveja> cadastrarCerveja(Cerveja cerveja){
        return new ResponseEntity<>(cervejaService.cadastrarCerveja(cerveja), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity buscarPorId(@PathVariable("id") Integer id) {
//        return ResponseEntity<>(cervejaService.buscarPorId(id)).get();
//    }
}
