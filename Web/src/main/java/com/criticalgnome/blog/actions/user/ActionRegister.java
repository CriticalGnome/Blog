package com.criticalgnome.blog.actions.user;

import com.criticalgnome.blog.dao.implement.RoleDAOold;
import com.criticalgnome.blog.dao.implement.UserDAOold;
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
import java.util.ResourceBundle;

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
        HttpSession session = request.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle((String) session.getAttribute("locale"));
        String email = request.getParameter("email");
        String password = MD5.md5Encode(request.getParameter("password"));
        String nickName = request.getParameter("nickName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (email.equals("")) {
            error = true;
            request.setAttribute("emailClass", "has-error");
            request.setAttribute("emailMessage", bundle.getString("register.hint.empty.email"));
        } else {
            request.setAttribute("emailClass", "has-success");
            request.setAttribute("emailValue", email);
        }

        if (password.equals("")) {
            error = true;
            request.setAttribute("passwordClass", "has-error");
            request.setAttribute("passwordMessage", bundle.getString("register.hint.empty.password"));
        } else {
            request.setAttribute("passwordClass", "has-success");
            request.setAttribute("passwordValue", password);
        }

        if (nickName.equals("")) {
            error = true;
            request.setAttribute("nickNameClass", "has-error");
            request.setAttribute("nickNameMessage", bundle.getString("register.hint.empty.nickname"));
        } else if (!RegexChecker.checkNickNameWithRegExp(nickName)) {
            error = true;
            request.setAttribute("nickNameClass", "has-error");
            request.setAttribute("nickNameMessage", bundle.getString("register.hint.wrong.nickname"));
            request.setAttribute("nickNameValue", nickName);
        } else {
            request.setAttribute("nickNameClass", "has-success");
            request.setAttribute("nickNameValue", nickName);
        }

        if (firstName.equals("")) {
            error = true;
            request.setAttribute("firstNameClass", "has-error");
            request.setAttribute("firstNameMessage", bundle.getString("register.hint.empty.firstname"));
        } else if (!RegexChecker.checkNameWithRegExp(firstName)) {
            error = true;
            request.setAttribute("firstNameClass", "has-error");
            request.setAttribute("firstNameMessage", bundle.getString("register.hint.wrong.firstname"));
            request.setAttribute("firstNameValue", firstName);
        } else {
            request.setAttribute("firstNameClass", "has-success");
            request.setAttribute("firstNameValue", firstName);
        }

        if (lastName.equals("")) {
            error = true;
            request.setAttribute("lastNameClass", "has-error");
            request.setAttribute("lastNameMessage", bundle.getString("register.hint.empty.lastname"));
        } else if (!RegexChecker.checkNameWithRegExp(lastName)) {
            error = true;
            request.setAttribute("lastNameClass", "has-error");
            request.setAttribute("lastNameMessage", bundle.getString("register.hint.wrong.lastname"));
            request.setAttribute("lastNameValue", lastName);
        } else {
            request.setAttribute("lastNameClass", "has-success");
            request.setAttribute("lastNameValue", lastName);
        }
        if (error) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            try {
                Role role = RoleDAOold.getInstance().getById(5);
                int id = UserDAOold.getInstance().getMaxId() + 1;
                User user = new User(id, email, password, nickName, firstName, lastName, role);
                UserDAOold.getInstance().create(user);
                session.setAttribute("user", user);
                page = "index.jsp";
            } catch (DAOException e) {
                if (e.getCause().toString().contains("email")) {
                    request.setAttribute("emailClass", "has-error");
                    request.setAttribute("emailMessage", bundle.getString("register.hint.taken.email"));
                    request.setAttribute("emailValue", email);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                if (e.getCause().toString().contains("nick")) {
                    request.setAttribute("nickNameClass", "has-error");
                    request.setAttribute("nickNameMessage", bundle.getString("register.hint.taken.nickname"));
                    request.setAttribute("nickNameValue", nickName);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
        }
        return page;
    }

}
