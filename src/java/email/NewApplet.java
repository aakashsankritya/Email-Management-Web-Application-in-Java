/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.applet.Applet;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.List;


/**
 *
 * @author akashsankritya
 */
/* <applet code="NewApplet" width=300 height=260>
   </applet> 
*/
public class NewApplet extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    Button b1;
    Choice c1;
    Canvas cs1;
    Checkbox cb1,cb2,cb3,cb4;
    CheckboxGroup cbg;
    Label l1;
    List li1;
    Scrollbar sb1;
    ScrollPane sp1;
    TextField tf1;
    TextArea ta1;
    public void init() {
        setBackground(Color.pink);
        b1 = new Button("Interface");
        b1.setBackground(Color.red);
        b1.setForeground(Color.white);
        // c1=new Choice();
        // c1.addItem("Male");
        // c1.add("Female");
        cs1=new Canvas();
        cs1.setSize(100,100);
        cs1.setBackground(Color.YELLOW);
        cs1.setVisible(true);
        cb1=new Checkbox("C");
        cb2=new Checkbox("Java",true);
        // cbg=new CheckboxGroup();
        // cb3=new Checkbox("Male",cbg,true);
        // cb4=new Checkbox("Female",cbg,false);
        // l1=new Label("Enter your Course");
        // li1=new List(3,true);
        // li1.add("C");
        // li1.add("C++");
        // li1.add("Java");
        // li1.add(".Net");
        sb1 = new Scrollbar(Scrollbar.HORIZONTAL,425,10,400,600);
        sp1=new ScrollPane();
        // Button b11=new Button("Learn Java");
        // b11.setFont(new Font("Verdana",Font.BOLD,200));
        // sp1.add(b11);
        // tf1=new TextField(10);
        // ta1=new TextArea(10,10);
        // add(c1);
        // add(ta1);
        // add(sp1);
        
        // add(l1);
        // add(sb1);
        // add(tf1);
        add(b1);
        add(cb1);
        add(cb2);
        // add(cb3);
        // add(cb4);
        add(cs1);
    }

    // TODO overwrite start(), stop() and destroy() methods
}
