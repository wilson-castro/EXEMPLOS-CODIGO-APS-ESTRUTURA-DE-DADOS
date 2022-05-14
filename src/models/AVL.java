package models;

public class AVL {

    public class Node {
        // Referencias ao nó pai, e filhos
        private Node left, right, parent;
        // Altura da árvore, usada para manter o equilíbrio
        private int height = 1;
        // Valor que o nó carrega, por fins didáticos aqui séra inteiro
        private int value;

        // Construtor do nó que recebe um valor
        private Node (int val) {
            this.value = val;
        }
    }

    // Método que retorna altura de um nó
    private int height (Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Método que retorno o fator de balanceamento de um Nó
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        // Usando recursão para adentrar nos ramos retorando as diferenças de altura deles
        return height(N.left) - height(N.right);
    }

    // Método que viaja até a extrema direita numa folha
    private Node minValueNode(Node node) {
        Node current = node;
        // Looping que navega até a folha mais profunda com menor valor
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Método de busca pré ordenada
    public void preOrder(Node root) {
        if (root != null) {
            // navegando para dentro do nó filho a esquerda
            preOrder(root.left);
            // tratando próprio nó
            System.out.printf("%d ", root.value);
            // navegando para dentro do nó filho a direita
            preOrder(root.right);
        }
    }

    // Método de rotação para Direita, recebe um nó
    private Node rightRotate(Node y) {
        // pega o nó filho a esquerda
        Node x = y.left;
        // pega o nó filho a direita desse nó
        Node T2 = x.right;

        // ao nó filho a direita de x, atribuir o nó que sera rotacionado e é pai de x
        x.right = y;
        // ao nó a esqueda de y, atribuir o nó filho a direita de x
        y.left = T2;
        // Swapp descendo um nível

        // Atualizando a altura dos nós usando os fotores de balanceamento
        y.height = Math.max(height(y.left), height(y.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1;

        // Retornando o nó com sua posição alterada
        return x;
    }

    private Node leftRotate(Node x) {
        // pega o filho a direita do nó x
        Node y = x.right;
        //pega o filho a esquerda do filho a direita de x
        Node T2 = y.left;

        // ao nó a esqueda de y, atribuir o valor de x, o nó que será rotacionado
        y.left = x;
        // ao nó filho a direita de x, atribuir o valor do antes 'neto' a esquerda de x
        x.right = T2;

        // Atualizando a altura dos nós usando os fotores de balanceamento
        x.height = Math.max(height(x.left), height(x.right))+1;
        y.height = Math.max(height(y.left), height(y.right))+1;

        // Retornando o nó com sua posição alterada
        return y;
    }

    private Node insert(Node node, int value) {
        /* 1. Execute a rotação normal do BST */
        if (node == null) {
            return(new Node(value));
        }

        if (value < node.value)
            node.left  = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        /* 2. Atualiza a altura deste nó ancestral */
        node.height = Math.max(height(node.left), height(node.right)) + 1;

         /* 3. Obtenha o fator de equilíbrio deste nó ancestral para verificar se
        este nó ficou desbalanceado */
        int balance = getBalance(node);

        // Se este nó ficar desbalanceado, então existem 4 casos

        // Caso Esquerda Esquerda
        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        // Caso Direita Direita
        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        // Caso Esquerda Direita
        if (balance > 1 && value > node.left.value)
        {
            node.left =  leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso Direita Esquerda
        if (balance < -1 && value < node.right.value)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* retorna o ponteiro do nó (inalterado) */
        return node;
    }

    private Node deleteNode(Node root, int value) {
        // PASSO 1: EXECUTAR A DELEÇÃO PADRÃO DA BST

        if (root == null)
            return root;

        // Se o valor a ser deletado for menor que o valor da raiz,
        // então está na subárvore esquerda
        if ( value < root.value )
            root.left = deleteNode(root.left, value);

            // Se o valor a ser deletado for maior que o valor da raiz,
            // então está na subárvore direita
        else if( value > root.value )
            root.right = deleteNode(root.right, value);

            // se o valor for igual ao valor da raiz, então Este é o nó
            // Para ser deletado
        else {
            // nó com apenas um filho ou nenhum filho
            if( (root.left == null) || (root.right == null) ) {

                Node temp;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                // Caso sem filho
                if(temp == null) {
                    temp = root;
                    root = null;
                }
                else // Caso com um filho
                    root = temp; // Copia o conteúdo do filho não vazio

                temp = null;
            }
            else {
                // nó com dois filhos: Obtém o sucessor inorder (menor
                // na subárvore direita)
                Node temp = minValueNode(root.right);

                // Copia os dados do sucessor inorder para este nó
                root.value = temp.value;

                // Deletando os sucessores ordenadamente
                root.right = deleteNode(root.right, temp.value);
            }
        }

        // Se a árvore tiver apenas um nó então retorna
        if (root == null)
            return root;

        // PASSO 2: ATUALIZAR ALTURA DO NÓ ATUAL
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // PASSO 3: OBTER O FATOR DE EQUILÍBRIO DESTE NÓ (para verificar se
        // este nó ficou desbalanceado)
        int balance = getBalance(root);

        // Se este nó ficar desbalanceado, então existem 4 casos

        // Caso Esquerda Esquerda
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Caso Esquerda Direita
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }

        // Caso Direita Direita
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Caso Direita Esquerda
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

}
