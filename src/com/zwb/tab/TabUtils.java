package com.zwb.tab;

public class TabUtils
{
    public static String createTableRow(String[] tableContents,
                                        int[] tableSizes)
    {
        if (tableContents.length != tableSizes.length)
        {
            throw new IllegalArgumentException("Illegal table size array length");
        }
        String s = "";
        for (int i = 0; i < tableContents.length; i++)
        {
            s += inflateString(tableContents[i], tableSizes[i]);
        }
        return s;
    }

    public static String createCSVRow(String[] tableContents)
    {
        String s = "";
        for (int i = 0; i < tableContents.length; i++)
        {
            s += ";";
        }
        return s;
    }

    public static String inflateString(String s,
                                       int len)
    {
        if (s == null)
        {
            s = "<null>";
        }
        if (s.isEmpty())
        {
            s = "<>";
        }
        if (s.length() >= len)
        {
            return s;
        }
        return s + repeatString(" ", (len - s.length()));
    }

    public static String repeatString(String s,
                                      int times)
    {
        String repeated = "";
        for (int i = 0; i < times; i++)
        {
            repeated += s;
        }
        return repeated;
    }

}
