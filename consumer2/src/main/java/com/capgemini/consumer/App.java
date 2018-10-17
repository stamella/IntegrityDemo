package com.capgemini.consumer;

import javax.jms.Connection;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.Queue;
import com.sun.messaging.QueueConnectionFactory;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       ConnectionFactory connectionFactory = null;
      
       Queue queue=null;
       try {
    	   InitialContext initialContext = new InitialContext();
    	    queue = (Queue) initialContext.lookup("jms/P2PQueue");
    	   connectionFactory = (QueueConnectionFactory)initialContext.lookup("jms/__defaultConnectionFactory");
    	  
    	   
       }catch (NamingException e) {
    	   e.printStackTrace();
      try(JMSContext context  = connectionFactory.createContext()) {
    	  JMSConsumer consumer = context.createConsumer(queue);
    	  String body = consumer.receiveBody(String.class);
    	  System.out.println(body);
      }
    }
    }
}
