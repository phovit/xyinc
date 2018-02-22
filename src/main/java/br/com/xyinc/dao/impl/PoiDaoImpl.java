package br.com.xyinc.dao.impl;

import br.com.xyinc.dao.PoiDao;
import br.com.xyinc.models.Poi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PoiDaoImpl implements PoiDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Poi> all() {
        return manager.createQuery("select p from Poi p", Poi.class).getResultList();
    }

    @Override
    public void save(Poi poi) {
        manager.persist(poi);
    }

    @Override
    public Poi findById(Integer id) {
        return manager.find(Poi.class, id);
    }

    @Override
    public void remove(Poi poi) {
        manager.remove(poi);
    }

    @Override
    public void update(Poi poi) {
        manager.merge(poi);
    }

    @Override
    public List<Poi> buscaProximos(int x, int y, int s) {
        return manager.createQuery("select p from Poi p where sqrt(((p.x - " + x + ") * (p.x - " + x + ")) + ((p.y - " + y + ") * (p.y - " + y + ")) ) < " + s, Poi.class).getResultList();
    }
}
