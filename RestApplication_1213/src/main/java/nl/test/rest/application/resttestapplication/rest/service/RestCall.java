package nl.test.rest.application.resttestapplication.rest.service;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.test.rest.application.resttestapplication.rest.generic.Namespace;
import nl.test.rest.application.resttestapplication.rest.object.ComplexObject;
import nl.test.rest.application.resttestapplication.rest.object.RootObject;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

@Stateless
@Path("/v1/RestCall")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCall {

  /**
   * Get the rootobject example
   *
   * @return RootObject response
   */
  @GET
  @Path("/getTest")
  @Produces(MediaType.APPLICATION_JSON)
  public RootObject getRootObject() {
    ComplexObject comObj = new ComplexObject(Namespace.TEST, "init78532498567");
    RootObject rObject = new RootObject();
    rObject.setName("Init object");
    rObject.setIdent("Ident object");
    rObject.setCreateDate(new LocalDate("1970-01-01"));
    rObject.setMyObject(comObj);

    return rObject;
  }

  /**
   * Get the parameter by parameterName
   *
   * @param parameterName
   * @return Json response
   */
  @POST
  @Path("/create")
  public Response getParameter(final RootObject object) {

    return Response.status(Response.Status.OK).build();
  }

}
