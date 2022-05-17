package com.ex.IdMaker;

/**
 * @author Exception
 * @create 2021-07-16-16:09
 * @content
 */
public class TestIdMaker {
    public static void main(String[] args) throws Exception {
        //相当于命名服务
        IdMaker idMaker = new IdMaker("/NameService/IdGen","ID");
        idMaker.start();
        try {
            for (int i = 0; i < 5; i++) {
                String id = idMaker.generateId(IdMaker.RemoveMethod.IMMEDIATELY);
                System.out.println(id);
            }
        } finally {
            idMaker.stop();
        }
    }
}
