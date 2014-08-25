package com.zwb.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabRow implements ITabRow
{
    private List<String> cells = new ArrayList<String>();

    public TabRow(String... cells)
    {
    	for(String c: cells)
    	{
    		if(c!=null)
    		{
    			this.cells.add(c);
    		}
    		else
    		{
    			this.cells.add("<null>");
    		}
    	}
//        this.cells = Arrays.asList(cells);
    }

    public int getColCount()
    {
        return this.cells.size();
    }

    public String getCellContent(int i)
    {
        return this.cells.get(i);
    }

    public int getCellSize(int i)
    {
        return this.cells.get(i).length();
    }

    public String printFormatted(List<Integer> lens)
    {
        if (lens.size() != this.cells.size())
        {
            throw new IllegalArgumentException("Col count doesn't match!");
        }
        String row = "";
        for (int i = 0; i < this.cells.size(); i++)
        {
            row += TabUtils.inflateString(this.cells.get(i), lens.get(i));
        }
        return row;
    }
    
    public boolean isSeparator()
    {
    	return false;
    }
}
