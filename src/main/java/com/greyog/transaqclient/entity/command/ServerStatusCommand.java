package com.greyog.transaqclient.entity.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "command")
public class ServerStatusCommand {
    @XmlAttribute
    String id = "server_status";
}
