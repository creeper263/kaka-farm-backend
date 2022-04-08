package icu.f2v.kakafarm.persistence.repo;

import icu.f2v.kakafarm.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    boolean existsById(String id);
    User findById(String id);
}
