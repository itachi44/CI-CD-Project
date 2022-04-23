package sn.ept.git.seminaire.cicd.repositories;

import sn.ept.git.seminaire.cicd.models.Attachement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttachementRepository extends JpaRepository<Attachement, UUID> {


}
