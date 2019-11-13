package application.controller;

import application.dao.SaltResponse;
import application.entity.People;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/")
public class UserRestController {

    @Autowired
    public UserService userService;
    public Map<String, String> saltMap = new HashMap<String, String>();

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
    public SaltResponse getSalt(HttpSession session, @RequestParam("login") String login) {
        SaltResponse response = new SaltResponse();
        response.setSalt(Long.toHexString((long) ((Math.random() * 900000000000000000L) + 100000000000000000L)));
        saltMap.put(login,response.getSalt());
        session.setAttribute("salt", response.getSalt());
        return response;
    }

//    @PostMapping("/signUp")
//    public Boolean signUp(HttpSession session, @RequestBody SignUpRequestBody body){
//        BigInteger privateKey = BigInteger.valueOf((long) (Math.random() * 1000));
//        session.setAttribute("privateKey", privateKey);
//        BigInteger publicKey = diffieHellman(BigInteger.valueOf(1000), privateKey);
//        session.setAttribute("publicValue", publicKey);
//        session.setAttribute("resultKey", 0);
//        String password = request.getParameter("PASSWORD");
//        setEmailPassword(session, request.getParameter("EMAIL"), password);
//        String email = session.getAttribute("email").toString();
//        String password = session.getAttribute("password").toString();
//        People user;
//        try {
//            user = userService.logIn(email, password, session);
//        } catch (EmptyPasswordException e) {
//            session.setAttribute("email", request.getParameter("EMAIL"));
//            session.setAttribute("loginError", "Введите, пожалуйста, пароль.");
//            model.addAttribute("user", null);
//            return new ModelAndView("redirect:/");
//        } catch (WrongPasswordException e) {
//            setEmailPassword(session, request.getParameter("EMAIL"), "");
//            session.setAttribute("loginError", "Пароль введён неверно! Попробуйте ещё раз.");
//            model.addAttribute("user", null);
//            return new ModelAndView("redirect:/");
//        }
//        if (user == null) {
//            session.setAttribute("loginError", "Введите email и пароль!");
//            model.addAttribute("user", null);
//            return new ModelAndView("redirect:/");
//        }
//        model.addAttribute("user", user);
//        session.setAttribute("user", user);
//        session.setAttribute("registration", "");
//        session.setAttribute("loginError", "");
//        return new ModelAndView("View");
//    }

    /**
     * Метод проверяет, присутствует ли в БД зпапись с переданным сочетанием логин - пароль.
     *
     * @param login    логин
     * @param password зашифрованный пароль
     * @param session
     * @return если залогинились, то возвращаем true, в противном случае - false
     */
    @PostMapping(value = "/login", params = {"login", "password", "salt"})
    public Boolean login(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("salt") String salt, HttpSession session, HttpServletRequest request){
        BigInteger privateKey = BigInteger.valueOf((long) (Math.random() * 1000));
//        session.setAttribute("privateKey", privateKey);
//        BigInteger publicKey = diffieHellman(BigInteger.valueOf(1000), privateKey);
        session.setAttribute("salt", saltMap.get(login));
//        session.setAttribute("publicValue", publicKey);
//        session.setAttribute("resultKey", 0);
//        session = httpSession;
        setEmailPassword(session, login, password);
        String email = login;
        String password2 = password;
        People user;
        try {
            user = userService.logIn(email, password2, session);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
