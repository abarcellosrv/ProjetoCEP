package br.com.cep.aplicacao;

public class CadastroValidation {

    public boolean validarNome(String nome){
        if (nome.isBlank()){
            return false;
        }
        return true;
    }

    public boolean validarCep(String cep){
        if (cep.isBlank()|| cep.length() != 8){
            return false;
        }
        if (!cep.matches("[0-9]+")){
            return false;
        }
        return true;
    }

    public boolean validarNumero(String numero){
        if (numero == "" || numero ==null){
            return false;
        }
        if (!numero.matches("[0-9]+")){
            return false;
        }
        return true;
    }

}
