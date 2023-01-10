package com.example.ATM_SIMULATION_SYSTEM.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.ATM_SIMULATION_SYSTEM.model.Cards;
import com.example.ATM_SIMULATION_SYSTEM.model.RegisteredUsers;
import com.example.ATM_SIMULATION_SYSTEM.repository.CardsRepositoty;
import com.example.ATM_SIMULATION_SYSTEM.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            UsersRepository repository
            , CardsRepositoty repository2
            ) {
        return args -> {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String ecryptedPass1 = passwordEncoder.encode("123456");
            String ecryptedPass2 = passwordEncoder.encode("123");
            AESConfig config = AESConfig.getInstance();
            String ecryptedNumber1 = config.encrypt("12345678998765432");
            String ecryptedNumber2 = config.encrypt("2365");
            String ecryptedNumber3 = config.encrypt("3333");

            String pin = config.encrypt("0000");


            RegisteredUsers blazhe=new RegisteredUsers("Blazhe", "Manev", LocalDate.of(2001, Month.JANUARY, 5), "blaze_manev@yahoo.com",ecryptedPass1);
            RegisteredUsers rs=new RegisteredUsers("Saso", "Manev", LocalDate.of(2001, Month.JANUARY, 5), "dfhfhdfh@yahoo.com",ecryptedPass2);
            RegisteredUsers admin = new RegisteredUsers("Admin","Admin",LocalDate.of(2000,Month.APRIL,6),"Admin@admin.com",ecryptedPass1);
            Cards cards= new Cards(1,ecryptedNumber1,0F,pin);
            Cards cards2= new Cards(1,ecryptedNumber2, 0F,pin);
            Cards cards3= new Cards(2,ecryptedNumber3,0F,pin);
            repository.saveAll(List.of(blazhe,rs,admin));
            repository2.saveAll(List.of(cards,cards2,cards3));
        };
    }
}
