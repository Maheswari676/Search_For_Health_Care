package com.healthCare.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/Admin_Registration_Servlet")
public class Admin_Registration_Servlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
 
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter out=response.getWriter();
String fname=request.getParameter("firstname");
String lname=request.getParameter("lastname");
int age=Integer.parseInt(request.getParameter("age"));
String gender=request.getParameter("gender");
String contactnumber=request.getParameter("contactnumber");
String adminid=request.getParameter("adminid");
String password=request.getParameter("psw");
HttpSession session=request.getSession();
boolean flag=false;

try
{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Search_For_Health_Care","root","maheswari");
String query = "insert into Admin_Registration_Details values(?,?,?,?,?,?,?)";
PreparedStatement ptmt = con.prepareStatement(query);
ptmt.setString(1, fname);
ptmt.setString(2, lname);
ptmt.setInt(3, (int)age);
ptmt.setString(4, gender);
ptmt.setString(5, contactnumber);
ptmt.setString(6, adminid);
ptmt.setString(7, password);
ptmt.executeUpdate();
String query1 = "insert into Admin_Login_Credentials values(?,?)";
PreparedStatement ptmt1 = con.prepareStatement(query1);
ptmt1.setString(1, adminid);
ptmt1.setString(2, password);
ptmt1.executeUpdate();
response.sendRedirect("./Admin_Login.html");
}
    catch(Exception p)
{
    out.print(p);
}
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
}

}