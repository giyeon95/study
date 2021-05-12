package com.study.spring.jdk;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

public class ReflectionTest {

    @Test
    void invokeMethod()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name = "Spring";

        assertThat(name.length()).isEqualTo(6);

        Method lengthMethod = String.class.getMethod("length");
        assertThat((Integer) lengthMethod.invoke(name)).isEqualTo(6);

        assertThat(name.charAt(0)).isEqualTo('S');

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character) charAtMethod.invoke(name, 0)).isEqualTo('S');
    }

    @Test
    void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("Toby")).isEqualTo("Hello Toby");
        assertThat(hello.sayHi("Toby")).isEqualTo("Hi Toby");
        assertThat(hello.sayThankYou("Toby")).isEqualTo("Thank You Toby");

        Hello proxyHello = new HelloUppercase(hello);
        assertThat(proxyHello.sayHello("Toby")).isEqualTo("HELLO TOBY");
        assertThat(proxyHello.sayHi("Toby")).isEqualTo("HI TOBY");
        assertThat(proxyHello.sayThankYou("Toby")).isEqualTo("THANK YOU TOBY");
    }


    Hello proxiedHello = (Hello) Proxy.newProxyInstance(
        getClass().getClassLoader(),
        new Class[]{Hello.class},
        new UppercaseHandler(new HelloTarget())
    );


    @RequiredArgsConstructor
    public class UppercaseHandler implements InvocationHandler {

        private final Object target;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object ret = method.invoke(target, args);
            if(method.getName().startsWith("say") && ret instanceof String) {
                    return ((String) ret).toUpperCase();
            }

            return ret;
        }
    }

    interface Hello {

        String sayHello(String name);

        String sayHi(String name);

        String sayThankYou(String name);
    }

    public class HelloTarget implements Hello {

        @Override
        public String sayHello(String name) {
            return "Hello " + name;
        }

        @Override
        public String sayHi(String name) {
            return "Hi " + name;
        }

        @Override
        public String sayThankYou(String name) {
            return "Thank You " + name;
        }
    }

    @RequiredArgsConstructor
    public class HelloUppercase implements Hello {

        private final Hello hello;

        @Override
        public String sayHello(String name) {
            return hello.sayHello(name).toUpperCase();
        }

        @Override
        public String sayHi(String name) {
            return hello.sayHi(name).toUpperCase();
        }

        @Override
        public String sayThankYou(String name) {
            return hello.sayThankYou(name).toUpperCase();
        }
    }

}
