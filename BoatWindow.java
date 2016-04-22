import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BoatWindow extends JFrame
{  
    private Boat _boat; // use '_boat' instead of 'boat'
    public BoatWindow(Boat boat)
    {
        this._boat = boat;
        setup();
        build(_boat);
        pack();
        setVisible(true);
    }

    private void setup()
    {
        setLocation(800, 500 + (_boat.id() - 1) * 150);
    }

    private void build(Boat boat)
    {
        add(new BoatPanel(boat));
    }

    private class BoatPanel extends JPanel implements View
    {  
        private Boat boat;
        private JTable table;
        private BoatTable model;
        public BoatPanel(Boat boat)
        {
            this.boat = boat;
            this.boat.attach(this);
            setup();
            build();
        }

        public void update()
        {
            setup();
            build();
        }

        private void setup()
        {
            model = new BoatTable();
            table = new JTable(model);
            setTitle("Boat " + _boat.id());
            TableColumnModel model = table.getColumnModel();
            model.getColumn(0).setPreferredWidth(30);
            model.getColumn(1).setPreferredWidth(100);
            model.getColumn(2).setPreferredWidth(100);
            setBorder(BorderFactory.createLineBorder(Color.GREEN));
            table.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }

        private void build()
        {
            Box mBox = Box.createHorizontalBox();
            Box tableBox = Box.createVerticalBox();
            tableBox.add(table.getTableHeader());
            tableBox.add(table);
            mBox.add(tableBox);
            mBox.add(Box.createHorizontalStrut(10));
            JButton goButton = new JButton("GO");
            Listener listener = new Listener();
            listener.setBoat(boat);
            goButton.addActionListener(listener);
            mBox.add(goButton);
            add(mBox);
        }
        
        private class Listener implements ActionListener
        {
            private Boat boat;
            public void setBoat(Boat boat)
            {
                this.boat = boat;
            }

            public void actionPerformed(ActionEvent e)
            {
                boat.go();
                update();
            }
        }
        
        private class BoatTable extends AbstractTableModel
        {  
            private final int cols = 3;
            public int getRowCount()
            {
                return boat.stops();

                //switch(boat.id())
                //{   case 1: return 3;
                //    case 2: return 4;
                //    case 3: return 5;
                //    default: return 3; }
            }

            public int getColumnCount()
            {
                return cols;
            }
            
            public String getColumnName(int col)
            {
                switch(col)
                {
                    case 0: return "Stop";
                    case 1: return "On";
                    default:return "Off";
                }
            }

            public Object getValueAt(int row, int col)
            {
                switch(col)
                {   
                    case 0: return row;
                    case 1: return boat.on(row);
                    case 2: return boat.off(row); 
                }
                return ""; // return empty
            }
        }
    }
}