package application.controller;

import application.dao.AutorizationData;
import application.dao.SaltResponse;
import application.entity.People;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/")
public class UserRestController {

    @Autowired
    public UserService userService;
    public Date date;

    public Map<String, AutorizationData> saltMap = new HashMap<String, AutorizationData>();

    public void setEmailPassword(HttpSession session, String email, String password) {
        session.setAttribute("email", email);
        session.setAttribute("password", password);
    }

    public BigInteger diffieHellman(BigInteger num, BigInteger exp) {
        BigInteger key = num.pow(exp.intValue()).mod(BigInteger.valueOf(983));

        return key;
    }

    /**
     *
     * @param session
     * @return
     */
    @PostMapping(value = "/Salt", params = {"login"})
    public SaltResponse getSalt(HttpSession session, @RequestParam("login") String login, HttpServletResponse httpResponse) {
        SaltResponse response = new SaltResponse();
        AutorizationData autorizationData = new AutorizationData();
        date = new Date();
        response.setSalt(Long.toHexString((long) ((Math.random() * 900000000000000000L) + 100000000000000000L)));
        autorizationData.setSalt(response.getSalt());
        autorizationData.setDate(date);
        saltMap.put(login, autorizationData);
        Cookie cookie = new Cookie("cookieName", response.getSalt());
        cookie.setMaxAge(1800);
        httpResponse.addCookie(cookie);
        session.setAttribute("salt", response.getSalt());
        return response;
    }

    @PostMapping(value = "/login", params = {"name", "surname", "login", "password", "copyPassword", "birthday", "gender", "bug", "comments"})
    public Boolean signUp(HttpSession session, @RequestParam("login") String login, @RequestParam("password") String password){
        BigInteger privateKey = BigInteger.valueOf((long) (Math.random() * 1000));
        session.setAttribute("privateKey", privateKey);
        BigInteger publicKey = diffieHellman(BigInteger.valueOf(1000), privateKey);
        session.setAttribute("publicValue", publicKey);
        session.setAttribute("resultKey", 0);
        String password = request.getParameter("PASSWORD");
        setEmailPassword(session, request.getParameter("EMAIL"), password);
        String email = session.getAttribute("email").toString();
        String password = session.getAttribute("password").toString();
        People user;
        try {
            user = userService.logIn(email, password, session);
        } catch (EmptyPasswordException e) {
            session.setAttribute("email", request.getParameter("EMAIL"));
            session.setAttribute("loginError", "Введите, пожалуйста, пароль.");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
        } catch (WrongPasswordException e) {
            setEmailPassword(session, request.getParameter("EMAIL"), "");
            session.setAttribute("loginError", "Пароль введён неверно! Попробуйте ещё раз.");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
        }
        if (user == null) {
            session.setAttribute("loginError", "Введите email и пароль!");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        session.setAttribute("registration", "");
        session.setAttribute("loginError", "");
        return new ModelAndView("View");
    }

    /**
     * Метод проверяет, присутствует ли в БД запись с переданным сочетанием логин - пароль.
     *
     * @param login    логин
     * @param password зашифрованный пароль
     * @param session
     * @return если залогинились, то возвращаем true, в противном случае - false
     */
    @PostMapping(value = "/login", params = {"login", "password"})
    public Boolean login(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session){
        BigInteger privateKey = BigInteger.valueOf((long) (Math.random() * 1000));
//        session.setAttribute("privateKey", privateKey);
//        BigInteger publicKey = diffieHellman(BigInteger.valueOf(1000), privateKey);
        session.setAttribute("salt", saltMap.get(login).getSalt());
//        session.setAttribute("publicValue", publicKey);
//        session.setAttribute("resultKey", 0);
//        session = httpSession;
        date = new Date();
        setEmailPassword(session, login, password);
        People user;
        try {
            user = userService.logIn(login, password, session);
        } catch (Exception e) {
            return false;
        }
        if (user == null) {
            return false;
        }
        return true;
    }


}
