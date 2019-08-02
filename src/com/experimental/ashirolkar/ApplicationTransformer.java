package com.experimental.ashirolkar;

import java.util.function.Function;

class ApplicationTransformer {

  static Function<LambdaFunction, LambdaFunction> transform2(
      Function<LambdaFunction, Function<LambdaFunction, LambdaFunction>> application) {
    return a -> new LambdaFunction(application.apply(a));
  }

  static Function<LambdaFunction, LambdaFunction> transform3(
      Function<LambdaFunction, Function<LambdaFunction, Function<LambdaFunction, LambdaFunction>>>
          application) {
    return a -> new LambdaFunction(transform2(application.apply(a)));
  }
}
