# Desenvolvimento Centrado em Objetos - Trabalho Prático 1

## Descrição do Projeto

Este projeto é uma aplicação de biblioteca de músicas que permite gerenciar músicas, playlists e suas avaliações. A aplicação foi desenvolvida utilizando o paradigma de programação orientada a objetos.

## Estrutura do Projeto

- `src/`: Contém o código-fonte da aplicação.
  - `client/`: Código relacionado ao cliente.
  - `domain/`: Código relacionado ao domínio da aplicação.
    - `core/`: Classes principais do domínio, como `MusicLibrary`, `Rate`, `Song`, etc.
    - `facade/`: Classes de fachada, como `MusicLibraryController`, `PlaylistListController`, etc.
    - `playlists/`: Classes relacionadas a playlists, como `AbsPlaylist`, `MostLikedSongsPlaylist`, `PlaylistList`, etc.
  - `servicos/`: Serviços utilizados pela aplicação.
  - `ui/`: Código relacionado à interface do usuário.
  - `util/`: Utilitários usados pela aplicação.
- `tests/`: Contém os testes da aplicação.
  - `ArrayQListWithSelectionTests.java`: Testes para a classe `ArrayQListWithSelection`.
  - `SongTests.java`: Testes para a classe `Song`.
- `lib/`: Contém bibliotecas externas utilizadas pela aplicação.
  - `jl1.0.1.jar`
  - `mp3agic-0.9.1.jar`
  - `swt.jar`
- `docs/`: Documentação do projeto.
- `songs/`: Contém arquivos de músicas para teste.
- `.metadata/`, `.settings/`, `bin/`, `.classpath`, `.project`: Arquivos e pastas de configuração do Eclipse.

## Classes Principais

### AbsQListWithSelection

Na classe abstrata `AbsQListWithSelection.java`, usamos dois atributos (`E selected` e `List<E> objList`) para representar o objeto selecionado e a lista de todos os objetos, respectivamente. A inicialização da lista é feita através do método `createList()`, que pode ser sobrescrito pela classe que estende `AbsQListWithSelection`.

### Rate

Na classe `Rate.java`, implementamos três valores: `Rate.LOW`, `Rate.MID` e `Rate.HIGH`. Além dos métodos necessários, criamos outros métodos para comparar diferentes `Rate`.

### MusicLibraryController

Na classe `MusicLibraryController.java`, realizamos a leitura dos meta-dados das músicas a partir de arquivos mp3 utilizando a biblioteca de código aberto `com.mpatric.mp3agic`.

### MusicLibrary e AbsPlaylist

Nas classes `MusicLibrary.java` e `AbsPlaylist.java`, usamos um atributo `songPlaying` que representa a música que está tocando. Se nenhuma música estiver tocando, este valor é `null`.

### MostRecentlyAddedSongsPlaylist e MostLikedSongsPlaylist

A classe `MostRecentlyAddedSongsPlaylist.java` representa uma playlist com no máximo 5 músicas que foram mais recentemente adicionadas à biblioteca. A classe `MostLikedSongsPlaylist.java` representa uma playlist com as 5 músicas com maior `Rate`, que são adicionadas quando ocorre um evento de alteração dos seus rates.

## Como Executar

1. Clone o repositório.
2. Execute a classe `GUIClient` ou `SimpleClient` para iniciar a aplicação.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.