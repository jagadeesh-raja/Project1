package DAOClasses;

import Entities.Department;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Iterator;

public class DepartmentDAO {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public DepartmentDAO() {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("Department");
    }

    public void createDepartment(Department department) {
        Document document = new Document()
                .append("name", department.getName());

        try {
            MongoCollection<Document> collection = db.getCollection("departmentDBCollection");
            collection.insertOne(document);
            System.out.println("The data is added Succesfully");
        } finally {
            System.out.println("Now Move On");
        }
    }

    public void readDepartment() {
        MongoCollection<Document> collection = db.getCollection("companyDbCollection");
        FindIterable<Document> iterdoc = collection.find();
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }

    public void updateDepartment(String oldName , String oldNameValue, String newChangeKey, String newChangeValue) {
        MongoCollection<Document> collection = db.getCollection("departmentDBCollection");
        collection.updateMany(Filters.eq(oldName, oldNameValue), Updates.set(newChangeKey, newChangeValue));
        System.out.println("The Department details after updating are : ");
        FindIterable<Document> iterdoc = collection.find();
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void deleteDataFromDepartment(String deleteKey, String deleteValue) {
        MongoCollection<Document> collection = db.getCollection("departmentDBCollection");
        collection.deleteMany(Filters.eq(deleteKey,deleteValue));
        System.out.println("The record is deleted");
        System.out.println("The Department details after deleting are : ");
        FindIterable<Document> iterdoc = collection.find();
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
    public void readParticularRecord(Document query) {
        MongoCollection<Document> collection = db.getCollection("departmentDBCollection");
        System.out.println("The records that matches your search");
        FindIterable<Document> iterdoc = collection.find(query);
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
