package br.com.xyinc.service.impl;

import br.com.xyinc.dao.PoiDao;
import br.com.xyinc.models.Poi;
import br.com.xyinc.models.PontoView;
import br.com.xyinc.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {


    @Autowired
    private PoiDao poiDao;

    public List<Poi> list() {
        return poiDao.all();
    }

    public void add(String name, Integer x, Integer y) {
        poiDao.save(new Poi(null, name, x, y));
    }

    public void add(Poi poi) {
        poiDao.save(poi);
    }

    public List<Poi> buscaProximos(Integer x, Integer y, Integer s) {
        return poiDao.buscaProximos(x, y, s);
    }

    public List<Poi> buscaProximosForEach(Integer x, Integer y, Integer s) {
        List<Poi> poisProximas = new ArrayList<>();

        this.list().forEach((poi) -> {
            if (this.verificaProximidade(poi, x, y, s)) {
                poisProximas.add(poi);
            }
        });

        return poisProximas;
    }

    private boolean verificaProximidade(Poi poi, Integer x, Integer y, Integer s) {
        PontoView p1 = new PontoView(poi.getX(), poi.getY());
        PontoView p2 = new PontoView(x, y);
        return calculaDistanciaPontos(p1, p2) > s;
    }

    private Double calculaDistanciaPontos(PontoView ponto1, PontoView ponto2) {

        Double dx = Math.pow(ponto1.getX() - ponto2.getX(), 2);
        Double dy = Math.pow(ponto1.getY() - ponto2.getY(), 2);

        return Math.sqrt(dx + dy);
    }

}
