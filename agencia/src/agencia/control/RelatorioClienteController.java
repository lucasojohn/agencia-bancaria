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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import util.EntityManagerUtil;

/**
 *
 * @author lucas
 */
public class RelatorioClienteController {
    
    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    ContaClienteDAO contaClienteDao = new ContaClienteDAO();
    
    @FXML
    private Button cancelar;
    @FXML
    private TextField dados;
    @FXML
    private Button buscar;
    @FXML
    private TextField cpf;
    @FXML
    private Label msgErro;
    @FXML
    private List<Conta> contasCliente = null;
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
    
    public void buscarCliente(ActionEvent event) {
        
        this.cliente = clienteDao.buscaCliente(cpf.getText());
        this.contasCliente = contaDao.buscaPorCliente(this.cliente);

        if(this.cliente == null){
            msgErro.setVisible(true);
            dados.setText("");
            return;
        }
         msgErro.setVisible(false);
        // limpa campos da pesquisa anterior
        limpaCampos();

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
        dados.setText(descricao);
    }
    
    private void limpaCampos() {
        dados.setText("");
    }
    
    public static void fecharUmaTela(ActionEvent event){
        Node no = (Node) event.getSource();
        
        while (no != null && !(no instanceof TitledPane)) {
            no = no.getParent();
        }
        
        if (no != null) {
            TitledPane tp = (TitledPane)no;
            tp.setContent(null);
            tp.setVisible(false);
        }
    }
}
