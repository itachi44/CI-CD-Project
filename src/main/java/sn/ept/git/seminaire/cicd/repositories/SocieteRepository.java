package sn.ept.git.seminaire.cicd.repositories;

import sn.ept.git.seminaire.cicd.models.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SocieteRepository extends JpaRepository<Societe, UUID> {


    @Query("select s from Societe  s where s.name=:name")
    Optional<Societe> findByName(@Param("name") String name);

    @Query("select s from Societe  s where s.phone=:phone")
    Optional<Societe> findByPhone(@Param("phone") String phone);

    @Query("select s from Societe  s where s.email=:email")
    Optional<Societe> findByEmail(@Param("email") String email);


    @Query("select s from Societe  s where s.name=:name and s.id<>:id")
    Optional<Societe> findByNameWithIdNotEqual(@Param("name") String name,@Param("id") UUID uuid);

    @Query("select s from Societe  s where s.phone=:phone and s.id<>:id")
    Optional<Societe> findByPhoneWithIdNotEqual(@Param("phone") String phone,@Param("id") UUID uuid);

    @Query("select s from Societe  s where s.email=:email and s.id<>:id")
    Optional<Societe> findByEmailWithIdNotEqual(@Param("email") String email,@Param("id") UUID uuid);

}
