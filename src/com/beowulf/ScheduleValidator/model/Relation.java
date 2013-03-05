package com.beowulf.ScheduleValidator.model;

import java.util.ArrayList;

public class Relation
{
    private Lecture x1;
    private Lecture x2;
    private ArrayList<String> cons;

    public Relation(Lecture p1, Lecture p2)
    {
        x1 = p1;
        x2 = p2;

    }

    public ArrayList<String> addConstraint(String pConstraint)
    {
        cons.add(pConstraint);
        return cons;

    }

    public Lecture getX1()
    {
        return x1;
    }

    public void setX1(Lecture x1)
    {
        this.x1 = x1;
    }

    public Lecture getX2()
    {
        return x2;
    }

    public void setX2(Lecture x2)
    {
        this.x2 = x2;
    }

    public ArrayList<String> getCons()
    {
        return cons;
    }

    public void setCons(ArrayList<String> cons)
    {
        this.cons = cons;
    }
}
