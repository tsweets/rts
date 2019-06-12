package org.beer30.rts.web.rest;

import io.swagger.annotations.*;
import org.beer30.rts.domain.Component;
import org.beer30.rts.repository.ComponentRepository;
import org.beer30.rts.service.ComponentService;
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
@Api(value="Components", description="Operations pertaining to components in the Release Tracking System")
public class ComponentResource {
    private final Logger log = LoggerFactory.getLogger(ComponentResource.class);

    @Autowired
    ComponentService componentService;

    @Autowired
    ComponentRepository componentRepository;

    /**
     * {@code POST  /components} : Create a new component.
     *
     * @param component the component to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new component, or with status {@code 400 (Bad Request)} if the component has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/components")
    @ApiOperation(value = "Create a component in RTS", response = Component.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a component"),
            @ApiResponse(code = 401, message = "You are not authorized to create"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Component> createComponent(@RequestBody Component component) throws URISyntaxException {
        log.info("REST request to save Component: {}", component);
        if (component.getId() != null) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        Component result = componentService.addComponent(component.getEnvironment(),component.getName(),component.getScmHash(),component.getBuildId(), component.getComponentType(), component.getComponentReference());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /components/:id} : get the "id" component.
     *
     * @param id the id of the component to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the component, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/components/{id}")
    @ApiOperation(value = "Find a component in RTS by its ID", response = Component.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found a component"),
            @ApiResponse(code = 401, message = "You are not authorized to this component"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Component> getComponent(
            @ApiParam(value = "Component id from which component object will retrieve", required = true) @PathVariable Long id) {
        log.debug("REST request to get Component : {}", id);
        Optional<Component> component = componentRepository.findById(id);

        return component.map(response -> ResponseEntity.ok().headers(null).body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}



