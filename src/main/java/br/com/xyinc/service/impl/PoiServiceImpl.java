package br.com.xyinc.service.impl;

import br.com.xyinc.models.Poi;
import br.com.xyinc.repository.PoiRepository;
import br.com.xyinc.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {


    @Autowired
    private PoiRepository repository;

    public List<Poi> list() {
        return repository.findAll();
    }

    public Poi findById(Long id) {
        return repository.findOne(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void add(Poi poi) {
        repository.save(poi);
    }

    public List<Poi> buscaProximos(Integer x, Integer y, Integer s) {
        List<Poi> poisProximas = new ArrayList<>();

        this.list().forEach(p -> {

            Double distancia = calculaDistancia(p, new Poi(x,y));

            if(distancia <= new Double(10)){
                poisProximas.add(p);
            }
        });

        return poisProximas;
    }

    private Double calculaDistancia(Poi p, Poi pontoInformado){
        int coordX = p.getX() -  pontoInformado.getX() ;
        int coordY = p.getY() -  pontoInformado.getY() ;

        return Math.sqrt(Math.pow(coordX, 2) + Math.pow(coordY, 2));
    }

}
