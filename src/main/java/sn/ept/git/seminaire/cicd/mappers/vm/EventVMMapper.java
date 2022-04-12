package sn.ept.git.seminaire.cicd.mappers.vm;

import sn.ept.git.seminaire.cicd.dto.vm.EventVM;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventVMMapper extends GenericMapper<Event, EventVM> {

}