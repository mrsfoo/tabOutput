package com.zwb.tab;

import java.util.ArrayList;
import java.util.List;

public class Tab
{
    private String        tableTitle;
    private String        tableComment;
    private String        separator   = "=";
    private int           cellSpacing = 5;
    private TabRow         headline;
    private List<ITabRow>   rows        = new ArrayList<ITabRow>();
    private List<Integer> cellSizes;

    public Tab()
    {
    }

    public Tab(String title)
    {
        setTableTitle(title);
    }

    public Tab(String title,
                   String... headline)
    {
        setTableTitle(title);
        setHeadline(headline);
    }

    public void setTableTitle(String title)
    {
        this.tableTitle = title;
    }

    public void setTableComment(String comment)
    {
        this.tableComment = comment;
    }

    public void setSeparatorString(String separator)
    {
        this.separator = separator;
    }

    public void setCellSpacing(int space)
    {
        this.cellSpacing = space;
    }

    public void setHeadline(String... headline)
    {
        this.headline = new TabRow(headline);
    }

    public void addRow(String... cells)
    {
        if (cells.length != getColCount())
        {
            throw new IllegalArgumentException("Col count doesn't match! Table has "+getColCount()+" cols, but tried to add row with "+cells.length+" cols!");
        }
        this.rows.add(new TabRow(cells));
    }

    public String printFormatted()
    {
        List<Integer> lens = calculateCellSizes();
        int tot = calculateTotalTableSize(lens);

        String tab = "";

        if ((this.tableTitle != null) && !this.tableTitle.isEmpty())
        {
            tab += createSeparatorString(tot) + "\n";
            tab += this.tableTitle + "\n";
        }

        if ((this.tableComment != null) && !this.tableComment.isEmpty())
        {
            tab += createSeparatorString(tot) + "\n";
            tab += "Comment:" + "\n";
            tab += this.tableComment + "\n";
        }

        tab += createSeparatorString(tot) + "\n";
        tab += this.headline.printFormatted(lens) + "\n";
        tab += createSeparatorString(tot) + "\n";
        int i = 0;
        for (ITabRow row : this.rows)
        {
        	if(row.isSeparator())
        	{
        		tab += createSeparatorString(tot) + "\n";
        	}
        	else
        	{
        		tab += row.printFormatted(lens) + "\n";
        	}
        }
        tab += createSeparatorString(tot) + "\n";
        tab += createSeparatorString(tot) + "\n";
        return tab;
    }

    private int getColCount()
    {
        return this.headline.getColCount();
    }

    private List<ITabRow> getAllRowsIncludingHeadline()
    {
        List<ITabRow> list = new ArrayList<ITabRow>();
        list.add(this.headline);
        list.addAll(this.rows);
        return list;
    }

    private List<Integer> calculateCellSizes()
    {
        List<Integer> sizes = new ArrayList<Integer>();
        List<ITabRow> allRows = getAllRowsIncludingHeadline();
        for (int i = 0; i < getColCount(); i++)
        {
            int size = 0;
            for (ITabRow row : allRows)
            {
                size = Math.max(size, row.getCellSize(i));
            }
            sizes.add(size + this.cellSpacing);
        }
        return sizes;
    }

    private int calculateTotalTableSize(List<Integer> sizes)
    {
        int sizeInt = 0;
        for (int size : sizes)
        {
            sizeInt += size;
        }
        return sizeInt;
    }

    private String createSeparatorString(int size)
    {
        return TabUtils.repeatString(this.separator, size);
    }
    
    public void addSeparator()
    {
    	this.rows.add(new TabSeparator());
    }

}
