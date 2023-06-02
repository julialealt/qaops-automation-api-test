#language: pt

Funcionalidade: Gerenciamento de pedidos da loja

  @DeleteExtraPets @sanity
  Cenario: Cliente cria um pedido na loja
    Dado que eu possua animal available
    Quando faco o pedido desse animal
    Entao o pedido eh aprovado
