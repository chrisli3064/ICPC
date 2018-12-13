import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class visitfj {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("visitfg.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(br.readLine());
		int time = Integer.valueOf(st.nextToken());
		int[][] squares = new int[size][size];
		node[][][] nodes = new node[size][size][3];
		int counter =0;
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				squares[i][j] = Integer.parseInt(st.nextToken());
				for(int k=0; k<3; k++) {
					nodes[i][j][k] = new node(squares[i][j],counter,k);
				}
				counter++;
			}
			
		}
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				for(int k=0; k<3; k++) {
					if(j>0) {
						nodes[i][j][k].addNeighbor(nodes[i][j-1][(k+1)%3]);
					}
					if(j<size-1) {
						nodes[i][j][k].addNeighbor(nodes[i][j+1][(k+1)%3]);
					}
					if(i>0) {
						nodes[i][j][k].addNeighbor(nodes[i-1][j][(k+1)%3]);
					}
					if(i<size-1) {
						nodes[i][j][k].addNeighbor(nodes[i+1][j][(k+1)%3]);
					}
				}
			}
		}
		
		ArrayList<node> queue = new ArrayList<node>();
		nodes[0][0][0].setValue(0);
		queue.add(nodes[0][0][0]);
		while(queue.size()>0) {
			for(node node : queue.get(0).getNeighbors()) {
				if(node.getValue()==-1) {
					queue.add(node);
					node.setValue(queue.get(0).getValue() +(time+node.getStep()%3 ==0 ? node.getTime():0));
					
				}else {
					if(node.getValue()>queue.get(0).getValue() +(time+node.getStep()%3 ==0 ? node.getTime():0)) {
						queue.add(node);
						node.setValue(queue.get(0).getValue() +(time+node.getStep()%3 ==0 ? node.getTime():0));
					}
				}
				queue.remove(0);
			}
			pw.println(Math.min(nodes[size-1][size-1][2].getValue(),Math.min(nodes[size-1][size-1][0].getValue(),nodes[size-1][size-1][1].getValue())));
		}
	}
}
class node{
	int value;
	int time;
	int index;
	int step;
	ArrayList<node> neighbors;
	public node(int time, int index, int step ) {
		this.time = time;
		this.index = index;
		this.step = step;
		neighbors = new ArrayList<node>();
		value = -1;
	}
	public int getStep() {
		return step;
	}
	public int getIndex() {
		return index;
	}
	public int getTime() {
		return time;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public ArrayList<node> getNeighbors(){
		return neighbors;
	}
	public void addNeighbor(node node) {
		neighbors.add(node);
	}
	public void addNeighbors(node...nodes ) {
		for(node node : nodes) {
			addNeighbor(node);
		}
	}
}
class edge{

	public edge(){

	}
}
