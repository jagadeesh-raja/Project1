package DAOClasses;

import com.mongodb.MongoClient;
import Entities.Company;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Iterator;

public class CompanyDAO {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public CompanyDAO() {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("company");
    }

    public void createCompany(Company company) {
        Document document = new Document()
                .append("name", company.getName())
                .append("address", company.getAddress());

        try {
            MongoCollection<Document> collection = db.getCollection("companyDbCollection");
            collection.insertOne(document);
            System.out.println("The data is added Succesfully");
        } finally {
            System.out.println("Now Move On");
        }
    }

    public void readCompany() {
        MongoCollection<Document> collection = db.getCollection("companyDbCollection");
        FindIterable<Document> iterdoc = collection.find();
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }

    public void updateCompany(String oldName , String oldNameValue, String newChangeKey, String newChangeValue) {
        MongoCollection<Document> collection = db.getCollection("companyDbCollection");
        collection.updateMany(Filters.eq(oldName, oldNameValue), Updates.set(newChangeKey, newChangeValue));
        System.out.println("The company details after updating are : ");
        FindIterable<Document> iterdoc = collection.find();
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void deleteDataFromCompany(String deleteKey, String deleteValue) {
        MongoCollection<Document> collection = db.getCollection("companyDbCollection");
        collection.deleteOne(Filters.eq(deleteKey,deleteValue));
        System.out.println("The record is deleted");
        System.out.println("The company details after deleting are : ");
        FindIterable<Document> iterdoc = collection.find();
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }

    public void readParticularRecord(Document query) {
        MongoCollection<Document> collection = db.getCollection("companyDbCollection");
        System.out.println("The records that matches your search");
        FindIterable<Document> iterdoc = collection.find(query);
        Iterator it = iterdoc.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }




}
