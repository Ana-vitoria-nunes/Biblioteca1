package org.example.view;
import org.example.model.BookModel;
import org.example.service.BookService;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookView {

    private final Scanner scanner;
    private final BookModel bookModel;
    private final BookService bookService;

    public BookView() {
        scanner = new Scanner(System.in);
        bookModel = new BookModel();
        bookService = new BookService();
    }

    public void iniciar() {
        int opcao;

        do {
            imprimirMenu();
            opcao = selecionarOpcao();

            switch (opcao) {
                case 1 -> bookService.consultaTodosLivros();
                case 2 -> {
                    System.out.println("Digite o nome do livro: ");
                    String name = scanner.nextLine();
                    System.out.println("Digite o autor do livro: ");
                    String author = scanner.nextLine();
                    System.out.println("Digite a data de lançamento do livro (AAAA-MM-DD): ");
                    String releaseDate = scanner.nextLine();
                    System.out.println("Digite o código do livro: ");
                    String code = scanner.nextLine();
                    bookModel.setName(name);
                    bookModel.setAuthor(author);
                    bookModel.setReleaseDate(LocalDate.parse(releaseDate));
                    bookModel.setCode(code);
                    bookService.inserirLivro(bookModel.getName(), bookModel.getAuthor(),
                            bookModel.getReleaseDate().toString(), bookModel.getCode());
                }
                case 3 -> {
                    System.out.println("Digite o ID do livro que deseja alterar: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine(); // Limpar a quebra de linha
                    System.out.println("Digite o novo nome do livro: ");
                    String novoNome = scanner.nextLine();
                    System.out.println("Digite o novo autor do livro: ");
                    String novoAutor = scanner.nextLine();
                    System.out.println("Digite a nova data de lançamento do livro (AAAA-MM-DD): ");
                    String novaDataLancamento = scanner.nextLine();
                    System.out.println("Digite o novo código do livro: ");
                    String novoCodigo = scanner.nextLine();
                    bookService.alterarLivro(idAlterar, novoNome, novoAutor, novaDataLancamento, novoCodigo);
                }
                case 4 -> {
                    System.out.println("Digite o ID do livro que deseja deletar: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Limpar a quebra de linha
                    bookService.deletarLivro(idDeletar);
                }
                case 5 -> {
                    System.out.println("Digite o ID do livro que deseja consultar: ");
                    int idConsulta = scanner.nextInt();
                    scanner.nextLine(); // Limpar a quebra de linha
                    bookService.consultaLivroPorId(idConsulta);
                }
                case 0 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (opcao != 0);
    }

    public void imprimirMenu() {
        System.out.println("Digite uma das seguinte opções");
        System.out.println("1 - Consultar todos os dados dos livros.");
        System.out.println("2 - Inserir um novo livro.");
        System.out.println("3 - Alterar um livro.");
        System.out.println("4 - Deletar um livro.");
        System.out.println("5 - Consultar  livros por Id.");
        System.out.println("0 - Sair.");
    }

    public int selecionarOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        return 1;
    }

}
