package com.zwb.tab;

import java.util.List;

public interface ITabRow 
{
    public int getColCount();
    public String getCellContent(int i);
    public int getCellSize(int i);
    public String printFormatted(List<Integer> lens);
    public boolean isSeparator();
}
