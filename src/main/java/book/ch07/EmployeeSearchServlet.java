package book.ch07;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sangjun on 2015. 8. 14..
 */
public class EmployeeSearchServlet extends HttpServlet {
    private SearchBiz searchBiz;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = this.searchBiz.getEmployeeByEmpid(req.getParameter("empid"));

        req.setAttribute("employee", employee);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/SearchResult.jsp");
        dispatcher.forward(req, resp);
    }

    public void setModel(SearchBiz biz) {
        this.searchBiz = biz;
    }
}
