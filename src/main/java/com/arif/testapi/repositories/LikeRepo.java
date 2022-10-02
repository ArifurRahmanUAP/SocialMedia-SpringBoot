package com.arif.testapi.repositories;

import com.arif.testapi.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<Like, Integer> {

}
