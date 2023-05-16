/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natemoe
 */
public class GraphException extends RuntimeException{
     
    public GraphException(){
        super("Exception was found");
    }
    public GraphException(String s){
        super(s);
    }
}
