package com.kiwigrid.simplerest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kiwigrid.simplerest.model.Device;

@Path("")
public class SimpleRest {

	/**
	 * Device List
	 */
	private static final Map<String, Device> devices = new HashMap<String, Device>();
	static {
		Device d1 = new Device();
		d1.guid = "xyz-123-1";
		d1.name = "Inverter 1";
		d1.type = "inverter";
		d1.installed = true;		
		devices.put(d1.guid, d1);
		
		Device d2 = new Device();
		d2.guid = "xyz-123-2";
		d2.name = "Inverter 2";
		d2.type = "inverter";
		d2.installed = false;		
		devices.put(d2.guid, d2);
		
		Device d3 = new Device();
		d3.guid = "abc-111-1";
		d3.name = "Plug 1";
		d3.type = "plug";
		d3.installed = false;		
		devices.put(d3.guid, d3);
		
		Device d4 = new Device();
		d4.guid = "abc-111-2";
		d4.name = "Plug 2";
		d4.type = "plug";
		d4.installed = true;		
		devices.put(d4.guid, d4);
	}
	
	/**
	 * Get all devices in the system
	 * @return list of all devices
	 */
	@Path("/devices")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Device> getAllDevices() {		
		return new ArrayList<Device>(devices.values());
	}
	
	/**
	 * Get a single device by guid
	 * @param lems_id
	 * @return
	 */
	@Path("/devices/{device_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Device getDevice(@PathParam("device_id") String deviceId) {
		if(devices.containsKey(deviceId)) {
			return devices.get(deviceId);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	/**
	 * Update (or create) a single device by guid
	 * @param lems_id
	 * @return
	 */
	@Path("/devices/{device_id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Device putDevice(Device device, @PathParam("device_id") String deviceId) {
		if(devices.containsKey(deviceId)) {
			// make sure we have the same Id as in the Path
			device.guid = deviceId;
			devices.put(deviceId, device);
			return device;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	/**
	 * Create a single device
	 * @param lems_id
	 * @return
	 */
	@Path("/devices")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Device postDevice(Device device) {
		if(device.guid == null || "".equals(device.guid)) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		devices.put(device.guid, device);
		return device;
	}
	
	/**
	 * Update (or create) a single device by guid
	 * @param lems_id
	 * @return
	 */
	@Path("/devices/{device_id}")
	@DELETE
	public Response deleteDevice(@PathParam("device_id") String deviceId) {
		if(devices.containsKey(deviceId)) {
			devices.remove(deviceId);
			return Response.ok().build();
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
}
