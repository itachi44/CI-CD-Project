package sn.ept.git.seminaire.cicd.repositories;

import sn.ept.git.seminaire.cicd.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgentRepository extends JpaRepository<Agent, UUID> {


    @Query("select a from Agent  a where a.phone=:phone")
    Optional<Agent> findByPhone(@Param("phone") String phone);


    @Query("select a from Agent  a where a.email=:email")
    Optional<Agent> findByEmail(@Param("email") String email);


    @Query("select a from Agent  a where a.email=:email and a.id<>:id")
    Optional<Agent> findByEmailWithIdDifferent(@Param("email") String email, @Param("id")  UUID id);


    @Query("select a from Agent  a where a.phone=:phone and a.id<>:id")
    Optional<Agent> findByPhoneWithIdDifferent(@Param("phone") String phone, @Param("id")  UUID id);
}
