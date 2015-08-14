package book.ch07;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertLenientEquals;

/**
 * Created by Sangjun on 2015. 8. 14..
 */
public class EmployeeSearchServletTest {
    @Test
    public void testSearchByEmpid() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.addParameter("empid", "5874");

        SearchBiz biz = mock(SearchBiz.class);
        Employee expectedEmployee = new Employee("박성철", "5874", "fupfin", "회장");
        when(biz.getEmployeeByEmpid(anyString())).thenReturn(expectedEmployee);

        EmployeeSearchServlet searchServlet = new EmployeeSearchServlet();
        searchServlet.setModel(biz);
        searchServlet.service(request, response);

        Employee employee = (Employee) request.getAttribute("employee");
        assertLenientEquals(expectedEmployee, employee);

        assertEquals("/SearchResult.jsp", response.getForwardedUrl());
    }
}
