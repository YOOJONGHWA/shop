package com.apple.shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {
    private Integer age;
    private String name;
    public void 한살더하기() {
        this.age = this.age + 1;
    }
    public void 나이설정(Integer a) {
        if (a < 1 || a > 99) {
            System.out.println("음수나 100살은 안되용");
        }
        else {
            this.age = a;
        }
    }
    public static void main(String[] args) {
        var a = new Test();
        a.setName("홍길동");
        a.setAge(20);
        a.나이설정(-10);
        a.한살더하기();
//        System.out.println(a.getAge());
    }
}
