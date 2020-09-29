package com.pfizer.sacchon.team3.resource.chief.settings;

import com.pfizer.sacchon.team3.exception.BadEntityException;
import com.pfizer.sacchon.team3.model.Chiefs;
import com.pfizer.sacchon.team3.repository.ChiefRepository;
import com.pfizer.sacchon.team3.repository.util.JpaUtil;
import com.pfizer.sacchon.team3.representation.ChiefRepresentation;
import com.pfizer.sacchon.team3.representation.ResponseRepresentation;
import com.pfizer.sacchon.team3.resource.util.ResourceValidator;
import org.restlet.engine.Engine;
import org.restlet.resource.ServerResource;

import java.util.Optional;
import java.util.logging.Logger;

public class ChiefResourceImpl extends ServerResource implements ChiefResource {
    public static final Logger LOGGER = Engine.getLogger(ChiefResourceImpl.class);
    private long id;
    private ChiefRepository chiefRepository;

    @Override
    protected void doInit() {
        LOGGER.info("Initialising chief resource starts");
        try {
            chiefRepository = new ChiefRepository(JpaUtil.getEntityManager());
            id = Long.parseLong(getAttribute("id"));
        } catch (Exception e) {
            id = -1;
        }
        LOGGER.info("Initialising chief resource ends");
    }

    @Override
    public ResponseRepresentation<ChiefRepresentation> update(ChiefRepresentation chiefRepresentation) {
        LOGGER.finer("Update chief.");
        // Check given entity
        try {
            ResourceValidator.notNull(chiefRepresentation);
            ResourceValidator.validateChief(chiefRepresentation);
        } catch (BadEntityException ex) {
            return new ResponseRepresentation<>(422, "BadEntity", null);
        }

        LOGGER.finer("Chief checked");

        try {
            // Convert ChiefRepr to Chief
            Chiefs chiefIn = chiefRepresentation.createChief();
            chiefIn.setId(id);
            Optional<Chiefs> chiefOut = chiefRepository.findById(id);
            setExisting(chiefOut.isPresent());

            if (isExisting()) {
                LOGGER.finer("Update chief.");
                chiefOut = chiefRepository.update(chiefIn);
                if (!chiefOut.isPresent()) {
                    LOGGER.finer("chief does not exist.");
                    return new ResponseRepresentation<>(404, "Chief not found", null);
                }
            } else {
                LOGGER.finer("chief does not exist.");
                return new ResponseRepresentation<>(404, "Chief not found", null);
            }
            LOGGER.finer("Chief successfully updated.");

            return new ResponseRepresentation<>(200, "Chief retrieved", new ChiefRepresentation(chiefOut.get()));
        } catch (Exception ex) {
            return new ResponseRepresentation<>(422, "Resource exception", null);
        }
    }
}
