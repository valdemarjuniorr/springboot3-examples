package br.com.valdemarjr.redisexample.controllers;

import java.io.Serializable;

public record Product(Long id, String description) implements Serializable {}
