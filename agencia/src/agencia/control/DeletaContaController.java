package agencia.control;

import agencia.Clientes;
import agencia.Conta;
import agencia.dao.ClienteDAO;
import agencia.dao.ContaClienteDAO;
import agencia.dao.ContaDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DeletaContaController implements Initializable {
    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    ContaClienteDAO contaClienteDao = new ContaClienteDAO();
        
    @FXML
    private Button deletar;

    @FXML
    private ChoiceBox cliente;

    @FXML
    private ChoiceBox comboConta;

    @FXML
    private Button cancelar;

    @FXML
    private TextField saldo;
    
    @FXML
    private Text sucesso;
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    @FXML
    public void deletar(ActionEvent event) {
        sucesso.setText("");
        deletar.setDisable(true);
        cancelar.setDisable(true);
       
        contaClienteDao.deletaPorConta(contaDao.buscaId((Long) comboConta.getValue()));
        contaDao.deleta((Long) comboConta.getValue());
        comboConta.getItems().clear();
        
        limpaCampos();
        
        deletar.setDisable(false);
        cancelar.setDisable(false);
        
        sucesso.setText("Conta deletada com sucesso.");
        sucesso.setVisible(true);
    }

        
    @FXML
    public void popularSaldo(ActionEvent event){       
        if (comboConta.getValue() != null) {
            Conta c = contaDao.buscaId((Long) comboConta.getValue());
            saldo.setText(c.getSaldo().toString());
        }
        
        deletar.setDisable(false);
    }
    
    @FXML
    public void habilitarConta(ActionEvent event){  
        if (cliente.getValue() != null) {
            comboConta.setDisable(false);
            List<Conta> contas = contaDao.lista();
            for (Conta conta : contas) {
                comboConta.getItems().add(conta.getNumero());
            }
        }
    }  
  
    private void limpaCampos() {
        cliente.setValue(null);
        comboConta.setValue(null);
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


