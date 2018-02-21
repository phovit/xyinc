package br.com.xyinc.dao;

import br.com.xyinc.models.Poi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoiDao {

    List<Poi> all();

    void save(Poi poi);

    Poi findById(Integer id);

    void remove(Poi poi);

    void update(Poi poi);

    List<Poi> buscaProximos(int x, int y, int s);

}
