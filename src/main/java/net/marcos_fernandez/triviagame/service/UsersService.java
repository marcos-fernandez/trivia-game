package net.marcos_fernandez.triviagame.service;

import net.marcos_fernandez.triviagame.model.User;
import net.marcos_fernandez.triviagame.model.UserBasicInformation;
import net.marcos_fernandez.triviagame.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;

    public UsersService(final UsersRepository usersRepository, @Qualifier("mvcConversionService") final ConversionService conversionService, final PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.conversionService = conversionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return this.usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public Optional<User> findByUserName(final String userName) {
        return this.usersRepository.findByUsername(userName);
    }

    public List<UserBasicInformation> list() {
        return this.usersRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(user -> this.conversionService.convert(user, UserBasicInformation.class))
                .collect(Collectors.toList());
    }

    public UserBasicInformation save(final UserBasicInformation userBasicInformation) {
        final var user = this.getUserWithChangesApplied(userBasicInformation);
        final var updatedUser = this.usersRepository.save(user);
        return this.conversionService.convert(updatedUser, UserBasicInformation.class);
    }

    private User getUserWithChangesApplied(final UserBasicInformation userBasicInformation) {
        final var existingUser = this.getExistingUserOrEmptyOne(userBasicInformation);
        return userBasicInformation.applyChanges(existingUser);
    }

    private User getExistingUserOrEmptyOne(final UserBasicInformation userBasicInformation) {
        return Optional.ofNullable(userBasicInformation.getId())
                .map(this.usersRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElse(User.empty());
    }

    public void delete(final UserBasicInformation userBasicInformation) {
        this.usersRepository.findById(userBasicInformation.getId())
                .ifPresent(this.usersRepository::delete);
    }

    public Optional<UserBasicInformation> getUserBasicInformation(final String username) {
        return this.usersRepository.findByUsername(username)
                .map(user -> this.conversionService.convert(user, UserBasicInformation.class));

    }

    public void updatePassword(final String username, final String password) {
        this.usersRepository.findByUsername(username)
                .map(user -> this.getUserWithUpdatedPassword(user, password))
                .map(this.usersRepository::save);

    }

    private User getUserWithUpdatedPassword(final User user, final String password) {
        return user.toBuilder()
                .password(this.passwordEncoder.encode(password))
                .build();
    }
}
