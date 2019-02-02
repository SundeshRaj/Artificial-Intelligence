/**
 * 
 */
package com.sundesh.ai.hw1;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class NodeUtils implements Comparable<NodeUtils> {
	
	NodeUtils parent;
	NodeUtils child;
	int depth;
	float pathCost;
	float heuristic;
	String currentState;
	
	public NodeUtils (NodeUtils parent, int depth, float pathCost, String currentState, float heuristic) {
		this.parent = parent;
		this.depth = depth;
		this.pathCost = pathCost;
		this.heuristic = heuristic;
		this.currentState = currentState;
	}

	@Override
	public int compareTo(NodeUtils o) {
		return Float.compare(this.pathCost, o.pathCost);
	}

}
