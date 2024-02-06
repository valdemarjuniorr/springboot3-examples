package br.com.valdemarjr.mongodbcrudexample.controllers;

import br.com.valdemarjr.mongodbcrudexample.domain.Hobby;
import br.com.valdemarjr.mongodbcrudexample.domain.User;
import br.com.valdemarjr.mongodbcrudexample.exceptions.UserNotFoundException;
import br.com.valdemarjr.mongodbcrudexample.service.UserService;
import org.assertj.core.api.Assertions;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  private static final String USER_ID = "123";
  private static final String HOBBY_NAME = "Dancing";
  private static final String USER_NAME = "Valdemar Jr";
  private static final int USER_AGE = 41;
  private static final int FREQUENCY = 2;

  @Mock private UserService service;

  @InjectMocks private UserController controller;

  private static User getUser() {
    return User.builder()
        .id(USER_ID)
        .name(USER_NAME)
        .age(USER_AGE)
        .hobbies(List.of(Hobby.builder().name(HOBBY_NAME).frequency(FREQUENCY).build()))
        .build();
  }

  private static UserRequest getUserRequest() {
    return UserRequest.builder()
        .id(USER_ID)
        .name(USER_NAME)
        .age(USER_AGE)
        .hobbies(List.of(HobbyRequest.builder().name(HOBBY_NAME).frequency(FREQUENCY).build()))
        .build();
  }

  @Test
  void findAllEmpty() {
    when(service.findAll()).thenReturn(Collections.emptyList());
    var response = controller.findAll();
    assertThat(response).isEmpty();
    verify(service).findAll();
  }

  @Test
  void findAllSizeOne() {
    var responseMock = getUser();
    when(service.findAll()).thenReturn(List.of(responseMock));
    var response = controller.findAll();
    assertThat(response.size()).isEqualTo(1);
    verify(service).findAll();
  }

  @Test
  void findByIdFound() {
    when(service.findBy(USER_ID)).thenReturn(Optional.of(getUser()));
    var response = controller.findBy(USER_ID);
    assertThat(response).isNotNull();
    verify(service).findBy(USER_ID);
  }

  @Test
  void findByIdException() {
    when(service.findBy(USER_ID)).thenReturn(Optional.empty());
    Assertions.assertThatThrownBy(() -> controller.findBy(USER_ID))
        .isInstanceOf(UserNotFoundException.class)
        .hasMessage("user not found");
    verify(service).findBy(USER_ID);
  }

  @Test
  void save() {
    var argCapture = ArgumentCaptor.forClass(User.class);
    var request = getUserRequest();
    when(service.save(argCapture.capture())).thenReturn(getUser());

    var newUserId = controller.save(request);

    assertThat(newUserId).isNotNull();
    verify(service).save(argCapture.getValue());
  }

  @Test
  void update() {
    var argsCaptor = ArgumentCaptor.forClass(User.class);
    var request = getUserRequest();
    doNothing().when(service).update(argsCaptor.capture());

    controller.update(USER_ID, request);

    verify(service).update(argsCaptor.getValue());
  }

  @Test
  void delete() {
    var user = getUser();
    when(service.delete(USER_ID)).thenReturn(user);

    var response = controller.delete(USER_ID);

    assertThat(response.getId()).isEqualTo(USER_ID);
    assertThat(response.getName()).isEqualTo(USER_NAME);
    assertThat(response.getAge()).isEqualTo(USER_AGE);
    verify(service).delete(USER_ID);
  }
}
