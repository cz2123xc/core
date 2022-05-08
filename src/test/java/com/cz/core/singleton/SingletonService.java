package com.cz.core.singleton;

// 싱글톤 패턴 수동 구현
public class SingletonService {

    // 1. static 영역에 객체 instance 를 미리 하나 생성해서 올려둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다. 이 메서드를 호출하면 항상 위의 static 으로 만들어진 인스턴스를 반환
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 만들어서 다른곳에서 생성하지 못하도록 함 (싱글톤 이므로 딱 1개의 인스턴스만 존재해야 하기 때문에 new 로 생성하지 못하도록 제일중요)
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
