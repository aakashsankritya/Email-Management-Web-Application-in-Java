/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.net.InetAddress;

/**
 *
 * @author akashsankritya
 */
public class getLocalIP {
    public String HostName()throws Exception
   {
      String name;
      InetAddress i=InetAddress.getLocalHost();
      name=i.getHostName();         
      return name;
   }      
}

