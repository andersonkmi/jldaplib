package org.sharpsw.ldap.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class DistinguishedNameBuilder {
	private static final String DN_ENTRY_ITEM_DELIMITER = ",";
	private static final String DN_ENTRY_DELIMITER = "=";
	
	public DistinguishedName build(String dnString) {
		DistinguishedName dn = new DistinguishedName();
		Map<String, String> entries = this.splitEntries(dnString);
		this.createNodes(dn, entries);
		return dn;
	}
	
	private Map<String, String> splitEntries(String dn) {
		Map<String, String> entries = new LinkedHashMap<>();
		
		List<String> items = new LinkedList<>();
		StringTokenizer dnTokenizer = new StringTokenizer(dn, DN_ENTRY_ITEM_DELIMITER);
		while(dnTokenizer.hasMoreTokens()) {
			items.add(dnTokenizer.nextToken().trim());
		}
		
		for(String item : items) {
			StringTokenizer tokenizer = new StringTokenizer(item, DN_ENTRY_DELIMITER);
			if(tokenizer.countTokens() == 2) {
				String nodeType = tokenizer.nextToken().trim();
				String nodeValue = tokenizer.nextToken().trim();
				
				entries.put(nodeType, nodeValue);
			}
		}
		return entries;
	}
	
	private void createNodes(DistinguishedName dn, Map<String, String> entries) {
		Stack<DistinguishedNameNode> items = new Stack<>();
		
		Set<Map.Entry<String, String>> elements = entries.entrySet();
		for(Map.Entry<String, String> entry : elements) {
			if(entry.getKey().equalsIgnoreCase(DistinguishedNameNodeType.DC.getCode())) {
				DistinguishedNameNode node = new DistinguishedNameNode(DistinguishedNameNodeType.DC, entry.getValue());
				items.push(node);
			} else if(entry.getKey().equalsIgnoreCase(DistinguishedNameNodeType.CN.getCode())) {
				DistinguishedNameNode node = new DistinguishedNameNode(DistinguishedNameNodeType.CN, entry.getValue());
				items.push(node);
			} else if(entry.getKey().equalsIgnoreCase(DistinguishedNameNodeType.OU.getCode())) {
				DistinguishedNameNode node = new DistinguishedNameNode(DistinguishedNameNodeType.OU, entry.getValue());
				items.push(node);
			}
		}
		
		while(!items.empty()) {
			dn.push(items.pop());
		}
	}
}
