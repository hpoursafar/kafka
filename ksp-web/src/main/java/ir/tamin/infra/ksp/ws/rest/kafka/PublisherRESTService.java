/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.infra.ksp.ws.rest.kafka;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ir.tamin.framework.swagger.common.model.ResponseRef;
import ir.tamin.framework.swagger.common.model.SecurityRef;
import ir.tamin.framework.swagger.common.model.XDepartment;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.infra.ksp.service.kafka.producer.AlertProducerV2;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

/**
 *
 * @author h_poursafar
 */
@Path("/producer")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PublisherRESTService {

    @Inject
    private AlertProducerV2 alertProducerV2;

    @Operation(
            summary = "kafka producer test",
           description = "kafka producer test",
            extensions = {@Extension(properties = {@ExtensionProperty(name = XDepartment.NAME, value =
                    XDepartment.INFRASTRUCTURE)})},
            security = @SecurityRequirement(name = SecurityRef.OAUTH2),
            responses = {
                    @ApiResponse(responseCode = "401", ref = ResponseRef.UNAUTHORIZED),
                    @ApiResponse(responseCode = "403", ref = ResponseRef.FORBIDDEN),
                    @ApiResponse(responseCode = "500", ref = ResponseRef.SERVER_ERROR),
                    @ApiResponse(responseCode = "404", ref = ResponseRef.NOT_FOUND),
                    @ApiResponse(responseCode = "200",
                            description = "اطلاعات به producer ارسال شد" ,
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = String.class))})
            },
            parameters = {
            @Parameter(
                    in = ParameterIn.HEADER,
                    description = "کد ملی",
                    name = "id"),
    })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendRequests( @RequestBody(description = "id" , content = @Content(mediaType = MediaType.APPLICATION_JSON)) String id ) throws WebApplicationException {
        try {
                alertProducerV2.produceAlert(id);
                return ResponseHelper.ok();
        } catch (Exception e) {
            return ResponseHelper.serverError();
        }
    }

}
