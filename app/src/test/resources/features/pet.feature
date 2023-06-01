#language: pt

  Funcionalidade: Gerenciamento de um animal da Loja

    Cenario: Lista somente animais disponíveis para a venda
      Dado que eu possua animais available
      Quando eu pesquiso por todos os animais available
      Entao eu recebo a lista de animais available
#     Passo desnecessario, somente para exemplos
      E eu recebo uma outra lista de animais available

    Cenario: Lista somente animais pending
      Dado que eu possua animais pending
      Quando eu pesquiso por todos os animais pending
      Entao eu recebo a lista com 2 animais

    Cenario: Nao lista nenhum animal
      Dado que eu nao possua animais sold
      Quando eu pesquiso por todos os animais sold
      Entao eu recebo a lista com 0 animal

    Esquema do Cenario: Lista animais pelo seu estado de venda
      Dado que eu nao possua animais sold
      Quando eu pesquiso por todos os animais <estado>
      Entao eu recebo a lista com <quantidade> animais

    Exemplos: Animais em estoque
      | estado    | quantidade |
      | available | 7          |
      | pending   | 2          |

    Exemplos: Animais sem estoque
      | estado | quantidade |
      | sold   | 0          |


    Cenario: Lista animais disponiveis para venda
      Dado que eu possua animais available
      Quando pesquiso por todos os animais available
      Entao recebo a lista com 7 animais available
      E 3 animais possuem o nome Lion

