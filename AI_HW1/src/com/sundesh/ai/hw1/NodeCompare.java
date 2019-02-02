/**
 * 
 */
package com.sundesh.ai.hw1;

import java.util.Comparator;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class NodeCompare implements Comparator<NodeUtils> { 

	@Override
	public int compare(NodeUtils o1, NodeUtils o2) {
		return (int) (o1.heuristic - o2.heuristic);
	}
}
