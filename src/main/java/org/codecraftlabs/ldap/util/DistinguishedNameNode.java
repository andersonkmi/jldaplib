package org.codecraftlabs.ldap.util;

public class DistinguishedNameNode {
	private String value;
	private DistinguishedNameNodeType nodeType;	
	
	public DistinguishedNameNode(DistinguishedNameNodeType type, final String value) {
		this.nodeType = type;
		this.value = value;
	}
		
	public DistinguishedNameNodeType getNodeType() {
		return this.nodeType;
	}
	
	public void setDistinguishedNameNodeType(DistinguishedNameNodeType type) {
		this.nodeType = type;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		
		if(!this.getClass().equals(other.getClass())) {
			return false;
		}
		
		DistinguishedNameNode instance = (DistinguishedNameNode) other;
		return this.getNodeType() == instance.getNodeType() && 
			   this.getValue().equals(instance.getValue());
	}
	
	public int hashCode() {
		return this.value.hashCode() + this.nodeType.hashCode();
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DistinguishedNameNode = [type = '").append(this.getNodeType().getCode()).append("', value = '").append(this.getValue()).append("'").append("]");
		return buffer.toString();
	}
}
