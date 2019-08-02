package com.experimental.ashirolkar;


import static com.experimental.ashirolkar.ApplicationTransformer.transform2;
import static com.experimental.ashirolkar.ApplicationTransformer.transform3;

public class Main {

  public static void main(String[] args) {

    LambdaFunction _true = new LambdaFunction("true", transform2(a -> b -> a));
    LambdaFunction _false = new LambdaFunction("false", transform2(a -> b -> b));

    LambdaFunction _not = new LambdaFunction("not", f -> f.of(_false, _true));
    LambdaFunction and = new LambdaFunction("and", transform2(a -> b -> a.of(b, a)));

    LambdaFunction id = new LambdaFunction("id", a -> a);

    // System.out.println(_not.of(_true).print()); // prints "false"
    // System.out.println(_not.of(_false).print()); // prints "true"

    LambdaFunction _0 = _false.rename("0");
    LambdaFunction _1 = new LambdaFunction("1", transform2(f -> x -> f.of(x)));
    LambdaFunction _2 = new LambdaFunction("2", transform2(f -> x -> f.of(f.of(x))));

    // System.out.println(_true.of(_0, _1).print());

    LambdaFunction successor =
        new LambdaFunction(transform3(n -> f -> x -> f.of(n.of(f, x))));

    LambdaFunction predecessor =
        new LambdaFunction(
            transform3(
                n ->
                    f ->
                        x ->
                            n.of(
                                new LambdaFunction(transform2(g -> h -> h.of(g.of(f)))),
                                new LambdaFunction(u -> x),
                                id)));

    LambdaFunction isZero =
        new LambdaFunction(n -> n.of(new LambdaFunction(x -> _false), _true));

    // System.out.println(isZero.of(predecessor.of(_1)).print());

    LambdaFunction minus = new LambdaFunction(transform2(m -> n -> n.of(predecessor).of(m)));

    // System.out.println(isZero.of(minus.of(_2, _2)).print());

    LambdaFunction leq = new LambdaFunction(transform2(m -> n -> isZero.of(minus.of(m, n))));

    // System.out.println(leq.of(_2, _1).print());

    LambdaFunction eq =
        new LambdaFunction(transform2(a -> b -> and.of(leq.of(a, b), leq.of(b, a))));

    // System.out.println(eq.of(_0, _0).print());
    // System.out.println(eq.of(_1, minus.of(_2, _1)).print());

    LambdaFunction pair = new LambdaFunction(transform3(a -> b -> f -> f.of(a, b)));

    LambdaFunction first = new LambdaFunction(p -> p.of(_true));
    LambdaFunction second = new LambdaFunction(p -> p.of(_false));

    // System.out.println(first.of(pair.of(_0, _1)).print());
    // System.out.println(second.of(pair.of(_0, _1)).print());

    LambdaFunction cons1 = new LambdaFunction(transform2(h -> t -> pair.of(_false, pair.of(h, t))));
    LambdaFunction emptyList = pair.of(_true, _true);
    LambdaFunction isEmptyList = first.rename("isEmptyList");
    LambdaFunction head = new LambdaFunction(list -> first.of(second.of(list)));
    LambdaFunction tail = new LambdaFunction(list -> second.of(second.of(list)));


  }
}
