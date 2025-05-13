package main.java.com.appGerenciadorDeProcessos.Model;

public class Cpu {

    //available_amount é um valor em porcentagem p/ representar a % disponível da CPU
    private static double available_amount = 0;
    private static Cpu instance = null;


    private Cpu(){
        available_amount = 1;
    }

    public static Cpu getInstance(){
        if(instance == null){
            instance = new Cpu();
        }
        return instance;
    }

    public double getAvailableAmount(){ return available_amount; }

    public void setAvailableAmount(double use_percentage) throws Exception{
        if(use_percentage > available_amount){
            throw new Exception("Limite da capacidade da CPU atingida.");
        }else{
            available_amount -= use_percentage;
        }
    }

}
