package org.pelevin.repositories;

import org.pelevin.model.CustomerVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by dmitry on 27.08.15.
 */
public interface CustomerRepository extends JpaRepository<CustomerVO, Long> {


	Optional<CustomerVO> findById(Long id);
	Optional<CustomerVO> findByName(String name);

}
