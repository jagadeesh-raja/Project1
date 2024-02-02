package DAOClasses;

import Entities.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Base64;
import java.util.Iterator;


public class EmployeeDAO {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public EmployeeDAO() {
        // Initialize MongoDB connection in the constructor
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("EmployeeDB");
    }

    public void createEmployee(Employee employee) {
        // Create a new document with employee details
        Document document = new Document()
                .append("id", employee.getId())
                .append("name", employee.getName())
                .append("company", employee.getCompany())
                .append("department", employee.getDepartment())
                .append("salary", employee.getSalary())
                .append("gender", employee.getGender())
                .append("role" , employee.getRole());



        // Connect to MongoDB
        try {
            // Get or create the collection
            MongoCollection<Document> collection = db.getCollection("EmployeeDpCollection");

            // Insert the document into the collection
            collection.insertOne(document);

            System.out.println("Entities.Employee document inserted successfully.");
        } finally {
            System.out.println("The Insertion process is completed");
        }
    }

    public static void readEmployee() {
        MongoCollection<Document> collection = db.getCollection("EmployeeDpCollection");
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void updateEmployee(String oldName , String oldNameValue, String newChangeKey, String newChangeValue) {
        MongoCollection<Document> collection = db.getCollection("EmployeeDpCollection");
        collection.updateMany(Filters.eq(oldName, oldNameValue), Updates.set(newChangeKey, newChangeValue));
        readEmployee();
    }

    public void deleteFromEmployee(String deleteKey, String deleteValue) {

            MongoCollection<Document> collection = db.getCollection("EmployeeDpCollection");
            collection.deleteMany(Filters.eq(deleteKey, deleteValue));
            System.out.println("The record is deleted");
            System.out.println("The Employee details after deleting are : ");
            FindIterable<Document> iterdoc = collection.find();
            Iterator it = iterdoc.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

    }

    public Employee readParticularRecord(Document query) {
        MongoCollection<Document> collection = db.getCollection("EmployeeDpCollection");
        System.out.println("The records that matches your search");
        FindIterable<Document> iterdoc = collection.find(query);
        Iterator it = iterdoc.iterator();
        Employee employee = new Employee();
        while(it.hasNext()) {
            employee = (Employee) it.next();
            System.out.println(it.next());
        }
        return employee;
    }
}
