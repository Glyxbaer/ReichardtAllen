package com.beowulf.ScheduleValidator.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.allen.temporalintervalrelationships.Constraint;
import org.allen.temporalintervalrelationships.ConstraintNetwork;
import org.allen.temporalintervalrelationships.Node;

import com.beowulf.ScheduleValidator.model.*;

public class ConsistencyTest
{

    private boolean result;

    public ConsistencyTest(University uni)
    {

        result = testDefinitions(uni, true);

    }

    public boolean getResult()
    {
        return result;
    }

    static public boolean testDefinitions(University uni, boolean defsOnly)
    {

        ConstraintNetwork<Lecture> myConstraintNetwork = new ConstraintNetwork<Lecture>();
        HashMap<String, Node<Lecture>> hashedmap = new HashMap<String, Node<Lecture>>();
        HashMap<String, Constraint<Lecture>> constraintmap = new HashMap<String, Constraint<Lecture>>();

        String idBoth, id1, id2;
        boolean conflict = false;

        for (Relation rel : uni.getRelations())
        {
            Node<Lecture> nodeA;
            Node<Lecture> nodeB;

            id1 = rel.getX1().getName() + rel.getX1().getProf() + rel.getX1().getCourse();
            id2 = rel.getX2().getName() + rel.getX2().getProf() + rel.getX2().getCourse();
            idBoth = id1 + id2;

            if ((nodeA = hashedmap.get(id1)) == null)
            {

                nodeA = new Node<Lecture>(rel.getX1());
                hashedmap.put(id1, nodeA);
                myConstraintNetwork.addNode(nodeA);

            }

            if ((nodeB = hashedmap.get(id2)) == null)
            {

                nodeB = new Node<Lecture>(rel.getX2());
                hashedmap.put(id2, nodeB);
                myConstraintNetwork.addNode(nodeB);
            }

            Short vektor = 0;

            if (constraintmap.get(idBoth) != null)
            {
                vektor = constraintmap.get(idBoth).getConstraints();

                if (!defsOnly)
                {
                    for (Short allen : rel.getCons())
                    {
                        if ((short) (vektor & allen) == 0)
                        {
                            System.out.println("Conflict: " + rel.getX1().getName() + " and " + rel.getX2().getName() + ". The constraint says: " + rel.getX1().getName() + " "
                                    + myConstraintNetwork.getConstraintStringFromConstraintShort(vektor) + " " + rel.getX2().getName());
                            conflict = true;
                        }
                    }
                    vektor = 0;
                }

            }

            for (Short allen : rel.getCons())
            {
                vektor = (short) (vektor | allen);
            }

            constraintmap.put(idBoth, new Constraint<Lecture>(nodeA, nodeB, vektor));

        }

        for (Constraint<Lecture> value : constraintmap.values())
        {
            // System.out.println("Constraint: " +
            // value.getSourceNode().getIdentifier().getName() + " "
            // +
            // myConstraintNetwork.getConstraintStringFromConstraintShort(value.getConstraints())
            // + " " + value.getDestinationNode().getIdentifier().getName());
            myConstraintNetwork.addConstraint(value);
        }

        return defsOnly ? myConstraintNetwork.pathConsistency() : conflict;

    }

}
