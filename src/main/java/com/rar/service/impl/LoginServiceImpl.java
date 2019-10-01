package com.rar.service.impl;

import com.rar.exception.InvalidTokenException;
import com.rar.exception.InvalidUserException;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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


    public String login(String token) throws Exception {


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

        Optional<UserInfo> repoEmail = userRepository.findByemail(email);


        UserInfo userInfo1 = userRepository.findByemail(email).get();



            if (repoEmail.isPresent()) {
                if (!userInfo1.getFirstSign()) {
                    userInfo1.setFirstSign(true);
                    userInfo1.setImageUrl(imageUrl);
                    userInfo1.setEmail(userInfo1.getEmail());
                    userInfo1.setName(userInfo1.getName());

                    userRepository.save(userInfo1);


                    String generatedToken = Jwts.builder()
                            .setSubject(String.valueOf(email))
                            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();

                    return generatedToken;

                } else {

                    String generatedToken = Jwts.builder()
                            .setSubject(String.valueOf(email))
                            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();

                    return generatedToken;
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
       /*JSONObject reply = new JSONObject();
        //reply.put("googleId",googleId);
        reply.put("email",email);
        reply.put("name",name);


        //System.out.println(email +" is the email and Google Id is "+ googleId);
        return reply.toJSONString();*/
        return "sdsad";
    }


    @Override
    public UserInfo saveLogin(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> findAll() {
        return (List<UserInfo>) userRepository.findAll();
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
    public void deleteByemail(String email) {

        userRepository.deleteByemail(email);
    }

    @Override
    public Long getIdByName(String user_email) throws Exception {
        return userRepository.getIdByEmail(user_email);
    }

    @Override
    public Optional<UserInfo> findByemail(String email) {
        return userRepository.findByemail(email);
    }




}