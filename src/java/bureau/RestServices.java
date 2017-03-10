
package bureau;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Nicolas Singer
 */
@Path("generic")
public class RestServices {

    @Context
    private UriInfo context;
    
    Services serv;

    public RestServices() {
        serv = new Services(DatabaseUtils.fact());
    }
    
    /** Patients **/

    @GET
    @Path("patients/{ipp}")
    @Produces("application/json")
    public Patient getPatient(@PathParam("ipp") String ipp) {
        return serv.getPatientByIPP(ipp);
    }
    
    @GET
    @Path("patients")
    @Produces("application/json")
    public List<Patient> getAllPatients() {
        return serv.getAllPatients();
    }
    
    @POST
    @Path("patients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Patient newPatient(Patient p) {
        serv.newPatient(p);
        System.out.println("id:"+p.getId());
        return p;
    }
    
    @POST
    @Path("patients/{ipp}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPatient(Patient p) {
        serv.editPatient(p);
        return Response.status(200).entity(p).build();
    }
    
    @DELETE
    @Path("patients/{ipp}")
    public Response removePatient(@PathParam("ipp") String ipp) {
        Patient p = serv.getPatientByIPP(ipp);
        serv.removePatient(p.getId());
        return Response.status(200).build();
    }
    
    /** Venues **/
    
    @GET
    @Path("venues/{iep}")
    @Produces("application/json")
    public Venue getVenue(@PathParam("iep") String iep) {
        return serv.getVenueByIEP(iep);
    }
    
    @GET
    @Path("venues/{iep}/Patient")
    @Produces("application/json")
    public Patient getPatientByVenue(@PathParam("iep") String iep) {
        return serv.getPatientByVenue(iep);
    }
    
    @GET
    @Path("venues/{iep}/mouvements")
    @Produces("application/json")
    public List<Mouvement> getMouvementsByVenue(@PathParam("iep") String iep) {
        return serv.getVenueByIEP(iep).getMouvements();
    }
    
    @GET
    @Path("venues/{iep}/mouvements/{id}")
    @Produces("application/json")
    public Mouvement getMouvementByVenue(@PathParam("iep") String iep, @PathParam("id") long id) {
        return serv.getMouvementById(id);
    }
    
    @GET
    @Path("venues")
    @Produces("application/json")
    public List<Venue> getAllVenues() {
        return serv.getAllVenues();
    }
    
    @POST
    @Path("venues")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Venue newVenue(Venue v) {
        serv.newVenue(v);
        System.out.println("id:"+v.getId());
        return v;
    }
    
    @POST
    @Path("venues/{iep}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editVenue(Venue v) {
        serv.editVenue(v);
        return Response.status(200).entity(v).build();
    }
    
    @DELETE
    @Path("venues/{iep}")
    public Response removeVenue(@PathParam("iep") String iep) {
        Venue v = serv.getVenueByIEP(iep);
        serv.removeVenue(v.getId());
        return Response.status(200).build();
    }
    
    @DELETE
    @Path("venues/{iep}/mouvements/{id}")
    public Response removeMouvement(@PathParam("iep") String iep, @PathParam("id") long id) {
        Mouvement m = serv.getMouvementById(id);
        serv.getVenueByIEP(iep).getMouvements().remove(serv.getVenueByIEP(iep).getMouvements().indexOf(m));
        serv.removeMouvement(id);
        return Response.status(200).build();
    }
    
    @POST
    @Path("venues/{iep}/mouvements")
    public Response addMouvement(@PathParam("iep") String iep, Mouvement m) {
        serv.newMouvement(m);
        serv.addMouvementToVenue(m, serv.getVenueByIEP(iep));
        return Response.status(200).build();
    }
    
    /** Mouvements **/
    
    @GET
    @Path("mouvements/{id}")
    @Produces("application/json")
    public Mouvement getMouvement(@PathParam("id") long id) {
        return serv.getMouvementById(id);
    }
    
    @GET
    @Path("mouvements")
    @Produces("application/json")
    public List<Mouvement> getAllMouvements() {
        return serv.getAllMouvements();
    }
    
    @POST
    @Path("mouvements")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Mouvement newMouvement(Mouvement m) {
        serv.newMouvement(m);
        return m;
    }
    
    @POST
    @Path("mouvements/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMouvement(Mouvement m) {
        serv.editMouvement(m);
        return Response.status(200).entity(m).build();
    }
    
    @DELETE
    @Path("mouvements/{id}")
    public Response removeMouvement(@PathParam("id") long id) {
        Mouvement m = serv.getMouvementById(id);
        serv.getVenueByMouvement(id).getMouvements().remove(m);
        serv.removeMouvement(id);
        return Response.status(200).build();
    }
    
    /** Services **/
    
    @GET
    @Path("services/{num}")
    @Produces("application/json")
    public Service getService(@PathParam("num") String num) {
        return serv.getServiceByNumero(num);
    }
    
    @GET
    @Path("services")
    @Produces("application/json")
    public List<Service> getAllServices() {
        return serv.getAllServices();
    }
    
    @POST
    @Path("services")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Service newPatient(Service s) {
        serv.newService(s);
        System.out.println("id:"+s.getId());
        return s;
    }
    
    @POST
    @Path("services/{num}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editService(Service s) {
        serv.editService(s);
        return Response.status(200).entity(s).build();
    }
    
    @DELETE
    @Path("services/{num}")
    public Response removeService(@PathParam("num") String num) {
        Service s = serv.getServiceByNumero(num);
        serv.removePatient(s.getId());
        return Response.status(200).build();
    }

}
