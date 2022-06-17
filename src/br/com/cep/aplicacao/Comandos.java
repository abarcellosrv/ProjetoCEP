package br.com.cep.aplicacao;

import br.com.cep.classes.ClienteWS;
import br.com.cep.classes.Logradouro;
import br.com.cep.classes.Teclado;
import br.com.cep.dao.CadastroDAO;
import br.com.cep.model.Cadastro;

import java.sql.SQLException;

public class Comandos {

    CadastroValidation validation = new CadastroValidation();
    CadastroDAO cadastroDAO = new CadastroDAO();

    public void salvar() throws Exception {

        System.out.println("Ola! Para ver seu endereco digite primeiro o seu nome:");
        String nomeUser = Teclado.getUmString();
        if (!this.validation.validarNome(nomeUser)) {
            System.out.println("Nome Invalido");
            System.exit(0);
        }

        System.out.println("Agora digite seu CEP, somente os numeros!");
        Integer cepUser = Teclado.getUmInt();
        if (!validation.validarCep(cepUser.toString())) {
            System.out.println("CEP Invalido");
            System.exit(0);
        }

        System.out.println("Agora digite o numero da sua casa.");
        Integer numeroUser = Teclado.getUmInt();
        if (!validation.validarNumero(numeroUser.toString())) {
            System.out.println("Numero Invalido");
            System.exit(0);
        }

        System.out.println("E se a sua casa tiver complemento, digite aqui. Se nao, digite casa");
        String complementoUser = Teclado.getUmString();

        Cadastro cadastroUser = new Cadastro(nomeUser, cepUser, numeroUser, complementoUser);
        cadastroDAO.save(cadastroUser);

        Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cadastroDAO.getUltimoCadastro().getCep());
        System.out.println("Confirmando dados:\n Você e " + cadastroDAO.getUltimoCadastro().getNome());
        System.out.println("Voce mora em: " + logradouro.getLogradouro());
        System.out.println("No bairro: " + logradouro.getBairro());
        System.out.println("Na cidade de " + logradouro.getCidade());
        System.out.println("No estado de " + logradouro.getEstado());
        System.out.println("Numero " + cadastroDAO.getUltimoCadastro().getNumero());
        System.out.println("Com o complemento: " + cadastroDAO.getUltimoCadastro().getComplemento());
    }

    public void consultarTodos() throws SQLException {
        System.out.println("Os usuarios cadastrados sao:");
        for (Cadastro c : cadastroDAO.getContatos()) {
            Logradouro logradouroContatos = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", c.getCep());
            System.out.println("=============================================");
            System.out.println("Nome: " + c.getNome());
            System.out.println("Rua: " + logradouroContatos.getLogradouro());
            System.out.println("Bairro: " + logradouroContatos.getBairro());
            System.out.println("Cidade: " + logradouroContatos.getCidade());
            System.out.println("Estado: " + logradouroContatos.getEstado());
            System.out.println("Numero: " + c.getNumero());
            System.out.println("Complemento: " + c.getComplemento());
        }
    }

    public void apagar() throws Exception {
        System.out.println("Qual o ID desejado?");
        Integer delete = Teclado.getUmInt();
        cadastroDAO.deleteUser(delete);
    }

    public void atualizar() throws Exception {
        System.out.println("Qual o ID desejado?");
        Integer idUpdate = Teclado.getUmInt();
        System.out.println("Qual o campo desejado?");
        String campoUpdate = Teclado.getUmString();
        System.out.println("Qual o novo valor");
        String valorUpdate = Teclado.getUmString();
        cadastroDAO.updateUser(idUpdate, campoUpdate, valorUpdate);
    }

    public void consultarPorID() throws Exception {
        System.out.println("Digite o ID que deseja buscar: ");
        Integer id = Teclado.getUmInt();
        Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cadastroDAO.getCadastroPorID(id).getCep());
        System.out.println("Confirmando dados:\n Você e " + cadastroDAO.getCadastroPorID(id).getNome());
        System.out.println("Voce mora em: " + logradouro.getLogradouro());
        System.out.println("No bairro: " + logradouro.getBairro());
        System.out.println("Na cidade de " + logradouro.getCidade());
        System.out.println("No estado de " + logradouro.getEstado());
        System.out.println("Numero " + cadastroDAO.getCadastroPorID(id).getNumero());
        System.out.println("Com o complemento: " + cadastroDAO.getCadastroPorID(id).getComplemento());
    }
}
