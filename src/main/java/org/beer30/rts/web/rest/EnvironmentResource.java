package org.beer30.rts.web.rest;

import io.swagger.annotations.*;
import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.repository.ComponentRepository;
import org.beer30.rts.repository.EnvironmentRepository;
import org.beer30.rts.service.ComponentService;
import org.beer30.rts.service.EnvironmentService;
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
@Api(value="Environments", description="Operations pertaining to environments in the Release Tracking System")
public class EnvironmentResource {
    private final Logger log = LoggerFactory.getLogger(EnvironmentResource.class);

    @Autowired
    EnvironmentService environmentService;

    @Autowired
    EnvironmentRepository environmentRepository;

    /**
     * {@code POST  /environments} : Create a new environment.
     *
     * @param environment the environment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new environment, or with status {@code 400 (Bad Request)} if the environment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/environments")
    @ApiOperation(value = "Create a environment in RTS", response = Environment.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created an environment"),
            @ApiResponse(code = 401, message = "You are not authorized to create"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Environment> createEnvironment(@RequestBody Environment environment) throws URISyntaxException {
        log.info("REST request to save Environment: {}", environment);
        if (environment.getId() != null) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        Environment result = environmentService.addEnvironment(environment.getName());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /environments/:id} : get the "id" component.
     *
     * @param id the id of the environment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the environment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/environments/{id}")
    @ApiOperation(value = "Find an environment in RTS by its ID", response = Component.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found an environment"),
            @ApiResponse(code = 401, message = "You are not authorized to this environment"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Environment> getEnvironment(
            @ApiParam(value = "Environment id from which environment object will retrieve", required = true) @PathVariable Long id) {
        log.debug("REST request to get Environment : {}", id);
        Optional<Environment> environment = environmentRepository.findById(id);

        return environment.map(response -> ResponseEntity.ok().headers(null).body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}



