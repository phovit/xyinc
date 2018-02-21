package br.com.xyinc.service;

import br.com.xyinc.models.Poi;

import java.util.List;


public interface PoiService {

    List<Poi> list();

    void add(String name, Integer x, Integer y);

    void add(Poi poi);

    List<Poi> buscaProximos(Integer x, Integer y, Integer s);

    List<Poi> buscaProximosForEach(Integer x, Integer y, Integer s);

}
