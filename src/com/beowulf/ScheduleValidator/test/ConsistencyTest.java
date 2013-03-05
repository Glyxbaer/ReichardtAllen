package com.beowulf.ScheduleValidator.test;


import java.util.HashMap;

import org.allen.temporalintervalrelationships.Constraint;
import org.allen.temporalintervalrelationships.ConstraintNetwork;
import org.allen.temporalintervalrelationships.Node;

import com.beowulf.ScheduleValidator.model.*;

public class ConsistencyTest
{

    private boolean result;

    public ConsistencyTest(University uni)
    {

        result = testDefinitions(uni);

    }

    public boolean getResult()
    {
        return result;
    }


    static public boolean testDefinitions(University uni)
    {

        ConstraintNetwork<Lecture> myConstraintNetwork = new ConstraintNetwork<Lecture>();
        HashMap<String, Node<Lecture>> hashedmap = new HashMap<String,Node<Lecture>>();
        
        
        for (Relation rel : uni.getRelations())
        {
            Node<Lecture> nodeA;
            Node<Lecture> nodeB;
           
            
            if((nodeA = hashedmap.get(rel.getX1().getName())) == null)
            {
                
                nodeA = new Node<Lecture>(rel.getX1());
                hashedmap.put(rel.getX1().getName(), nodeA);
                myConstraintNetwork.addNode(nodeA);
            
            }
            
            if((nodeB = hashedmap.get(rel.getX2().getName())) == null)
            {
                
                nodeB = new Node<Lecture>(rel.getX2());
                hashedmap.put(rel.getX2().getName(), nodeB);
                myConstraintNetwork.addNode(nodeB);
            }
            
            
            Short vektor = 0;
            for (Short allen : rel.getCons())
            {
                vektor = (short) (vektor | allen);
                
            }
            myConstraintNetwork.addConstraint(new Constraint<Lecture>(nodeA, nodeB, vektor));

        }

        return myConstraintNetwork.pathConsistency();

    }

}
