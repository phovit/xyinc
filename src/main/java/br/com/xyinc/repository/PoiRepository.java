package br.com.xyinc.repository;


import br.com.xyinc.models.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoiRepository extends JpaRepository<Poi, Long> {

}

