package sn.ept.git.seminaire.cicd.repositories;

import sn.ept.git.seminaire.cicd.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeRepository extends JpaRepository<Type, UUID> {

    @Query("select t from Type  t where t.name=:name")
    Optional<Type> findByName(@Param("name") String name);
}