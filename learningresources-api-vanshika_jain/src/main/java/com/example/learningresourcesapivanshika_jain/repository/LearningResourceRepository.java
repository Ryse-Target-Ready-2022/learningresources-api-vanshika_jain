package com.example.learningresourcesapivanshika_jain.repository;

import com.example.learningresourcesapivanshika_jain.entity.LearningResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceRepository  extends JpaRepository<LearningResource, Integer> {
}
