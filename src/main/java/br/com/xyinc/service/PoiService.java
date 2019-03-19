package br.com.xyinc.service;

import br.com.xyinc.models.Poi;

import java.util.List;


public interface PoiService {

    List<Poi> list();

    Poi findById(Long id);

    void add(Poi poi);

    List<Poi> buscaProximos(Integer x, Integer y, Integer s);

}
