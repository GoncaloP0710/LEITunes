Desenvolvimento Centrado em Objetos
Trabalho Prático 1
Gonçalo Pinto - Fc58178 e Pedro Pilô - Fc58179

Na classe abstrata AbsQListWithSelection.java decidimos usar dois atributos (E selected; List<E> objList), para representar o objeto selecionado e a lista de todos os objetos, respetivamente. A inicialização da lista é feita através do método createList() que com base na implementação desejada, a classe que estende a mesma pode definir a sua própria versão da lista ao dar @override deste método, que é o caso de ArrayQListWithSelection<E>.

Na classe Rate.java decidimos implementar os 3 seguintes valores: Rate.LOW, Rate.MID e Rate.HIGH. Para além dos métodos necessários criamos outros métodos para comparar diferentes Rates.
Na classe MusicLibraryController.java onde foi necessário a leitura dos meta-dados das músicas a partir de ficheiros mp3. Esta leitura foi realizada através da utilização da biblioteca de código aberto: com.mpatric.mp3agic. Através desta, conseguimos verificar o seu tipo de tag e retirar as informações necessárias da mesma.

Nas classes MusicLibrary.java e AbsPlaylist.java é usado um atributo songPlaying que representa a música que esta a tocar e se nenhuma estiver, este valor é null. 

A classe MostRecentlyAddedSongsPlaylist.java representa uma playlist com no máximo, as 5 músicas que foram mais recentemente adicionadas a biblioteca e a classe MostLikedSongsPlaylist.java que por sua vez, representa uma playlist com as 5 músicas com maior Rate porém estas apenas são adicionadas quando ocorre um evento de alteração dos seus rates.

Embora os commits no git terem sido realizados por apenas um membro do grupo, ambos os elementos ajudaram a realizar o projeto de forma igual.

