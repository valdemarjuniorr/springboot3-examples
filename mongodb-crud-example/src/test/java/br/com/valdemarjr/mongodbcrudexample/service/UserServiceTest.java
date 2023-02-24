package br.com.valdemarjr.mongodbcrudexample.service;

import br.com.valdemarjr.mongodbcrudexample.domain.Hobby;
import br.com.valdemarjr.mongodbcrudexample.domain.User;
import br.com.valdemarjr.mongodbcrudexample.exceptions.UserNotFoundException;
import br.com.valdemarjr.mongodbcrudexample.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private static final String USER_ID = "123";
  @Mock private UserRepository repository;

  @InjectMocks private UserService service;

  private static User getUser() {
    return User.builder()
        .id(USER_ID)
        .name("Valdemar Jr")
        .age(41)
        .hobbies(List.of(Hobby.builder().name("Dancing").frequency(2).build()))
        .build();
  }

  @Test
  void findByIdEmpty() {
    when(repository.findById(USER_ID)).thenReturn(Optional.empty());

    var user = service.findBy(USER_ID);

    assertThat(user).isEmpty();
    verify(repository).findById(USER_ID);
  }

  @Test
  void findByIdFound() {
    var userMock = mock(User.class);
    when(repository.findById(USER_ID)).thenReturn(Optional.of(userMock));

    var user = service.findBy(USER_ID);

    assertThat(user.get()).isEqualTo(userMock);
    verify(repository).findById(USER_ID);
  }

  @Test
  void findAllEmpty() {
    when(repository.findAll()).thenReturn(Collections.emptyList());

    var users = service.findAll();

    assertThat(users).isEmpty();
    verify(repository).findAll();
  }

  @Test
  void findAllOk() {
    var userMock = mock(User.class);
    when(repository.findAll()).thenReturn(List.of(userMock));

    var users = service.findAll();

    assertThat(users.size()).isEqualTo(1);
    verify(repository).findAll();
  }

  @Test
  void save() {
    var userMock = mock(User.class);
    when(repository.save(userMock)).thenReturn(userMock);

    var savedUser = service.save(userMock);

    assertThat(savedUser).isEqualTo(userMock);
    verify(repository).save(savedUser);
  }

  @Test
  void updateNotFound() {
    when(repository.findById(USER_ID)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> service.delete(USER_ID))
        .isInstanceOf(UserNotFoundException.class)
        .hasMessage("user not found");

    verify(repository).findById(USER_ID);
    verifyNoMoreInteractions(repository);
  }

  @Test
  void updateOk() {
    var user = getUser();
    var argsCapture = ArgumentCaptor.forClass(User.class);
    when(repository.findById(USER_ID)).thenReturn(Optional.of(user));
    when(repository.save(argsCapture.capture())).thenReturn(user);

    service.update(user);

    verify(repository).findById(USER_ID);
    verify(repository).save(argsCapture.capture());
  }

  @Test
  void delete() {
    var userMock = mock(User.class);
    when(repository.findById(USER_ID)).thenReturn(Optional.of(userMock));
    doNothing().when(repository).delete(userMock);

    var deleted = service.delete(USER_ID);

    assertThat(deleted).isEqualTo(deleted);
    verify(repository).delete(userMock);
  }

  @Test
  void deleteUserNotFound() {
    when(repository.findById(USER_ID)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> service.delete(USER_ID))
        .isInstanceOf(UserNotFoundException.class)
        .hasMessage("user not found");

    verify(repository).findById(USER_ID);
    verifyNoMoreInteractions(repository);
  }
}
