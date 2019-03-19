package br.com.xyinc.controllers;

import br.com.xyinc.models.Poi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;


@Controller
@RequestMapping("/poi")
public interface PoiController {

    @GetMapping
    @ApiOperation(value = "Busca todos",
            notes = "Este endpoint é responsável por realizar a busca de todos os Pontos de interesse cadastrados na base",
            response = Poi.class)
    @ResponseBody
    List<Poi> list();

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca por ID",
            notes = "Este endpoint é responsável por realizar a busca de um Pontos de interesse, por seu ID",
            response = Poi.class)
    @ResponseBody
    Poi findById(Long id);

    @ApiOperation(value = "Busca por ID",
            notes = "Este endpoint é responsável por cadastrar um ponto de interesse",
            response = Poi.class)
    @PostMapping
    ResponseEntity<Poi> add(Poi poi);

    @GetMapping("/proximidade/{x}/{y}/{s}")
    @ApiOperation(value = "Busca pontos de interesse proximos",
            notes = "Este endpoint é responsável por realizar a busca de pontos de interesses na proximidade, de acordo com coordenadas e distância inrmados",
            response = Poi.class)
    @ResponseBody
    List<Poi> buscaProximos(Integer x, Integer y, Integer s);
}
