package br.com.xyinc.controllers.impl;

import br.com.xyinc.controllers.PoiController;
import br.com.xyinc.exception.PoiNotFoundException;
import br.com.xyinc.models.Poi;
import br.com.xyinc.service.PoiService;
import com.google.gson.Gson;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@Transactional
public class PoiControllerImpl implements PoiController {

    private static final Logger log = Logger.getLogger(PoiControllerImpl.class.getName());

    @Autowired
    private PoiService service;

    @Override
    public @ResponseBody
    List<Poi> list() {
        List<Poi> pois = service.list();

        if (pois.isEmpty()) {
            throw new PoiNotFoundException("Não foi encontrado nenhum ponto de interesse");
        }
        return pois;
    }

    @Override
    public Poi findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @Override
    public ResponseEntity add(@RequestBody @Valid Poi poi) {
        service.add(poi);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/poi")
                .path("/{id}")
                .buildAndExpand(poi.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public @ResponseBody List<Poi> buscaProximos(@PathVariable("x") Integer x, @PathVariable("y") Integer y, @PathVariable("s") Integer s) {

        List<Poi> pois = service.buscaProximos(x, y, s);

        if (pois.isEmpty()) {
            throw new PoiNotFoundException("Não foi encontrado nenhum ponto de interesse");
        }
        return pois;
    }
}
