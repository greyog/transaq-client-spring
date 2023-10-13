package com.greyog.transaqclient.entity.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "command")
public class GetFortsPositionCommand {
    @XmlAttribute
    String id = "get_forts_positions";
}
