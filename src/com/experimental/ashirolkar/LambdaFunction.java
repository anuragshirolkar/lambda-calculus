package com.experimental.ashirolkar;

import java.util.function.Function;

final class LambdaFunction {

  private final String name;
  private final Function<LambdaFunction, LambdaFunction> application;

  LambdaFunction(String name, Function<LambdaFunction, LambdaFunction> application) {
    this.name = name;
    this.application = application;
  }

  LambdaFunction(Function<LambdaFunction, LambdaFunction> application) {
    this.name = "unnamed";
    this.application = application;
  }

  public LambdaFunction of(LambdaFunction input) {
    return application.apply(input);
  }

  public LambdaFunction of(LambdaFunction... inputs) {
    LambdaFunction cur = this;
    for (LambdaFunction input : inputs) {
      cur = cur.of(input);
    }
    return cur;
  }

  public LambdaFunction rename(String newName) {
    return new LambdaFunction(newName, application);
  }

  public String print() {
    return name;
  }
}