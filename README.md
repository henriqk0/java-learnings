
# Relatório - Análise da Aplicação AppRelatorioArvoreBinaria

## 📅 Data: [Insira a data do relatório]
## 👨‍💻 Autor(es): [Insira o(s) nome(s) do(s) autor(es)]

---

## 📌 1. Topologia das Árvores Criadas pelo Método `geraArvoreDegenerada`

**Descrição da Topologia:**  
Descreva a estrutura da árvore degenerada e como ela se comporta em termos de balanceamento e distribuição dos nós.

**Exemplo:**  
Inclua um exemplo específico, apresentando a estrutura da árvore gerada.  
```
Exemplo de árvore degenerada com 5 elementos:  
     1  
      \  
       2  
        \  
         3  
          \  
           4  
            \  
             5  
```

---

## 📌 2. Número de Nós Percorridos em Árvores Degeneradas

- **Árvore com 100 elementos:** [Insira o número de nós percorridos]
- **Árvore com 200 elementos:** [Insira o número de nós percorridos]
- **Árvore com 1000 elementos:** [Insira o número de nós percorridos]

**Justificativa:**  
Explique o raciocínio utilizado para chegar a esses números, considerando a estrutura linear da árvore degenerada.

---

## 📌 3. Complexidade da Busca em Árvores Degeneradas

**Ordem de Complexidade:** `O(n)`  
**Explicação:**  
A busca em uma árvore degenerada é equivalente a percorrer uma lista encadeada, resultando em uma complexidade linear.

---

## 📌 4. Topologia das Árvores Criadas pelo Método `geraArvorePerfeitamenteBalanceada`

**Descrição da Topologia:**  
Explique como a árvore balanceada é estruturada e organizada.  

**Exemplo:**  
Inclua um exemplo ilustrativo da árvore balanceada.  
```
Exemplo de árvore balanceada com 7 elementos:  
       4  
      / \  
     2   6  
    / \ / \  
   1  3 5  7  
```

---

## 📌 5. Número de Nós Percorridos em Árvores Balanceadas

- **Árvore com 100 elementos:** [Insira o número de nós percorridos]
- **Árvore com 200 elementos:** [Insira o número de nós percorridos]
- **Árvore com 1000 elementos:** [Insira o número de nós percorridos]

**Justificativa:**  
Detalhe como o balanceamento da árvore afeta a quantidade de nós percorridos durante a busca.

---

## 📌 6. Complexidade da Busca em Árvores Balanceadas

**Ordem de Complexidade:** `O(log n)`  
**Explicação:**  
A estrutura balanceada permite que a busca seja realizada de forma binária, reduzindo o número de comparações necessárias.

---

## 📌 7. Busca por Nome Utilizando `ComparadorAlunoPorNome`

**Número de Nós Percorridos no Pior Caso:** [Insira o valor]  
**Complexidade da Busca:** `O(n)`  
**Explicação:**  
Ao utilizar um comparador baseado no nome em uma árvore indexada por matrícula, a busca perde o benefício da estrutura ordenada.

---

## 📌 8. Comparação de Complexidade dos Métodos de Geração

- **Método `geraArvoreDegenerada`:** `O(n)`
- **Método `geraArvorePerfeitamenteBalanceada`:** `O(n log n)`

**Conclusão:**  
Explique qual dos métodos é mais eficiente em termos de tempo de execução e por quê.

---

## 📌 9. Problema na Geração de Árvores com 50.000 Elementos

**Erro Identificado:** [Descreva o erro, se houver]  
**Momento do Erro:** [Indique o momento exato em que o erro ocorre]  
**Causa do Erro:** [Explique a razão do erro]

---

## 📌 10. Requisitos Funcionais e Funcionamento do Aplicativo

**Requisitos Funcionais:**  
- [Requisito 1]
- [Requisito 2]
- [Requisito 3]

**Funcionamento do Aplicativo:**  
Descreva o fluxo geral de execução do aplicativo, destacando as funcionalidades principais.

---

## 📌 11. Arquitetura da Aplicação

**Estrutura da Aplicação:**  
- **Pacote de Geradores:** [Descrição da função do pacote]
- **Pacote de Comparadores:** [Descrição da função do pacote]
- **Pacote de Árvore Binária:** [Descrição da função do pacote]

**Diagrama de Classes:**  
Opcional: insira um diagrama UML para ilustrar a estrutura.

---

## 📌 12. Planilha de Desenvolvimento por Membro do Grupo

| Membro         | Tarefa                          | Status  |
|----------------|---------------------------------|---------|
| [Nome 1]       | Implementação da árvore binária | Concluído |
| [Nome 2]       | Criação dos métodos de geração  | Em andamento |
| [Nome 3]       | Implementação dos comparadores  | Concluído |

---

## 📦 Anexos

- Código-fonte do projeto
- Capturas de tela da execução do aplicativo
- Diagramas UML
- Planilha de desenvolvimento
