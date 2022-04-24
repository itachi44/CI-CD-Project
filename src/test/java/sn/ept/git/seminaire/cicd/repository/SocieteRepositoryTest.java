package sn.ept.git.seminaire.cicd.repositories;

import sn.ept.git.seminaire.cicd.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SiteRepository extends JpaRepository<Site, UUID> {

    @Query("select s from Site s where s.name=:name")
    Optional<Site> findByName(@Param("name") String name);

    @Query("select s from Site  s where s.phone=:phone")
    Optional<Site> findByPhone(@Param("phone") String phone);

    @Query("select s from Site  s where s.email=:email")
    Optional<Site> findByEmail(@Param("email") String email);


    @Query("select s from Site s where s.name=:name and s.id<>:id")
    Optional<Site> findByNameWithIdDifferent(@Param("name") String name, @Param("id") UUID id);

    @Query("select s from Site  s where s.phone=:phone and s.id<>:id")
    Optional<Site> findByPhoneWithIdNotEqual(@Param("phone") String phone, @Param("id") UUID uuid);

    @Query("select s from Site  s where s.email=:email and s.id<>:id")
    Optional<Site> findByEmailWithIdNotEqual(@Param("email") String email, @Param("id") UUID uuid);
}