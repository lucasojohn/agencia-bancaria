package agencia.control;

import agencia.Clientes;
import agencia.Conta;
import agencia.ContaCliente;
import agencia.dao.ClienteDAO;
import agencia.dao.ContaClienteDAO;
import agencia.dao.ContaDAO;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author lucas
 */
public class CadastroContaController implements Initializable {
    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    ContaClienteDAO contaClienteDao = new ContaClienteDAO();
    
    @FXML
    private ChoiceBox cliente;
    @FXML
    private RadioButton contaCorrente;
    @FXML
    private RadioButton contaPoupanca;
    @FXML
    private TextField saldo;
    @FXML
    private Button salvar;
    @FXML
    private Button cancelar;
    @FXML
    private Text sucesso;
    @FXML
    private Text validacao;
    
        
    @FXML
    public void salvarConta(ActionEvent event){
        sucesso.setText("");
        validacao.setText("");
        salvar.setDisable(true);
        cancelar.setDisable(true);
        
        if (!validaCampos()) {
            cancelar.setDisable(false);
            salvar.setDisable(false);
            return;
        }
        
        // Persistencia Conta
        
        Clientes c = clienteDao.buscaCliente(cliente.getValue().toString());
        Conta cn = new Conta();

        BigDecimal saldoDecimal = new BigDecimal(Float.toString(Float.parseFloat(saldo.getText())));
        cn.setSaldo(saldoDecimal);
        if (contaCorrente.isSelected()) {
            cn.setTpConta(contaDao.buscaTipoConta(ContaDAO.CONTA_CORRENTE));
        } else if (contaPoupanca.isSelected()) {
            cn.setTpConta(contaDao.buscaTipoConta(ContaDAO.CONTA_POUPANCA));
        }
        
        contaDao.salva(cn);
        
        // Persistencia Conta Cliente
        
        ContaCliente contaCliente = new ContaCliente();
        contaCliente.setCpfCliente(c);
        contaCliente.setNumeroConta(cn);
        
        contaClienteDao.salva(contaCliente);
        
        limpaCampos();
        
        salvar.setDisable(false);
        cancelar.setDisable(false);
        
        sucesso.setText("Conta cadastrada com sucesso.");
        sucesso.setVisible(true);
    }
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    @FXML
    public void setPoupanca(ActionEvent event){
        contaCorrente.setSelected(false);
    }
    
    @FXML
    public void setCorrente(ActionEvent event){
        contaPoupanca.setSelected(false);
    }
    
    private boolean validaCampos() {
        if (
            saldo.getText().isEmpty() || cliente.getValue() == null || 
            (contaCorrente.isSelected() || contaPoupanca.isSelected()) == false
        ) {
            validacao.setText("Campo obrigatório não preenchido.");
            validacao.setVisible(true);
            return false;
        }
        
        try {
            Float.valueOf(saldo.getText());
        }
        catch (NumberFormatException ex){
            validacao.setText("O saldo não é um decimal válido.");
            validacao.setVisible(true);
            return false;
        }
        
        return true;
    }
    
    private void limpaCampos() {
        cliente.setValue(null);
        contaCorrente.setSelected(false);
        contaPoupanca.setSelected(false);
        saldo.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Clientes> clientes = clienteDao.lista();
        
        for (Clientes c : clientes) {
            cliente.getItems().add(c.getCpf());
        }
    }    
    
    
    
}
