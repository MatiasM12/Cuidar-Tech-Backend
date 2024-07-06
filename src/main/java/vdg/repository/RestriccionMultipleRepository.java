package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import vdg.model.domain.RestriccionMultiple;

public interface RestriccionMultipleRepository extends JpaRepository<RestriccionMultiple, Integer> {


		public List<RestriccionMultiple> findById(int id);
		public List<RestriccionMultiple> findAll();
		public void deleteById(int id);
		public RestriccionMultiple save(RestriccionMultiple resMul);
}
