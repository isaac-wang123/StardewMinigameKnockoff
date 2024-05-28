package ai;

import java.util.ArrayList;

import main.GamePanel;

public class PathFinder {
	GamePanel gp;
	Node[][] nodes;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	boolean goalReached = false;
	int step = 0;
	
	public PathFinder (GamePanel gp) {
		this.gp = gp;
		instantiateNodes();
	}
	
	public void instantiateNodes() {
		nodes = new Node[gp.maxScreenCol][gp.maxScreenRow];
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[0].length; j++) {
				nodes[i][j] = new Node(i , j);
			}
		}
	}
	
	public void resetNodes() {
		for(Node[] row : nodes) {
			for(Node node : row) {
				node.open = false;
				node.solid = false;
				node.checked = false;
				node.parent = null;
			}
		}
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
		resetNodes();
		startNode = nodes[startCol][startRow];
		currentNode = startNode;
		goalNode = nodes[goalCol][goalRow];
		
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[0].length; j++) {
				if(gp.tileManager.tiles[i][j].collision) {
					nodes[i][j].solid = true;
				}
				getCost(nodes[i][j]);
			}
		}
	}
	
	public void getCost(Node node) {
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		
		node.fCost = node.gCost + node.hCost;
	}
	
	public boolean search() {
		while(goalReached == false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			if(row > 0) {
				openNode(nodes[col][row - 1]);
			}
			
			if(col > 0) {
				openNode(nodes[col - 1][row]);
			}
			if(row < gp.maxScreenCol - 1) {
				openNode(nodes[col][row + 1]);
			}
			
			if(col < gp.maxScreenRow - 1) {
				openNode(nodes[col + 1][row]);
			}
			
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for(int i= 0; i < openList.size(); i++){
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				} else if(openList.get(i).fCost==bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			
			if(openList.size() == 0) {
				break;
			}
			
			currentNode = openList.get(bestNodeIndex);
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			step++;
		}
		
		return goalReached;
	}
	
	public void openNode(Node node) {
		if(!(node.open||node.checked||node.solid)) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void trackThePath() {
		Node current = goalNode;
		while(current != startNode) {
			pathList.add(0, current);
			current = current.parent;
		}
	}
}
