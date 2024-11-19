package sda_Lab_Mid;

import java.util.ArrayList;
import java.util.List;

public class hostelSystem {
    public static void main(String[] args) {
        Student std1=new Student("Huzaifa Ahmed", "Peshawar",  true, true);
        filterPipeLine pipeline = new filterPipeLine();
        pipeline.addFilter(new eligible());  // Adding eligibility filter
        pipeline.addFilter(new priorityFilter());  // Adding priority filter

        Observer OBS = new Observer();
        OBS.addObserver(new Observer());

        pipeline.process(std1); 
        OBS.notifyObservers(std1);

        System.out.println("Student Name: " + std1.name);
        System.out.println("City: " + std1.city);
        System.out.println("Eligibility: " + std1.isEligible);
        System.out.println("Priority: " + std1.priority);
        System.out.println("Fees Paid: " + std1.fees);
    }
}

class Student {
    String name;
    String city;
    int priority;
    boolean isEligible;
    boolean isRemoteArea;
    boolean fees;

    public Student(String name, String city, boolean isEligible, boolean isRemoteArea) {
        this.name = name;
        this.city = city;
        
        this.isEligible = isEligible;
        this.isRemoteArea = isRemoteArea;

    }
}

interface observer{
void update(Student std);
}

interface Filter {
    void process(Student Std);
}


class eligible implements Filter{
    public void process(Student std) {
        if (std.isEligible) {
            std.fees = true;

        } else {
            std.fees = false;
        }
    }
}

class priorityFilter implements Filter{
    public void process(Student std) {
        if (std.isRemoteArea && std.isEligible) {
            std.priority = 1;// highest
        } else if (std.isEligible) {
            std.priority = 2;// middle
        } else {
            std.priority = 3;// lowest
        }
    }
}

class filterPipeLine{
    public List<Filter> filters = new ArrayList<>();

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void process(Student std) {
        for (int i = 0; i < filters.size(); i++) {
            filters.get(i).process(std);
        }
    }
}

class Observer implements observer{
    public List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(Student std) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(std);
        }
    }
    public void update(Student std)
    {
        System.out.println("Student was updated:"+std.name);
    }
}


