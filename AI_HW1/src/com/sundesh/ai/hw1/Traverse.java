/**
 * 
 */
package com.sundesh.ai.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class Traverse {

	public void startUninformerdSearch(ArrayList<String> list, String origin, String destination) {
		
		NodeUtils parentNode = new NodeUtils (null, 0, 0, origin, 0);
		ArrayList<NodeUtils> fringe = new ArrayList<NodeUtils>();
		
		fringe.add(parentNode);
		NodeUtils goal = null;
		int nodes_expanded = 0;
		
		ArrayList<String> closedArray = new ArrayList<String>();
		
		while (!(fringe.isEmpty()) && goal == null) {
			
			NodeUtils currentNode = fringe.remove(0);
			nodes_expanded++;
			
			if (currentNode.currentState.equals(destination)) {
				goal = currentNode;
			}
			else {
				if(closedArray.contains(currentNode.currentState)){}
				
				else {
					closedArray.add(currentNode.currentState);
					
					for (String temp : list) {
						if (temp.contains(currentNode.currentState)) {
							
							StringTokenizer tokenString = new StringTokenizer(temp, " ");
							String city1 = tokenString.nextToken();
							String city2 = tokenString.nextToken();
							float cost = (float) Integer.parseInt(tokenString.nextToken());
							
							if (currentNode.currentState.equals(city1)) {
								NodeUtils city = new NodeUtils(currentNode, currentNode.depth+1, currentNode.pathCost+cost, city2, 0);
								fringe.add(city);
							}
							else {
								NodeUtils city = new NodeUtils(currentNode, currentNode.depth+1, currentNode.pathCost+cost, city1, 0);
								fringe.add(city);
							}
							
						}
					}
					Collections.sort(fringe);
				}
			}
		}
		
		if (goal == null) {
			System.out.println("Nodes Expanded = " + nodes_expanded);
			System.out.println("Distance : Infinity \nRoute : \nNone");
		}
		else {
			goal.child = null;
			System.out.println("Nodes Expanded = " + nodes_expanded);
			System.out.println("Distance : " + goal.pathCost + "\nRoute : ");
			while (goal.parent != null) {
				goal.parent.child = goal;
				goal = goal.parent;
			}
			while (goal.child != null) {
				System.out.println(goal.currentState.toUpperCase() + " to " + goal.child.currentState.toUpperCase() + " " + (goal.child.pathCost - goal.pathCost));
				goal = goal.child;
			}
		}
		
	}

	public void startInformedSearch(ArrayList<String> inputList, String origin, String destination,
			ArrayList<String> heuristicList) {

		
		NodeUtils parentNode = new NodeUtils (null, 0, 0, origin, 0);
		ArrayList<NodeUtils> fringe = new ArrayList<NodeUtils>();
		
		fringe.add(parentNode);
		NodeUtils goal = null;
		int expanded = 0;
		float heuristicValue = 0;
		
		ArrayList<String> closedArray = new ArrayList<String>();
		
		while (!(fringe.isEmpty()) && goal == null) {
			
			NodeUtils currentNode = fringe.remove(0);
			expanded++;
			
			if (currentNode.currentState.equals(destination)) {
				goal = currentNode;
			}
			else {
				if(!(closedArray.contains(currentNode.currentState))){
					closedArray.add(currentNode.currentState);
					float cost;
					for (String temp : inputList) {
						if (temp.contains(currentNode.currentState)) {
							StringTokenizer tokenString = new StringTokenizer(temp, " ");
							String city1 = tokenString.nextToken();
							String city2 = tokenString.nextToken();
							cost = (float) Integer.parseInt(tokenString.nextToken());
							if (currentNode.currentState.equals(city1)) {
								for (String hueristic : heuristicList) {
									StringTokenizer heuristicTokenString = new StringTokenizer(hueristic, " ");
									String city = heuristicTokenString.nextToken();
									float heuristicCost = (float) Integer.parseInt(heuristicTokenString.nextToken());
									if (city.equalsIgnoreCase(city2)) {
										heuristicValue = heuristicCost;
										NodeUtils cityValue = new NodeUtils(currentNode, (int)(currentNode.pathCost+cost), currentNode.pathCost+cost, city2, currentNode.pathCost+cost+heuristicValue);
										fringe.add(cityValue);
									}
								}
							}
							else if (currentNode.currentState.equals(city2)) {
								for (String hueristic : heuristicList) {
									StringTokenizer heuristicTokenString = new StringTokenizer(hueristic, " ");
									String city = heuristicTokenString.nextToken();
									float heuristicCost = (float) Integer.parseInt(heuristicTokenString.nextToken());
									if (city.equalsIgnoreCase(city1)) {
										heuristicValue = heuristicCost;
										NodeUtils cityValue = new NodeUtils(currentNode, (int)(currentNode.pathCost+cost), currentNode.pathCost+cost, city1, currentNode.pathCost+cost+heuristicValue);
										fringe.add(cityValue);
									}
								}
							}
						}
					}
					Collections.sort(fringe, new NodeCompare());
				}

			}
		}
		
		if (goal == null) {
			System.out.println("Nodes Expanded = " + expanded);
			System.out.println("Distance : Infinity \nRoute : \nNone");
		}
		else {
			goal.child = null;
			System.out.println("Nodes Expanded = " + expanded);
			System.out.println("Distance : " + (goal.pathCost) + "\nRoute : ");
			while (goal.parent != null) {
				goal.parent.child = goal;
				goal = goal.parent;
			}
			while (goal.child != null) {
				System.out.println(goal.currentState.toUpperCase() + " to " + goal.child.currentState.toUpperCase() + " " + (goal.child.pathCost - goal.pathCost));
				goal = goal.child;
			}
		}
	}
}