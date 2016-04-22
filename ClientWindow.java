import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.text.DecimalFormat;
//import AbstractTableModel

public class ClientWindow extends JFrame
{
    private Boats boats;
    private JTable table;
    public ClientWindow(Boats boats)
    {
        this.boats = boats;
        setup();
        build();
        pack();
        setVisible(true);
    }

    private void setup()
    {
        setLocation(500, 620);
    }

    private void build()
    {
        add(new ClientPanel(this.boats));
    }

    private class ClientPanel extends JPanel implements View
    {
        private ClientTable model;
        private JTable table;
        private Boats boats;
        public ClientPanel(Boats boats)
        {
            this.boats = boats;
            for (Boat boat: boats.boats())
                boat.attach(this);   
            setup();
            build();
        }
        
        private void setup()
        {
            model = new ClientTable();
            table = new JTable(model);
            setTitle("Clients");
            
            table.getColumnModel().getColumn(0).setPreferredWidth(99);
            table.getColumnModel().getColumn(1).setPreferredWidth(70);
            
            setBorder(BorderFactory.createLineBorder(Color.WHITE));
            table.setBorder(BorderFactory.createLineBorder(Color.RED));
            table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.RED));
        }

        private void build()
        {
            Box box = Box.createVerticalBox();
            box.add(table.getTableHeader());
            box.add(table);
            add(box);
        }

        public void update()
        {
            setup();
            build();
        }
        
        private class ClientTable extends AbstractTableModel
        {
            private final int cols = 2;
            public int getRowCount()
            {
                return Client.all().size();
            }

            public int getColumnCount()
            {
                return cols;
            }
            
            public String getColumnName(int col)
            {
                switch(col)
                {
                    case 0: return "Name";
                    default: return "Cash";
                }
            }

            public Object getValueAt(int row, int col)
            {
                Client client = Client.all().get(row);
                switch(col)
                {
                    case 0: return "    " + client.name;
                            //if (clients.isAtRow(row)) {
                                //return "   " + person.name(); }
                    case 1: return "  $" + new DecimalFormat("###.00").format(client.money);
                            //if (clients.isAtRow(row)) {
                                //return "  $" + new DecimalFormat("###.00").format(client.money); }
                }
                return ""; // return empty
            }
        }
    }
}