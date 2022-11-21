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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import util.EntityManagerUtil;

/**
 *
 * @author lucas
 */
public class DeletaClienteController implements Initializable {
    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    private EntityManager em;
    
    @FXML
    private Button deletar;
    @FXML
    private TextField dataNasc;
    @FXML
    private Button cancelar;
    @FXML
    private TextField endereco;
    @FXML
    private Button buscar;
    @FXML
    private RadioButton contaCorrente;
    @FXML
    private TextField cpf;
    @FXML
    private RadioButton contaPoupanca;
    @FXML
    private RadioButton masculino;
    @FXML
    private TextField nome;
    @FXML
    private TextField saldo;
    @FXML
    private RadioButton feminino;
    @FXML
    private TextField nrContas;
    @FXML
    private Label msgErro;
    
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void buscarCliente(ActionEvent event) {
        
        Clientes c = clienteDao.buscaCliente(cpf.getText());

        if(c != null){

            // limpa campos da pesquisa anterior
            nome.setText("");
            dataNasc.setText(""); 
            endereco.setText("");
            feminino.setSelected(false);
            masculino.setSelected(false);
            nrContas.setText("");
            saldo.setText("");

            // preenche campos com valores da nova pesquisa
            nome.setText(c.getNome().toString());

            SimpleDateFormat fd = new SimpleDateFormat("dd-MM-yyyy");
            dataNasc.setText(fd.format(c.getNascimento()).toString());    

            String teste = c.getSexo();
            if(c.getSexo() == "m"){
              masculino.setSelected(true);
            } else {
                feminino.setSelected(true);
            }

            endereco.setText(c.getEndereco().toString());

            // total de contas
            nrContas.setText("3");
            // total de saldo em todas contas
            saldo.setText("25.600");

        } else {
            nome.setText("");
            dataNasc.setText(""); 
            endereco.setText("");
            feminino.setSelected(false);
            masculino.setSelected(false);
            nrContas.setText("");
            saldo.setText("");
            msgErro.setVisible(true);
        }
    }
    
    @FXML
    public void deletarCliente(ActionEvent event) {
        
        try {
            Clientes c = clienteDao.buscaCliente(cpf.getText());
            //Contas teste = contaDao.buscaPorCliente(cpf.getText());
            if(c != null){
                ContaDAO contaDao = new ContaDAO();
                ContaClienteDAO contaClienteDao = new ContaClienteDAO();
                
                List<Conta> contasDoCliente = contaDao.buscaPorCliente(c.getCpf());
                contaClienteDao.deletaPorCliente(c.getCpf());
                contaDao.deletaBulk(contasDoCliente);

            }

        } catch (PersistenceException e) {

        }
    }
    
    
}
