package tr.com.argela.whatsapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.argela.whatsapp.entity.User;
import tr.com.argela.whatsapp.entity.UserSession;
import tr.com.argela.whatsapp.repository.UserRepository;
import tr.com.argela.whatsapp.repository.UserSessionRepository;
import tr.com.argela.whatsapp.util.GeneralUtil;

@Service
public class UserService {

    private static final long timeDuration = 1000 * 60 * 60;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    public String login(String phone, String password) throws Exception {

        String md5 = GeneralUtil.getMD5Hash(password);
        List<User> users = userRepository.login(phone, md5);

        if (users == null || users.isEmpty()) {
            throw new Exception("You are not authorized");
        } else {
            User user = users.get(0);

            String GUID = GeneralUtil.getGUID();

            UserSession userSession = new UserSession();
            userSession.setUser(new User(user.getId()));
            userSession.setSessionId(GUID);
            userSession.setLoginDate(new Date());

            userSessionRepository.save(userSession);

            return GUID;
        }

    }

    public void validateSession(String sessionId) throws Exception {

        List<UserSession> userSessions = userSessionRepository.validateSession(sessionId);

        if (userSessions == null || userSessions.isEmpty()) {
            throw new Exception("you are not authorized");
        }
        UserSession userSession = userSessions.get(0);
        long loginDate = userSession.getLoginDate().getTime();
        long currentTime = new java.util.Date().getTime();

        if (currentTime - loginDate > timeDuration) {
            throw new Exception("timeout");
        }

    }

    public void logout(String sessionId) {
        userSessionRepository.logoutSession(sessionId);
    }

}
