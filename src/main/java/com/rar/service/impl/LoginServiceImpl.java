package com.rar.service.impl;
import com.rar.DTO.LoginUserDetails;
import com.rar.config.GenerateJWT;
import com.rar.enums.RoleEnum;
import com.rar.exception.InvalidTokenException;
import com.rar.exception.InvalidUserException;
import com.rar.model.Designation;
import com.rar.model.Roles;
import com.rar.model.UserInfo;
import com.rar.repository.UserRepository;
import com.rar.service.LoginService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenerateJWT generateJWT;

    @Value("${jwt.secret}")
    private String secret;
    public LoginUserDetails login(String token) throws Exception {

        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + token);
            CloseableHttpResponse response = null;
            response = client.execute(request);
            int status = response.getStatusLine().getStatusCode();
            if (!(status >= 200 && status < 300)) {

                throw new InvalidTokenException("The token is invalid");

            }
            HttpEntity httpEntity = response.getEntity();
            String responseString = EntityUtils.toString(httpEntity, "UTF-8");

            JSONObject json = (JSONObject) new JSONParser().parse(responseString);
            String email = (String) json.get("email");
            String imageUrl = (String) json.get("picture");
            try {
                Optional<UserInfo> repoEmail = userRepository.findByEmail(email);
                UserInfo userInfo = userRepository.findByEmail(email).get();
                Iterator<Roles> it = userInfo.getRoles().iterator();
                Roles r = it.next();
                RoleEnum roleEnum = r.getRole();
                Iterator<Designation> itt = userInfo.getDesignation().iterator();
                Designation d = itt.next();
                String designation = d.getDesignation();
                boolean isManager = true;
                if (userRepository.managerOrEmployee(email) == 0)
                    isManager = false;
                if (repoEmail.isPresent()) {

                    if (!userInfo.getFirstSign()) {
                        userInfo.setFirstSign(true);
                        userInfo.setImageUrl(imageUrl);
                        userInfo.setEmail(userInfo.getEmail());
                        userInfo.setName(userInfo.getName());
                        userInfo.setDesignation(userInfo.getDesignation());
                        userInfo.setRoles(userInfo.getRoles());
                        userInfo.setUserId(userInfo.getUserId());
                        userInfo.setWallet(userInfo.getWallet());
                        userRepository.save(userInfo);
                        String generatedToken = generateJWT.generateToken(email);
                        return new LoginUserDetails(userInfo.getEmail() + "", userInfo.getName() + "", userInfo.getImageUrl() + "", "" + generatedToken, roleEnum, designation, userInfo.getUserId(), isManager, userInfo.getWallet());
                    } else {
                        String generatedToken = generateJWT.generateToken(email);
                        return new LoginUserDetails(userInfo.getEmail() + "", userInfo.getName() + "", userInfo.getImageUrl() + "", "" + generatedToken, roleEnum, designation, userInfo.getUserId(), isManager, userInfo.getWallet());
                    }

                }
            } catch (Exception e) {
                throw new InvalidUserException("you are not a user till now");
            }
            LoginUserDetails details1 = new LoginUserDetails();
            return details1;
        }

    }
    @Override
    public ResponseEntity<UserInfo> saveLogin(UserInfo userInfo) {
        return new ResponseEntity<>(userRepository.save(userInfo),HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<LoginUserDetails>> findAll() {
        List<UserInfo> userInfos = userRepository.getAll();
        List<LoginUserDetails> userInfoList=new ArrayList<>();
        for(int i =0;i<userInfos.size();i++){
            userInfoList.add(i, new LoginUserDetails(userInfos.get(i).getEmail(), userInfos.get(i).getName(), userInfos.get(i).getImageUrl(), userInfos.get(i).getUserId()));
        }
        if (userInfos.isEmpty())
            userInfoList=new ArrayList<>();
        return new ResponseEntity<>(userInfoList,HttpStatus.OK);
    }
    @Override
    public void deleteById(Long uid) {
        userRepository.deleteById(uid);
    }
    @Override
    public ResponseEntity<UserInfo> findById(Long uid) {
        return new ResponseEntity( userRepository.findById(uid), HttpStatus.OK);
    }
    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
    @Override
    public Long getIdByName(String userEmail) {
        return userRepository.getIdByEmail(userEmail);
    }
}