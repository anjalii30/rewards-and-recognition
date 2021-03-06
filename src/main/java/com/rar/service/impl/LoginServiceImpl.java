package com.rar.service.impl;

import com.rar.enums.DesignationEnum;
import com.rar.enums.RoleEnum;
import com.rar.exception.InvalidTokenException;
import com.rar.exception.InvalidUserException;
import com.rar.model.*;
import com.rar.repository.UserRepository;
import com.rar.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    private static final long JWT_TOKEN_VALIDITY = (long )7* 24 * 60 * 60;


    public LoginUserDetails login(String token) throws Exception {

        //google token decryption
        UserInfo userInfo = new UserInfo();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + token);
        CloseableHttpResponse response = null;
        response = client.execute(request);
        int status = response.getStatusLine().getStatusCode();

        if (!(status >= 200 && status < 300)) {

            //illegal token exception
            System.out.println("Unexpected response status: " + status);

            throw new InvalidTokenException("The token is invalid");
            //throws an exception in case of invalid token;
        }

        HttpEntity httpEntity = response.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");

        //data extracting
        JSONObject json = (JSONObject) new JSONParser().parse(responseString);

        Set keys = json.<String>keySet();

        System.out.println(keys); // use logger

        String email = (String) json.get("email");
        System.out.println("" + email);
        String name = (String) json.get("name");
        System.out.println("" + name);
        String imageUrl = (String) json.get("picture");
        System.out.println(imageUrl);
        try {

            Optional<UserInfo> repoEmail = userRepository.findByEmail(email);

            UserInfo userInfo1 = userRepository.findByEmail(email).get();

            Iterator<Roles> it= userInfo1.getRoles().iterator();

            Roles r=it.next();
            RoleEnum roleEnum=r.getRole();

            Iterator<Designation> itt= userInfo1.getDesignation().iterator();
            Designation d=itt.next();

            DesignationEnum designationEnum= d.getDesignation();

            boolean isManager=true;
            if(userRepository.managerOrEmployee(email) == 0)
                isManager= false;

            if (repoEmail.isPresent()) {
                if (!userInfo1.getFirstSign()) {
                    userInfo1.setFirstSign(true);
                    userInfo1.setImageUrl(imageUrl);
                    userInfo1.setEmail(userInfo1.getEmail());
                    userInfo1.setName(userInfo1.getName());
                    userInfo1.setDesignation(userInfo1.getDesignation());
                    userInfo1.setRoles(userInfo1.getRoles());
                    userInfo1.setId(userInfo1.getId());

                    userRepository.save(userInfo1);

                    String generatedToken = Jwts.builder()
                            .setSubject(String.valueOf(email))
                            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();

                    return new LoginUserDetails(userInfo1.getEmail()+"",userInfo1.getName()+"",userInfo1.getImageUrl()+"",""+generatedToken,roleEnum,designationEnum,userInfo1.getId(),isManager);

                } else {

                    String generatedToken = Jwts.builder()
                            .setSubject(String.valueOf(email))
                            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();
                    return new LoginUserDetails(userInfo1.getEmail()+"",userInfo1.getName()+"",userInfo1.getImageUrl()+"",""+generatedToken,roleEnum,designationEnum,userInfo1.getId(),isManager);



                }
                //user already exists
            }

        } catch (Exception e) {

            throw new InvalidUserException("you are not a user till now");

        }
        LoginUserDetails details1=new LoginUserDetails();
        return details1;
    }

    @Override
    public UserInfo saveLogin(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }


    @Override
    public List<LoginUserDetails> findAll() {

        List<UserInfo> userInfos = userRepository.getAll();
        List<LoginUserDetails> userInfoList=new ArrayList<>();

        for(int i =0;i<userInfos.size();i++){
            userInfoList.add(i, new LoginUserDetails(userInfos.get(i).getEmail(), userInfos.get(i).getName(), userInfos.get(i).getImageUrl(), userInfos.get(i).getId()));
        }
        return userInfoList;

    }

    @Override
    public void deleteById(Long uid) {

        userRepository.deleteById(uid);
    }

    @Override
    public Optional<UserInfo> findById(Long uid) {
        return userRepository.findById(uid);
    }

    @Override
    public void deleteByEmail(String email) {

        userRepository.deleteByEmail(email);
    }

    @Override
    public Long getIdByName(String user_email) {
        return userRepository.getIdByEmail(user_email);
    }

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}