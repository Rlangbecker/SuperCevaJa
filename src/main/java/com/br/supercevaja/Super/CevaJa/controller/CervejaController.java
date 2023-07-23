package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.controller.documentationInterface.CervejaControllerInterface;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.service.CervejaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supercevaja/api/v1/cervejas")
public class CervejaController implements CervejaControllerInterface {

    private final CervejaService cervejaService;

    @PostMapping
    public ResponseEntity<CervejaDto> cadastrarCerveja(@RequestBody CervejaCreateDto cervejaCreateDto) throws RegraDeNegocioException {
        return new ResponseEntity<>(cervejaService.cadastrarCerveja(cervejaCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{idCerveja}")
    public ResponseEntity<CervejaDto> buscarPorId(@PathVariable("idCerveja") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(cervejaService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CervejaDto>> retornarTiposCeva() {
        return new ResponseEntity<>(cervejaService.retornarTiposCerveja(), HttpStatus.OK);
    }

    @PutMapping("/tipo/{nomeCerveja}")
    public ResponseEntity<CervejaDto> alterarPorNome(@PathVariable("nomeCerveja") String nomeCerveja, @RequestBody CervejaCreateDto cervejaCreateDto) throws RegraDeNegocioException {
        return new ResponseEntity<>(cervejaService.alterarPorNome(nomeCerveja, cervejaCreateDto), HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{nomeCerveja}")
    public ResponseEntity deletar(@PathVariable("nomeCerveja") String nomeCerveja) throws Exception {
        cervejaService.deletarPorNome(nomeCerveja);
        return ResponseEntity.accepted().build();
    }
}
