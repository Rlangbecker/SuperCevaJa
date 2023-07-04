package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.dto.CervejaCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.CervejaDto;
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
    public ResponseEntity<CervejaDto> cadastrarCerveja(@RequestBody CervejaCreateDto cervejaCreateDto){
        return new ResponseEntity<>(cervejaService.cadastrarCerveja(cervejaCreateDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CervejaDto> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity<>(cervejaService.buscarPorId(id), HttpStatus.OK);
    }
}
