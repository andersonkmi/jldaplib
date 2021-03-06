package org.codecraftlabs.ldap.util;

import java.util.Stack;

public class DistinguishedName {
	private Stack<DistinguishedNameNode> elements;
	
	public DistinguishedName() {
		this.elements = new Stack<DistinguishedNameNode>();
	}

	public void push(DistinguishedNameNode node) {
		this.elements.push(node);
	}
	
	public DistinguishedNameNode peek() {
		return this.elements.peek();
	}
	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("DistinguishedName [");
		for(DistinguishedNameNode node : this.elements) {
			buffer.append("node = '").append(node.toString()).append("', ");			
		}
		buffer.append("]");
		return buffer.toString();
	}
}
