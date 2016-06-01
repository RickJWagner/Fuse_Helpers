package org.switchyard.quickstarts.rest.binding.jpa;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "msg_tbl", schema="jpa_test")

public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(name  = "sender")
    private String _sender;

    @Column(name = "receiver")
    private String _receiver;

    public Message(){}
    
    public Message(String string, String string2) {
    	_sender = string;
    	_receiver = string2;
	}

	public Long getId() {
        return _id;
    }

    public String getSender() {
        return _sender;
    }

    public void setSender(String sender) {
        this._sender = sender;
    }

    public String getReceiver() {
        return _receiver;
    }

    public void setReceiver(String receiver) {
        this._receiver = receiver;
    }

  
    @Override
    public String toString() {
        return "Message [" + _id + ", from " + _sender + ", to " + _receiver + "]";
    }

}
