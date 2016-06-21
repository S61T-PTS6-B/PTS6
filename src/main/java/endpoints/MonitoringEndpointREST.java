/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import service.ICarTrackerService;

/**
 *
 * @author koenv
 */
@Path("/status")
public class MonitoringEndpointREST {

	@Inject
	ICarTrackerService cts;

	@GET
	@Path("/general")
	public Response getGeneralStatus() {
		return Response.ok().build();
	}

	@GET
	@Path("/database")
	public Response getDatabaseStatus() {
		if (cts.DatabaseIsOnline()) {
			return Response.ok().build();
		} else {
			return Response.serverError().build();
		}

	}
}
