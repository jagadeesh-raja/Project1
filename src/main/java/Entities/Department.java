package Entities;

public class Department {
    String Name;

    @Override
    public String toString() {
        return "Department{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
