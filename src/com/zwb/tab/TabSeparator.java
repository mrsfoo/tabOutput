package com.zwb.tab;

import java.util.List;

public class TabSeparator implements ITabRow
{
	@Override
    public boolean isSeparator()
    {
    	return true;
    }

	@Override
	public int getColCount() {
		return 0;
	}

	@Override
	public String getCellContent(int i) {
		return "";
	}

	@Override
	public int getCellSize(int i) {
		return 0;
	}

	@Override
	public String printFormatted(List<Integer> lens) {
		return "===";
	}

}
