package ru.itis.security;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.InsightsResponse;
import com.maxmind.geoip2.record.*;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserRepository userRepository;
    private HttpServletRequest request;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthProviderImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String ip = request.getRemoteAddr();
        String email = authentication.getName();
        try (WebServiceClient client = new WebServiceClient.Builder(266541, "91s00LjlluDGnD8i")
                .build()) {

            InetAddress ipAddress = InetAddress.getByName(ip);

            InsightsResponse response = client.insights(ipAddress);

            Country country = response.getCountry();
            System.out.println(country.getIsoCode());            // 'US'
            System.out.println(country.getName());               // 'United States'
            System.out.println(country.getNames().get("zh-CN")); // '美国'
            System.out.println(country.getConfidence());         // 99

            Subdivision subdivision = response.getMostSpecificSubdivision();
            System.out.println(subdivision.getName());       // 'Minnesota'
            System.out.println(subdivision.getIsoCode());    // 'MN'
            System.out.println(subdivision.getConfidence()); // 90

            City city = response.getCity();
            System.out.println(city.getName());       // 'Minneapolis'
            System.out.println(city.getConfidence()); // 50

            Postal postal = response.getPostal();
            System.out.println(postal.getCode());       // '55455'
            System.out.println(postal.getConfidence()); // 40

            Location location = response.getLocation();
            System.out.println(location.getLatitude());        // 44.9733
            System.out.println(location.getLongitude());       // -93.2323
            System.out.println(location.getAccuracyRadius());  // 3
            System.out.println(location.getTimeZone());        // 'America/Chicago'

            System.out.println(response.getTraits().getUserType()); // 'college'

            if(country.getIsoCode().equals("RU")){
                throw new BadCredentialsException("Bad credentials");
            }
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
