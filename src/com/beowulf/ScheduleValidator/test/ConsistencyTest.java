package com.beowulf.ScheduleValidator.test;

import org.allen.temporalintervalrelationships.Constraint;
import org.allen.temporalintervalrelationships.ConstraintNetwork;
import org.allen.temporalintervalrelationships.Node;

import com.beowulf.ScheduleValidator.model.*;

public class ConsistencyTest {

	private boolean result;
	private String msg;

	public ConsistencyTest(University uni) {

		test(uni);

	}

	public boolean getResult() {
		return result;
	}

	public String getMsg() {
		return msg;
	}

	private boolean test(University uni) {

		// TODO test everything for consistency here
		result = false;
		msg = "Alles falsch";
		
		ConstraintNetwork<String> myConstraintNetwork = new ConstraintNetwork<String>();
		
		for(Relation rel : uni.getRelations())
		{
		    Node<String> nodeA = new Node<String>(rel.getX1());
		    myConstraintNetwork.addNode(nodeA);
		    Node<String> nodeB = new Node<String>(rel.getX2());
		    myConstraintNetwork.addNode(nodeB);
		    
		    
		    Constraint<String> constraintAB = new Constraint<String> (nodeA,nodeB,ConstraintNetwork.bin_equals);
		    
		    
		}
        
        Node<String> nodeA = new Node<String>("A");
        myConstraintNetwork.addNode(nodeA);
        Node<String> nodeB = new Node<String>("B");
        myConstraintNetwork.addNode(nodeB);
        Node<String> nodeC = new Node<String>("C");
        myConstraintNetwork.addNode(nodeC);
        Node<String> nodeD = new Node<String>("D");
        myConstraintNetwork.addNode(nodeD);
        Constraint<String> constraintAB = new Constraint<String> (nodeA,nodeB,ConstraintNetwork.bin_equals);
        myConstraintNetwork.addConstraint(constraintAB);
        Constraint<String> constraintBC = new Constraint<String> (nodeB,nodeC,ConstraintNetwork.bin_equals);
        myConstraintNetwork.addConstraint(constraintBC);
        Constraint<String> constraintCD = new Constraint<String> (nodeC,nodeD,ConstraintNetwork.bin_equals);
        myConstraintNetwork.addConstraint(constraintCD);
        assertEquals(myConstraintNetwork.pathConsistency(),true);      
        Constraint<String> constraintAD = new Constraint<String> (nodeA,nodeD,ConstraintNetwork.bin_overlaps);
        myConstraintNetwork.addConstraint(constraintAD);
        assertEquals(myConstraintNetwork.pathConsistency(),false);  
		return result;
		
	}

}
