/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agencia.control;

import agencia.Clientes;
import agencia.Conta;
import agencia.dao.ClienteDAO;
import agencia.dao.ContaClienteDAO;
import agencia.dao.ContaDAO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author lucas
 */
public class RelatorioTodosClientesController implements Initializable {

    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    ContaClienteDAO contaClienteDao = new ContaClienteDAO();
    
    private List<Clientes> clientes = null;
    @FXML
    private Button sair;
    @FXML
    private TextArea dados;
    @FXML
    private TextField cpf;
    @FXML
    private Label msgErro;
    @FXML
    private Clientes cliente = null;
    @FXML
    private TextField nrContas;
    @FXML
    private TextField saldo;
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.clientes = clienteDao.lista();
        
        if(this.clientes.size() == 0){
            dados.setText("Não existem clientes cadastrados!");
            return;
        }
        
        String descricao = "";
        for (Clientes c : this.clientes) {
            
            if(c == null){
                dados.setText("");
            }
            
            List<Conta> contasCliente = contaDao.buscaPorCliente(c);
            
            descricao += "CPF: " + c.getCpf();
            descricao += "\nNome: " + c.getNome();
            SimpleDateFormat fd = new SimpleDateFormat("dd-MM-yyyy");
            descricao += "\nData de Nascimento: " + fd.format(c.getNascimento());
            descricao += "\nSexo: " + c.getSexo();
            descricao += "\nEndereço: " + c.getEndereco();
            descricao += "\nConta(s):";

            for (Conta conta : contasCliente) {
                descricao += "\n";
                descricao += "\n  Numero da Conta: " + conta.getNumero();
                descricao += "\n  Tipo de Conta: " + conta.getTpConta().getDescricao();
                descricao += "\n  Saldo: " + conta.getSaldo();
            }
            descricao += "\n";
            descricao += "\n";
            
            dados.setText(descricao);
            
        }
        
    }  
    
}
