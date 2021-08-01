package utils;

/** 
* La unica funcion de esta clase es permitir que un metodo pueda recibir
* una funcion con un solo argumento
 */
public interface ActionCallbackOneArg<T> {
    public void execute(T arg);
}
