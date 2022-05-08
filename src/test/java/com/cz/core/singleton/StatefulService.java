package com.cz.core.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order (String name, int price) {
        System.out.println(name + " ordered " + price + " dollars.");
        this.price = price; // 여기가 문제 this.price 에 값을 대입하는게 아니라 그냥 반환해주고 끝내야 한다.
    }

    public int getPrice() { // 함수에서 반환하고 끝내면 이건 필요없어진다.
        return price;
    }

}
