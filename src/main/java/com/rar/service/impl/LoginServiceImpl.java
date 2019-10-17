package com.rar.service.impl;

import com.rar.enums.DesignationEnum;
import com.rar.enums.RoleEnum;
import com.rar.exception.InvalidTokenException;
import com.rar.exception.InvalidUserException;
import com.rar.model.Designation;
import com.rar.model.LoginUserDetails;
import com.rar.model.Roles;
import com.rar.model.UserInfo;
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

   /* final Key signingKey = EncryptionUtil.getPrivateKey(
            env.getProperty("service.jwt.secret"));*/


    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;


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
        // String googleId = (String) json.get("sub");


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


/*
                    for (Iterator<RewardsCriteria> it = criterias.iterator(); it.hasNext(); ) {
                        RewardsCriteria f = it.next();

                        RewardsCriteria rewardsCriteria = new RewardsCriteria();
                        rewardsCriteria.setRewardId(new_reward.getId());
                        rewardsCriteria.setCriteriaId(f.getCriteriaId());
                        rewardsCriteria.setCompulsory(f.getCompulsory());

                        */




                    String generatedToken = Jwts.builder()
                            .setSubject(String.valueOf(email))
                            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();

                    return new LoginUserDetails(userInfo1.getEmail()+"",userInfo1.getName()+"",userInfo1.getImageUrl()+"",""+generatedToken,roleEnum,designationEnum,userInfo1.getId());

                } else {



                    String generatedToken = Jwts.builder()
                            .setSubject(String.valueOf(email))
                            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();
                    return new LoginUserDetails(userInfo1.getEmail()+"",userInfo1.getName()+"",userInfo1.getImageUrl()+"",""+generatedToken,roleEnum,designationEnum,userInfo1.getId());



                }
                //user already exists
            }
//        else
//            {
//            throw new InvalidUserException("you are not a user till now");


            //  temp.setGoogleId(googleId);
//
        } catch (Exception e) {

            throw new InvalidUserException("you are not a user till now");

        }
        LoginUserDetails details1=new LoginUserDetails();
       /*JSONObject reply = new JSONObject();
        //reply.put("googleId",googleId);
        reply.put("email",email);
        reply.put("name",name);


        //System.out.println(email +" is the email and Google Id is "+ googleId);
        return reply.toJSONString();*/
        return details1;
    }


    @Override
    public UserInfo saveLogin(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public List findAll() {
        return  userRepository.findAllUsers();
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
    public Long getIdByName(String user_email) throws Exception {
        return userRepository.getIdByEmail(user_email);
    }

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }




}