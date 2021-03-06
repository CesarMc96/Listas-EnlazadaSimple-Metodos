package Principal;

import Excepciones.ElementoNoEncontradoException;
import Excepciones.ListaVaciaException;
import java.util.ArrayList;
import java.util.Objects;

public class Lista {

    private Nodo inicio;
    private Integer contador;

    public Lista() {
        inicio = null;
        contador = 0;
    }

    public void insertarFinal(Integer elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (ListaVacia()) {
            inicio = nuevo;
        } else {
            Nodo aux = inicio;

            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }

        sumarContador();
    }

    public boolean ListaVacia() {
        return inicio == null;
    }

    public void insertarPrincipio(Integer elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (ListaVacia()) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        sumarContador();
    }

    public void recorrerLista() {
        Nodo aux = inicio;
        while (aux != null) {
            System.out.println(aux.getElemento());
            aux = aux.getSiguiente();
        }
    }

    public void insertarOrdenado(Integer elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (ListaVacia()) {
            inicio = nuevo;
        } else {
            Nodo aux = inicio;
            Nodo anterior = null;

            while (aux != null && aux.getElemento() < elemento) {
                anterior = aux;
                aux = aux.getSiguiente();
            }

            if (anterior == null) {
                nuevo.setSiguiente(inicio);
                inicio = nuevo;
            } else {
                anterior.setSiguiente(nuevo);
                nuevo.setSiguiente(aux);
            }
        }
        sumarContador();
    }

    public boolean existeElemento(Integer elemento) {
        Nodo aux = inicio;

        while (aux != null && !Objects.equals(aux.getElemento(), elemento)) {
            aux = aux.getSiguiente();
        }

        return aux != null;
    }

    public boolean estaOrdenada() {
        Nodo aux = inicio;
        boolean bandera = true;

        while (aux != null && bandera) {
            bandera = aux.getElemento() != null && aux.getElemento() <= aux.getSiguiente().getElemento();
            aux = aux.getSiguiente();
        }

        return aux != null;
    }

    public void sumarContador() {
        contador++;
    }

    public Integer totalElementos() {
        return contador;
    }

    public void eliminarInicio() {
        if (!ListaVacia()) {
            Nodo aux = inicio;
            inicio = inicio.getSiguiente();
            aux.setSiguiente(null);
        }
    }

    public void eliminarFinal() {
        Nodo aux = inicio;
        Nodo ant = null;

        while (aux != null && aux.getSiguiente() == null) {
            ant = aux;
            aux = aux.getSiguiente();
        }

        if (aux != null) {
            if (ant == null) {
                inicio = null;
            } else {
                ant.setSiguiente(null);
            }
        }
    }

    public void elementarElemento(Integer elemento) throws ElementoNoEncontradoException {
        Nodo aux = inicio;
        Nodo ant = null;

        while (aux != null && !Objects.equals(aux.getElemento(), elemento)) {
            ant = aux;
            aux = aux.getSiguiente();
        }

        if (aux != null) {
            throw new ElementoNoEncontradoException();
        } else if (ant == null) {
            inicio = inicio.getSiguiente();
            aux.setSiguiente(null);
        } else {
            ant.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(null);
        }
    }

    public void eliminarElementoRepetido(Integer elemento) throws ElementoNoEncontradoException {
        Nodo aux = inicio;
        Nodo ant = null;
        Boolean bandera = false;

        while (aux != null) {
            if (Objects.equals(aux.getElemento(), elemento)) {
                bandera = true;
                if (ant == null) {
                    inicio = inicio.getSiguiente();
                    aux.setSiguiente(null);
                    aux = inicio;
                } else {
                    ant.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(null);
                    aux = ant.getSiguiente();
                }
            } else {
                ant = aux;
                aux = aux.getSiguiente();
            }
        }

        if (bandera) {
            throw new ElementoNoEncontradoException();
        }
    }

    public void agregar(Lista lista) {
        if (ListaVacia()) {
            inicio = lista.inicio;
        } else {
            Nodo aux = inicio;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(lista.inicio);
        }
    }

    public Integer sacarElemento() throws ListaVaciaException {
        if (ListaVacia()) {
            throw new ListaVaciaException();
        } else {
            Integer resultado = inicio.getElemento();
            Nodo aux = inicio;
            inicio = inicio.getSiguiente();
            aux.setSiguiente(null);
            return resultado;
        }
    }

    public Integer getMayor() {
        int mayor = 0;
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.getElemento() > mayor) {
                mayor = aux.getElemento();
            }
            aux = aux.getSiguiente();
        }
        return mayor;
    }

    //Metodos de Ordenamiento 
    public void ordenarBurbuja() {
        Nodo aux2 = inicio;
        Nodo aux1 = inicio;

        while (aux2 != null) {
            aux1 = aux2.getSiguiente();
            while (aux1 != null) {
                if (aux1.getElemento().compareTo(aux2.getElemento()) < 0) {
                    int temporal = aux2.getElemento();
                    aux2.setElemento(aux1.getElemento());
                    aux1.setElemento(temporal);
                }
                aux1 = aux1.getSiguiente();
            }
            aux2 = aux2.getSiguiente();
        }

    }

    public void ordenarQuicksort() {
        Nodo primero;
        Nodo ultimo;
        Nodo aux;
        ArrayList pivote[] = new ArrayList[2];

        Integer[] division = new Integer[totalElementos()];

        for (int i = 0; i < totalElementos(); i++) {
            division[i] = 0;
        }

        pivote[0] = new ArrayList();
        pivote[1] = new ArrayList();

        if (totalElementos() > 0) {
            division[0] = 1;
            division[totalElementos() - 1] = 1;
            pivote[0].remove(0);
            pivote[1].remove(0);
        }

        while (!pivote[0].isEmpty()) {
            primero = (Nodo) pivote[0].get(0);
            ultimo = (Nodo) pivote[1].get(0);
            pivote[0].remove(0);
            pivote[1].remove(0);
        }
    }

    public void ordenarShell() {
        Integer pivote = totalElementos() / 2;
        Nodo aux = inicio; 
        Nodo aux1 = inicio; 

        for (int i = 0; aux != null && i < pivote; i++) {
            aux1 = aux1.getSiguiente();
        }
        while (pivote >= 1) {
            while (aux1 != null) {

                while (aux != null && aux.getElemento() > aux1.getElemento()) {
                    Integer k = aux.getElemento();
                    aux.setElemento(aux1.getElemento());
                    aux1.setElemento(k);
                    k = 0;
                    aux1 = aux;
                    while (aux != null && k < pivote) {
                        //aux = aux.
                        k++;
                    }
                }
                aux = aux.getSiguiente();
                aux1 = aux1.getSiguiente();
            }
            pivote /= 2;
            aux = inicio;
            aux1 = inicio;
            
            for (int k = 0; aux1 != null && k < pivote; k++) {
                aux1 = aux1.getSiguiente();
            }
        }
    }
}
