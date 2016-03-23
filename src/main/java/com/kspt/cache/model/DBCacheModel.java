package com.kspt.cache.model;

public class DBCacheModel {
	public final static String symbol_like="like";
	//符号
	private String symbol;
	//值
	private Object value;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public DBCacheModel(){
	}
	public DBCacheModel(Object value){
		this.value=value;
	}
	public DBCacheModel(Object value,String symbol){
		this.value=value;
		this.symbol=symbol;
	}
}
