package com.greyog.transaqclient.entity.command;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "command")
public class DisconnectCommand {
    @XmlAttribute
    String id = "disconnect";
}
