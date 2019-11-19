package application.controller;

import application.dao.AutorizationData;
import application.dao.PublicValueResponse;
import application.dao.SaltResponse;
import application.entity.People;
import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordException;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static application.controller.UserController.decodePassword;
import static application.dao.UserDaoImpl.hashPassword;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/")
public class UserRestController {

    @Autowired
    public UserService userService;
    public Date date;

    public Map<String, AutorizationData> saltMap = new HashMap<String, AutorizationData>();
    public Map<String, BigInteger> keyMap = new HashMap<String, BigInteger>();

    public void setEmailPassword(HttpSession session, String email, String password) {
        session.setAttribute("email", email);
        session.setAttribute("password", password);
    }

    public BigInteger diffieHellman(BigInteger num, BigInteger exp) {
        BigInteger key = num.pow(exp.intValue()).mod(BigInteger.valueOf(983));

        return key;
    }

    /**
     * @param session
     * @return
     */
    @PostMapping(value = "/Salt", params = {"login"})
    public SaltResponse getSalt(HttpSession session, @RequestParam("login") String login) {
        SaltResponse response = new SaltResponse();
        AutorizationData autorizationData = new AutorizationData();
        date = new Date();
        response.setSalt(Long.toHexString((long) ((Math.random() * 900000000000000000L) + 100000000000000000L)));
        autorizationData.setSalt(response.getSalt());
        autorizationData.setDate(date);
        saltMap.put(login, autorizationData);
        session.setAttribute("salt", response.getSalt());
        return response;
    }

    @PostMapping(value = "/key", params = {"publicValue", "login"})
    public PublicValueResponse getPublicKey(@RequestParam("publicValue") BigInteger publicValue,
                                            @RequestParam("login") String login) {
        PublicValueResponse response = new PublicValueResponse();
        BigInteger privateKey = BigInteger.valueOf((long) (Math.random() * 1000));
        BigInteger resultKey = diffieHellman(publicValue,privateKey);
        keyMap.put(login,resultKey);
        BigInteger publicKey = diffieHellman(BigInteger.valueOf(1000), privateKey);
        response.setPublicValue(publicKey);
        return response;
    }

    @PostMapping(value = "/registrate", params = {"name", "surname", "login", "password", "copyPassword", "birthday", "gender", "bug", "comments"})
    public Boolean signUp(Model model, @ModelAttribute("user") People user, HttpSession session, HttpServletRequest request, @RequestParam("name") String name, @RequestParam("surname") String surname,
                          @RequestParam("login") String login, @RequestParam("password") String password,
                          @RequestParam("copyPassword") String copyPassword, @RequestParam("birthday") String birthday,
                          @RequestParam("gender") String gender, @RequestParam("bug") String bug,
                          @RequestParam("comments") String comments) throws ParseException, EmptyPasswordException, WrongPasswordException {
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(login);
        user.setPassword(decodePassword(password, keyMap.get(login)));
        user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setGender(gender);
        user.setBug(bug);
        user.setComments(comments.equals("undefined") ? null:comments);
        try {
            userService.save(user, request, session);
        } catch (EntityExistsException e) {
            String newPassword = hashPassword(user.getEmail() +
                    user.getPassword() + session.getAttribute("salt"));
            setEmailPassword(session, user.getEmail(), newPassword);
            user = userService.logIn(user.getEmail(), newPassword, session);
            model.addAttribute("user", user);
            return false;
        } catch (Exception e) {
            return false;
        }
        model.addAttribute("user", user);
        return true;
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
    public Boolean login(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session) {
        session.setAttribute("salt", saltMap.get(login).getSalt());
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
