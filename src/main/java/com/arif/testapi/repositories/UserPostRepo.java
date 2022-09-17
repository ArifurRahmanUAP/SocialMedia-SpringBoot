package com.arif.testapi.repositories;

import com.arif.testapi.entities.Category;
import com.arif.testapi.entities.User;
import com.arif.testapi.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser (User user);

    List<Post> findByCategory (Category category);

    List<Post> findByPostTitleContaining(String keyword);


}
