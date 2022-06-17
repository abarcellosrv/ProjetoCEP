package br.com.cep.aplicacao;

import br.com.cep.classes.ClienteWS;
import br.com.cep.classes.Logradouro;
import br.com.cep.classes.Teclado;
import br.com.cep.dao.CadastroDAO;
import br.com.cep.model.Cadastro;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        Integer opcao = 66;

        Comandos comandos = new Comandos();
        while (opcao != 6) {
            System.out.println("O que gostaria de fazer?");
            System.out.println("1- Inserir novo cadastro");
            System.out.println("2- Consultar cadastro específico por ID");
            System.out.println("3- Consultar todos os cadastros");
            System.out.println("4- Atualizar cadastro");
            System.out.println("5- Deletar cadastro");
            System.out.println("6- Sair");
            System.out.println("Digite o número da opção desejada:");
            opcao = Teclado.getUmInt();

            switch (opcao) {
                case 1:
                    comandos.salvar();
                    break;

                case 2:
                    comandos.consultarPorID();
                    break;
                case 3:
                    comandos.consultarTodos();
                    break;

                case 4:
                    comandos.atualizar();
                    break;

                case 5:
                    comandos.apagar();
                    break;
            }
        }


    }
}
