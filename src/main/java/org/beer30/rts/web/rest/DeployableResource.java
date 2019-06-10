package org.beer30.rts.web.rest;

import io.swagger.annotations.*;
import org.beer30.rts.domain.Deployable;
import org.beer30.rts.repository.DeployableRepository;
import org.beer30.rts.service.DeployableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Api(value="Deployable", description="Operations pertaining to deployables in the Release Tracking System")
public class DeployableResource {
    private final Logger log = LoggerFactory.getLogger(DeployableResource.class);

    @Autowired
    DeployableService deployableService;

    @Autowired
    DeployableRepository deployableRepository;

    /**
     * {@code POST  /deployable} : Create a new deployable.
     *
     * @param deployable the deployable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deployable, or with status {@code 400 (Bad Request)} if the deployable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deployables")
    @ApiOperation(value = "Create a deployable in RTS", response = Deployable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a deployable"),
            @ApiResponse(code = 401, message = "You are not authorized to create"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Deployable> createDeployable(@RequestBody Deployable deployable) throws URISyntaxException {
        log.info("REST request to save Deployable: {}", deployable);
        if (deployable.getId() != null) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        Deployable result = deployableService.addDeployable(deployable.getEnvironment(),deployable.getName(),deployable.getBuildId(),deployable.getSha256());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /deployables/:id} : get the "id" deployable.
     *
     * @param id the id of the deployable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deployable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deployables/{id}")
    @ApiOperation(value = "Find a deployable in RTS by its ID", response = Deployable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found a deployable"),
            @ApiResponse(code = 401, message = "You are not authorized to this deployable"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Deployable> getDeployable(
            @ApiParam(value = "Deployable id from which deployable object will retrieve", required = true) @PathVariable Long id) {
        log.debug("REST request to get Deployable : {}", id);
        Optional<Deployable> deployable = deployableRepository.findById(id);

        return deployable.map(response -> ResponseEntity.ok().headers(null).body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}



