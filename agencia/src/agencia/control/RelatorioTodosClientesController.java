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
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author lucas
 */
public class RelatorioTodosClientesController {

    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    ContaClienteDAO contaClienteDao = new ContaClienteDAO();
    
    @FXML
    private Button sair;
    @FXML
    private TextField dados;
    private TextField cpf;
    @FXML
    private Label msgErro;
    @FXML
    private List<Conta> contasCliente = null;
    @FXML
    private List<Clientes> clientes = null;
    @FXML
    private Clientes cliente = null;
    @FXML
    private TextField nrContas;
    @FXML
    private TextField saldo;
    @FXML
    private Button btnListar;
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    public void buscarTodosClientes(ActionEvent event) {
        
        List<Clientes> listaClientes = clienteDao.lista();
        
        for (Clientes lista : listaClientes ) {
            this.cliente = clienteDao.buscaCliente(cpf.getText());
            this.contasCliente = contaDao.buscaPorCliente(this.cliente);
            
            if(this.cliente == null){
                msgErro.setVisible(true);
                dados.setText("");
            }
            msgErro.setVisible(false);
            
            String descricao = "";
            descricao += "\nCPF: " + this.cliente.getCpf();
            descricao += "\n";
            descricao += "\nNome: " + this.cliente.getNome();
            descricao += "\nData de Nascimento: " + this.cliente.getNascimento();
            descricao += "\nSexo: " + this.cliente.getSexo();
            descricao += "\nEndereço: " + this.cliente.getEndereco();
            descricao += "\nConta(s):";

            for (Conta conta : this.contasCliente) {
                descricao += "\nNumero da Conta: " + conta.getNumero();
                descricao += "\nTipo de Conta: " + conta.getTpConta();
                descricao += "\nSaldo: " + conta.getSaldo();
                descricao += "\nTaxa: " + conta.getTaxaConta();
                descricao += "\n";
            }
            
        }
        
    }
    
}
