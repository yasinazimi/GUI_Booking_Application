import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainPanel extends JPanel implements View
{  
    private DefaultListModel model = new DefaultListModel();
    private JList list = new JList(model);
    private JTextField name = new JTextField(9);
    private JTextField start = new JTextField(9);
    private JTextField end = new JTextField(9);
    private Boats boats;
    public MainPanel(Boats boats)
    {
        this.boats = boats;
        for (Boat boat: boats.boats())
            boat.attach(this);
        //list = new JList(model);
        setup();
        build();
        update();
    }

    public void update()
    {
        //clear();
        model.clear();
        for (Boat boat: boats.boats())
            model.addElement("Boat " + boat.id());
    }

    private void setup()
    {
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.getSelectionModel().addListSelectionListener(new Listener());
    }

    private void setSize(JComponent c, int x)
    {  
        c.setPreferredSize(new Dimension(x, 20));
        c.setMinimumSize(new Dimension(x, 20));
        c.setMaximumSize(new Dimension(x, 20));
    }

    private void build()
    {
        Box box = Box.createHorizontalBox();
        box.add(inputBox());
        box.add(Box.createHorizontalStrut(30));
        box.add(theList());
        box.add(Box.createHorizontalStrut(30));
        add(box);
        setSize(name, 100);
        setSize(start, 50);
        setSize(end, 50);
    }

    private Box inputBox()
    {  
        Box box = Box.createVerticalBox();
        box.add(pair("Name", name));
        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(pair("  Start", start));
        start.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(pair("    End", end));
        end.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }

    private JList theList()
    {   
        return list;
    }

    private Box pair(String label, JTextField field)
    {
        Box box = Box.createHorizontalBox();
        box.add(new JLabel(label));
        box.add(Box.createHorizontalStrut(10));
        box.add(field);
        box.setAlignmentX(0); //set the horizontal alignment to zero
        //box.add(Box.createHorizontalGlue());
        return box;
    }

    private void clear()
    {
        name.setText(null);
        start.setText(null);
        end.setText(null);
        list.clearSelection();
    }

    private class Listener implements ListSelectionListener
    {  
        public void valueChanged(ListSelectionEvent e)
        {
            //list.getSelectionModel().getLeadSelectionIndex();
            //dont throw an exception

            if (e.getValueIsAdjusting())
                return;
            if (list.getSelectedIndex() == -1)
                return;
            Boat boat = boats.boat(list.getSelectedIndex());
            boat.book(name.getText(), Integer.parseInt(start.getText()), Integer.parseInt(end.getText()));;
            clear();
        }
    }
}
