package br.com.xyinc.controllers;

import br.com.xyinc.models.Poi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/poi")
public interface PoiController {

    @GetMapping
    @ResponseBody
    List<Poi> list();

    @GetMapping("/{id}")
    @ResponseBody
    Poi findById(Long id);

    @PostMapping
    ResponseEntity<Poi> add(Poi poi);

    @GetMapping("/proximidade/{x}/{y}/{s}")
    @ResponseBody
    List<Poi> buscaProximos(Integer x, Integer y, Integer s);
}
