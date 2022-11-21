package agencia.control;

import agencia.Clientes;
import agencia.Conta;
import agencia.TipoConta;
import agencia.dao.ClienteDAO;
import agencia.dao.ContaDAO;
import agencia.dao.TpContaDAO;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.PersistenceException;

/**
 *
 * @author lucas
 */
public class CadastroClienteController implements Initializable {
    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    
    @FXML
    private TextField cpf;
    @FXML
    private TextField nome;
    @FXML
    private DatePicker dataNasc;
    @FXML
    private RadioButton masculino;
    @FXML
    private RadioButton feminino;
    @FXML
    private TextField endereco;
    @FXML
    private RadioButton contaCorrente;
    @FXML
    private RadioButton contaPoupanca;
    @FXML
    private TextField saldo;
    @FXML
    private Button cancelar;
    @FXML
    private Button salvar;
    @FXML
    private Text validacao;
    @FXML
    private Text sucesso;
    
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    @FXML
    public void salvarCliente(ActionEvent event){
        sucesso.setVisible(false);
        validacao.setVisible(false);
        
        cancelar.setDisable(true);
        salvar.setDisable(true);
        
        if (!validaCampos()) {
            cancelar.setDisable(false);
            salvar.setDisable(false);
            return;
        }
        
        Clientes cliente = new Clientes();
        Conta conta = new Conta();
        
        java.sql.Date dataDatePicker = java.sql.Date.valueOf(dataNasc.getValue());     
        cliente.setCpf(cpf.getText());
        cliente.setNome(nome.getText());
        cliente.setEndereco(endereco.getText());
        cliente.setNascimento(dataDatePicker);
        if (feminino.isSelected()) {
            cliente.setSexo("f");
        } else if (masculino.isSelected()) {
            cliente.setSexo("m");
        }

        try {
            clienteDao.salva(cliente);
        } catch (PersistenceException e) {
            validacao.setText("CPF já cadastrado.");
            validacao.setVisible(true);
            cancelar.setDisable(false);
            salvar.setDisable(false);
            return;
        }
        
        BigDecimal saldoDecimal = new BigDecimal(Float.toString(Float.parseFloat(saldo.getText())));
        conta.setSaldo(saldoDecimal);
        if (contaCorrente.isSelected()) {
            conta.setTpConta(contaDao.buscaTipoConta(TpContaDAO.CONTA_CORRENTE));
        } else if (contaPoupanca.isSelected()) {
            conta.setTpConta(contaDao.buscaTipoConta(TpContaDAO.CONTA_POUPANCA));
        }
        
        contaDao.salva(conta);
        
        cancelar.setDisable(false);
        salvar.setDisable(false);
        limpaCampos();
        sucesso.setText("Cliente cadastrado com sucesso.");
        sucesso.setVisible(true);
    }
    
    @FXML
    public void setFeminino(ActionEvent event){
        masculino.setSelected(false);

    }
    
    @FXML
    public void setMasculino(ActionEvent event){
        feminino.setSelected(false);

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
        if (saldo.getText().isEmpty()) {
            saldo.setText("0");
        }
        
        try {
            Float.valueOf(saldo.getText());
        }
        catch (NumberFormatException ex){
            validacao.setText("O saldo não é um decimal válido.");
            validacao.setVisible(true);
            return false;
        }
        
        if (
                cpf.getText().isEmpty() || nome.getText().isEmpty() || 
                endereco.getText().isEmpty() || dataNasc.getValue() == null ||
                (feminino.isSelected() || masculino.isSelected()) == false ||
                (contaCorrente.isSelected() || contaPoupanca.isSelected()) == false
            ) {
            validacao.setText("Campo obrigatório não preenchido.");
            validacao.setVisible(true);
            return false;
        }
        
        if (clienteDao.buscaCliente(cpf.getText()) != null) {
            validacao.setText("CPF já cadastrado.");
            validacao.setVisible(true);
            return false;
        }
        
        if (cpf.getText().length() != 11) {
            validacao.setText("O CPF deve possuir 11 dígitos.");
            validacao.setVisible(true);
            return false;
        }
        
        return true;
    }
    
    private void limpaCampos() {
        cpf.setText("");
        nome.setText("");
        endereco.setText("");
        dataNasc.setValue(null);
        feminino.setSelected(false);
        masculino.setSelected(false);
        contaCorrente.setSelected(false);
        contaPoupanca.setSelected(false);
        saldo.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
