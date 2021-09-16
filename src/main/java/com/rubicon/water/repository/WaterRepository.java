package com.rubicon.water.repository;

import com.rubicon.water.entity.WaterOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterRepository extends CrudRepository<WaterOrder, String> {

}
