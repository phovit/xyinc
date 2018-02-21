package br.com.xyinc.controllers.impl;

import br.com.xyinc.controllers.PoiController;
import br.com.xyinc.exception.PoiNotFoundException;
import br.com.xyinc.models.Poi;
import br.com.xyinc.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/poi")
@Transactional
public class PoiControllerImpl implements PoiController {

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
    public ResponseEntity<Poi> add(@PathVariable("name") String name, @PathVariable("x") Integer x, @PathVariable("y") Integer y) {
        Poi poi = null;

        try {
            Assert.notNull(name, "O Parametro nome não pode ser vazio");
            Assert.notNull(x, "O Parametro X não pode ser vazio");
            Assert.notNull(y, "O Parametro Y não pode ser vazio");
            Assert.isTrue(x > 0, "O Parametro X não pode ser negativo");
            Assert.isTrue(y > 0, "O Parametro Y não pode ser negativo");
        }catch (Exception e){
            return new ResponseEntity<>(poi, HttpStatus.BAD_REQUEST);
        }
        try {
            poi = new Poi(null, name, x, y);
            service.add(poi);
        } catch (Exception e) {
            return new ResponseEntity<>(poi, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(poi, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Poi> add(@RequestBody Poi poi) {
        try{
            Assert.notNull( poi.getName() , "O Parametro nome não pode ser vazio");
            Assert.notNull( poi.getX()  , "O Parametro X não pode ser vazio");
            Assert.notNull( poi.getY()  , "O Parametro Y não pode ser vazio");
            Assert.isTrue( poi.getX() >0   , "O Parametro X não pode ser negativo");
            Assert.isTrue( poi.getY() >0   , "O Parametro Y não pode ser negativo");
        }catch (Exception e){
            return new ResponseEntity<>(poi, HttpStatus.BAD_REQUEST);
        }

        try {
            service.add((Poi) poi);
        } catch (Exception e) {
            return new ResponseEntity<Poi>((Poi) poi, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Poi>((Poi) poi, HttpStatus.CREATED);
    }

    @Override
    public @ResponseBody
    List<Poi> buscaProximos(@PathVariable("x") Integer x, @PathVariable("y") Integer y, @PathVariable("s") Integer s) {

        List<Poi> pois = service.buscaProximos(x, y, s);

        if (pois.isEmpty()) {
            throw new PoiNotFoundException("Não foi encontrado nenhum ponto de interesse");
        }
        return pois;
    }

    @Override
    public @ResponseBody
    List<Poi> buscaProximosForEach(@PathVariable("x") Integer x, @PathVariable("y") Integer y, @PathVariable("s") Integer s) {

        List<Poi> pois = service.buscaProximosForEach(x, y, s);

        if (pois.isEmpty()) {
            throw new PoiNotFoundException("Não foi encontrado nenhum ponto de interesse");
        }
        return pois;
    }
}
