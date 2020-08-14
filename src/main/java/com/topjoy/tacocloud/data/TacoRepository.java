package com.topjoy.tacocloud.data;

import com.topjoy.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
//   Taco save(Taco design);
}
