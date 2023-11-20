package co.banco.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.banco.DAO.BillDAO;
import co.banco.DAO.UserDAO;
import co.banco.model.Bill;
import co.banco.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class BillServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

    private BillDAO billDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {

		this.billDAO = new BillDAO();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();
		
		System.out.println(action);
		try {
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertBill(request, response);
			break;

		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateBill(request, response);
			break;
		default:
			listBills(request, response);
			break;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("billForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int id = Integer.parseInt(request.getParameter("id"));
		Bill billActual = billDAO.selectByID(id);

		request.setAttribute("bill", billActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("billForm.jsp");
		dispatcher.forward(request, response);
	}

	private void listBills(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int id = Integer.parseInt(request.getParameter("id"));
		List<Bill> bills = billDAO.selectAll(id);
		request.setAttribute("listBills", bills);

		RequestDispatcher dispatcher = request.getRequestDispatcher("bill_list.jsp");
		dispatcher.forward(request, response);
		
		
	}
	private void insertBill(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String date = request.getParameter("date");
		int value= Integer.parseInt(request.getParameter("value"));
		String observation= request.getParameter("observation");
		short type= Short.parseShort(request.getParameter("tpe"));
		
		Bill bill= new Bill(date,value,observation,type);
		billDAO.insert(bill);
		
		response.sendRedirect("bill_list.jsp");
	}
	
	private void updateBill(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String date = request.getParameter("date");
		int value= Integer.parseInt(request.getParameter("value"));
		String observation= request.getParameter("observation");
		short type= Short.parseShort(request.getParameter("tpe"));
		
		Bill bill= new Bill(id,date,value,type,observation);
		billDAO.update(bill);
		
		response.sendRedirect("bill_list.jsp");
	}

}
