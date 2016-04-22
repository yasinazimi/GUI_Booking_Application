// Yasin Azimi - 11733490


import model.*;
import java.awt.*;
import javax.swing.*;

public class Root extends JFrame
{  
    public static void main(String[] args)
    {
        new Root(new Boats());
    }

    public Root(Boats boats)
    {
        setup();
        build(boats);
        pack();
        setVisible(true);
    }

    private void setup()
    {
        setLocation(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void build(Boats boats)
    {
        add(new MainPanel(boats));
        new ClientWindow(boats);
        for (Boat boat: boats.boats())
            new BoatWindow(boat);

        //switch (id)
        //{ case 1: new BoatWindow(boat, 500); break;
        //  case 2: new BoatWindow(boat, 650); break;
        //  default:new BoatWindow(boat, 800); break; }
    }
}
