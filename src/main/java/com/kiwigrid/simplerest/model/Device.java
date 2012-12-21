package com.kiwigrid.simplerest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="device", namespace="http://www.kiwigrid.com/simplerest")
public class Device {
	
	public String guid = "1-1-1";
	public String name = "n/a";
	public String type = "device";
	public boolean installed = true;
	

}
