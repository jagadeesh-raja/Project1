package Servlets;
import DAOClasses.*;
import Entities.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet {
    EmployeeDAO employeeDAO = new EmployeeDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("read")) {
            readEmployee(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("create")) {
            createEmployee(req,resp);
        } else if(action.equalsIgnoreCase("update")) {
            updateEmployee(req, resp);
        } else if(action.equalsIgnoreCase("delete")) {
            deleteEmployee(req,resp);
        } else {
            resp.getWriter().write("Invalid action");
        }
    }

    private void readEmployee(HttpServletRequest req, HttpServletResponse resp) {
        EmployeeDAO.readEmployee();
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Employee employee =  new Employee(
                req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("company"),
                req.getParameter("department"),
                Double.parseDouble(req.getParameter("salary")),
                req.getParameter("gender"),
                req.getParameter("role")
        );

        employeeDAO.createEmployee(employee);
        resp.getWriter().write("Employee created successfully.");

    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String oldName = req.getParameter("oldName");
        String oldNameValue = req.getParameter("oldNameValue");
        String newChangeKey = req.getParameter("newChangeKey");
        String newChangeValue = req.getParameter("newChangeValue");

        // Call updateEmployee method in EmployeeDAO
        employeeDAO.updateEmployee(oldName, oldNameValue, newChangeKey, newChangeValue);

        resp.getWriter().write("Employee updated successfully.");
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String deleteKey = req.getParameter("deleteKey");
        String deleteValue = req.getParameter("deleteValue");

        employeeDAO.deleteFromEmployee(deleteKey, deleteValue);

        resp.getWriter().write("Employee deleted successfully.");
    }
}
