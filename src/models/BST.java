package models;

import java.util.Deque;
import java.util.LinkedList;

// Declaração da classe Binary Search Tree
public class BST {

    // Declaração da classe Nó
   private static class Node {

        int value;   // Valor do nó
        Node left;   // Nó filho localizado na esquerda
        Node right;  // Nó filho localizado na esquerda
        Node parent; // Nó pai

        // Construtor da classe nó
        Node(int v) { this.value = v; }

    }

    private Node root; // Referência da árvore ao nó raiz
    // o Nó raiz apontará para os seus filhos a esquerda e a direita
    // e esses os seus descendentes, assim a rede de referências está feita


    public boolean isEmpty() {
        return this.root == null;
    }

        // Declaração do método pre-ordem a modo encapsulado
        public void preOrder() {
            // Chamada do metodo privado de pre ordem utilizando da raiz como início
            preOrder(this.root);
        }

        // Método privado pré-ordem que recebe um nó como parametro
        private void preOrder(Node node) {
            // Caso nó seja nulo, ele não faz nada
            if (node != null) {
                // Demostra o nó atual
                System.out.println(node.value);
                // O método vai adentrando até que este não tenha nada como filho de determinado lado
                // Assim não faz nada e segue o continua, a outra chamada de recursão

                // Chama o filho a esquerda do nó, caso ele não tenha ira cair no if
                preOrder(node.left);

                // Chama o filho a esquerda do nó, caso ele não tenha ira cair no if
                preOrder(node.right);

                // Ao atingir os folhas, ele não fará nada e seguirá para o pŕoxima recursão
            }
        }

        // Declararação do método público chamando o privado visando encapsular
        public void inOrder() {
            // Chamada do método em ordem privado apontando a raiz como ponto de partida
            inOrder(this.root);
        }

        // Declaração do método privado em Ordem
        private void inOrder(Node node) {
            // Caso o nó seja null não faz nada
            if (node != null) {
                // Adentrando nos ramos mais a esqueda
                inOrder(node.left);
                // Imprimindo o próprio nó
                System.out.println(node.value);
                // Adentrando nos ramos mais a direita
                inOrder(node.right);
            }
        }

        // Declararação do método público chamando o privado visando encapsular
        public void posOrder() {
            // Chamada do método em ordem privado apontando a raiz como ponto de partida
            posOrder(this.root);
            }

        // Declaração do método privado em Ordem
        private void posOrder(Node node) {
            // Caso o nó seja null não faz nada

            if (node != null) {
                // Adentrando nos ramos mais a esqueda
                posOrder(node.left);
                // Adentrando nos ramos mais a direita
                posOrder(node.right);
                // Imprimindo o próprio nó
                System.out.println(node.value);
                }
        }


        // Declaração do método de impressão utilizando a busca em Largura
        public void printBFS() {
            // Utilização de uma recebendo a interface da linkedList com Node em parametro
            Deque<Node> queue = new LinkedList<Node>();
            // Checando se a árvore está vazia
            if (!isEmpty()) {
                // Adicionando a raiz a lista na última posição, nesse caso primeira
                queue.addLast(this.root);
                // Checando se a lista está vazia, enquanto não estiver
                while (!queue.isEmpty()) {
                    // No atual recebendo o primeiro elemento da lista que é retirado
                    Node current = queue.removeFirst();
                    //printando o último/atual nó
                    System.out.println(current);
                    // Caso o elemento da esqueda não esteja nulo
                    if(current.left != null)
                        //adicionando a última posição
                        queue.addLast(current.left);
                    // Caso o elemento da direita não esteja nulo
                    if(current.right != null)
                        //adicionando a última posiçãp
                        queue.addLast(current.right);
                }
            }
            // o funcionamento é baseado no fato de a lista receber o elemento nó e mostrá-lo
            // adicioná-lo num ambiente que checa se tem  filho
            // caso ele tenha filho, os filhos são adicionados primeiro esquerda depois direito
        }
}