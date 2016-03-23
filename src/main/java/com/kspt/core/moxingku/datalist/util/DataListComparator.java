package com.kspt.core.moxingku.datalist.util;

import java.util.Comparator;

import com.kspt.core.moxingku.datalist.pojo.DataList;

public class DataListComparator implements Comparator<Object>{

	public int compare(Object o1, Object o2) {
		if(((DataList)o1).getOrder_index() > ((DataList)o2).getOrder_index()){   

            return 1;   
        }   
        if(((DataList)o1).getOrder_index() == ((DataList)o2).getOrder_index()){   
            return 0;   
       }   
       return -1;   
	}

	
}
