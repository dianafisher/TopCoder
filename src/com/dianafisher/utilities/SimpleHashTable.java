package com.dianafisher.utilities;

/***
 * Hashtable implemented using only array.
 *
 */

public class SimpleHashTable
{
    Object[] keys;
    Object[] values;
    int M;

    public SimpleHashTable(int capacity)
    {
        this.M = capacity;
        keys = new Object[capacity];
        values = new Object[capacity];
    }

    public void put(Object key, Object value)
    {
        int hash = hash(key);
        // search for an empty slot, loop around to the beginning, if needed.
        int i;
        for (i = hash; keys[i] != null; i = (i+1) % M)
        {
            if (keys[i].equals(key))
            {
                // replace value
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
    }

    public Object get(Object key)
    {
        // search for key, starting at hash value
        int hash = hash(key);
        for (int i = hash; keys[i] != null; i = (i+1) % M)
        {
            if (keys[i].equals(key)) return values[i];
        }
        // key not found, return null
        return null;
    }

    public boolean contains(Object key)
    {
        return get(key) != null;
    }


    private int hash(Object key)
    {
        // & with 0x7fffffff to prevent negative numbers
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public static void main(String[] args) {
        SimpleHashTable table = new SimpleHashTable(100);
        table.put("Hamburger", "Helper");
        table.put("Rodney", "Reindeer");
        table.put("Rodney", "Bearcat");

        String result = (String)table.get("Rodney");
        System.out.println("result = " + result);

    }
}
