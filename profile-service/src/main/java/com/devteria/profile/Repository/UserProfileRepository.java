package com.devteria.profile.Repository;

import com.devteria.profile.Entity.UserProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile,String> {
}
