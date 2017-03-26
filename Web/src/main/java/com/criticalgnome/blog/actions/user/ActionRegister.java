package com.criticalgnome.blog.actions.user;

import com.criticalgnome.blog.dao.implement.RoleDAO;
import com.criticalgnome.blog.dao.implement.UserDAO;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.MD5;
import com.criticalgnome.blog.utils.RegexChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public class ActionRegister implements com.criticalgnome.blog.actions.Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        Boolean error = false;
        String email = request.getParameter("email");
        String password = MD5.md5Encode(request.getParameter("password"));
        String nickName = request.getParameter("nickName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (email.equals("")) {
            error = true;
            request.setAttribute("emailClass", "has-error");
            request.setAttribute("emailMessage", "Email cannot be empty");
        } else {
            request.setAttribute("emailClass", "has-success");
            request.setAttribute("emailValue", email);
        }

        if (password.equals("")) {
            error = true;
            request.setAttribute("passwordClass", "has-error");
            request.setAttribute("passwordMessage", "Password cannot be empty");
        } else {
            request.setAttribute("passwordClass", "has-success");
            request.setAttribute("passwordValue", password);
        }

        if (nickName.equals("")) {
            error = true;
            request.setAttribute("nickNameClass", "has-error");
            request.setAttribute("nickNameMessage", "Nickname cannot be empty");
        } else if (!RegexChecker.checkNickNameWithRegExp(nickName)) {
            error = true;
            request.setAttribute("nickNameClass", "has-error");
            request.setAttribute("nickNameMessage", "Nickname must contain letters and digits only");
            request.setAttribute("nickNameValue", nickName);
        } else {
            request.setAttribute("nickNameClass", "has-success");
            request.setAttribute("nickNameValue", nickName);
        }

        if (firstName.equals("")) {
            error = true;
            request.setAttribute("firstNameClass", "has-error");
            request.setAttribute("firstNameMessage", "First name cannot be empty");
        } else if (!RegexChecker.checkNameWithRegExp(firstName)) {
            error = true;
            request.setAttribute("firstNameClass", "has-error");
            request.setAttribute("firstNameMessage", "First name must contain letters only");
            request.setAttribute("firstNameValue", firstName);
        } else {
            request.setAttribute("firstNameClass", "has-success");
            request.setAttribute("firstNameValue", firstName);
        }

        if (lastName.equals("")) {
            error = true;
            request.setAttribute("lastNameClass", "has-error");
            request.setAttribute("lastNameMessage", "Last name cannot be empty");
        } else if (!RegexChecker.checkNameWithRegExp(lastName)) {
            error = true;
            request.setAttribute("lastNameClass", "has-error");
            request.setAttribute("lastNameMessage", "Last name must contain letters only");
            request.setAttribute("lastNameValue", lastName);
        } else {
            request.setAttribute("lastNameClass", "has-success");
            request.setAttribute("lastNameValue", lastName);
        }
        if (error) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            try {
                Role role = RoleDAO.getInstance().getById(5);
                int id = UserDAO.getInstance().getMaxId() + 1;
                User user = new User(id, email, password, nickName, firstName, lastName, role);
                UserDAO.getInstance().create(user);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                page = "index.jsp";
            } catch (DAOException e) {
                if (e.getCause().toString().contains("email")) {
                    request.setAttribute("emailClass", "has-error");
                    request.setAttribute("emailMessage", "Email already registered");
                    request.setAttribute("emailValue", email);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                if (e.getCause().toString().contains("nick")) {
                    request.setAttribute("nickNameClass", "has-error");
                    request.setAttribute("nickNameMessage", "Nickname already taken");
                    request.setAttribute("nickNameValue", nickName);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
        }
        return page;
    }

}
