package com.zcc.aditya.ZccAditya.Model;

public class ZccLinks {

	private String next;

	private String prev;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "ZccLinks [next=" + next + ", prev=" + prev + "]";
	}

}
