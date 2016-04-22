package model;
import java.util.*;

public class Client
{
    public String name;
    public double money;

    public Client(String name, double money)
    {
        this.name = name;
        this.money = money;
    }

    public void charge(double amount)
    {
        this.money -= amount;
    }

    public static Client findByName(String name)
    {
        for(Client client: all())
        {    if (client.name.equals(name))
                return client; }
        return null;
    }

    private static LinkedList<Client> store = null;

    public static LinkedList<Client> all()
    {
        if (store == null) 
        {   store = new LinkedList<Client>();
            store.add(new Client("Homer", 100));
            store.add(new Client("Marge", 100));
            store.add(new Client("Bart", 100));
            store.add(new Client("Lisa", 100)); }
        return store; //store the data
    }
}
