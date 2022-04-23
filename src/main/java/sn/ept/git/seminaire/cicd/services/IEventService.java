package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.EventDTO;
import sn.ept.git.seminaire.cicd.dto.vm.EventVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface IEventService extends GenericService<EventDTO, EventVM, UUID> {
}