/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listaDinamica;

/**
 *
 * @author Jairo Calderón
 */
public class Lista<T> {
    public Nodo inicio;
    public Nodo cola;
    public int size = 0;
    
    public void agregarNodo(T pObjeto) {
        if(this. inicio == null) {
            Nodo nodo = new Nodo<T>();
            nodo.objeto = pObjeto;
            inicio = nodo;
            cola = nodo;
        }
        else {
            Nodo nodo = new Nodo<T>();
            nodo.objeto = pObjeto;
            nodo.anterior = cola;
            cola.siguiente = nodo;
            cola = nodo;
        }
        size++;
    }
    
    public void intercambiarNodos(Nodo pNodo1, Nodo pNodo2) {
        
        if(this.inicio == pNodo1 && this.cola == pNodo2) {
            pNodo1.anterior = pNodo2;
            pNodo2.siguiente = pNodo1;
            this.inicio = pNodo2;
            this.cola = pNodo1;
        }
        else if(this.inicio == pNodo1) {
            pNodo1.siguiente = pNodo2.siguiente;
            pNodo2.siguiente.anterior = pNodo1;
            pNodo1.anterior = pNodo2;
            pNodo2.siguiente = pNodo1;
            this.inicio = pNodo2;
        }
        else if(this.cola == pNodo2) {
            // T 1 2
            pNodo2.anterior = pNodo1.anterior;
            pNodo1.anterior.siguiente = pNodo2;
            pNodo2.siguiente = pNodo1;
            pNodo1.anterior = pNodo2;
            pNodo1.siguiente = null;
            this.cola = pNodo1;
        }
        else {
            Nodo nuevoSiguienteNodo1 = pNodo2.siguiente;
            pNodo1.anterior.siguiente = pNodo2;
            pNodo2.anterior = pNodo1.anterior;
            pNodo2.siguiente = pNodo1;
            nuevoSiguienteNodo1.anterior = pNodo1;
            pNodo1.siguiente = nuevoSiguienteNodo1;
            pNodo1.anterior = pNodo2;
        }
    }
}
