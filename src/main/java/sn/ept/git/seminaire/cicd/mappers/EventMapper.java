package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.EventDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper extends GenericMapper<Event, EventDTO> {

}