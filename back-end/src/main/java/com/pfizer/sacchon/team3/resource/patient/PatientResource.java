package com.pfizer.sacchon.team3.resource.patient;

import com.pfizer.sacchon.team3.exception.BadEntityException;
import com.pfizer.sacchon.team3.exception.NotFoundException;
import com.pfizer.sacchon.team3.representation.PatientRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface PatientResource {
    @Get("json")
    public PatientRepresentation getPatient() throws NotFoundException;

    @Delete
    public void remove() throws NotFoundException;

    @Put("json")
    public PatientRepresentation store(PatientRepresentation patientRepresentation) throws NotFoundException, BadEntityException;
}
