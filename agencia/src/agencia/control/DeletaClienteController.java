package agencia.control;

import agencia.Clientes;
import agencia.Conta;
import agencia.dao.ClienteDAO;
import agencia.dao.ContaClienteDAO;
import agencia.dao.ContaDAO;
import java.math.BigDecimal;
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

/**
 *
 * @author lucas
 */
public class DeletaClienteController implements Initializable {
    ClienteDAO clienteDao = new ClienteDAO();
    ContaDAO contaDao = new ContaDAO();
    ContaClienteDAO contaClienteDao = new ContaClienteDAO();
    
    private List<Conta> contasCliente = null;
    private Clientes cliente = null;
    
    @FXML
    private Button deletar;
    @FXML
    private TextField dataNasc;
    @FXML
    private TextField endereco;
    @FXML
    private TextField cpf;
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
    private Text sucesso;
    
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
        sucesso.setVisible(false);
        msgErro.setVisible(false);
        msgErro.setText("");
        
        this.cliente = clienteDao.buscaCliente(cpf.getText());
        this.contasCliente = contaDao.buscaPorCliente(this.cliente);

        if(this.cliente == null){
            deletar.setDisable(true);
            msgErro.setVisible(true);
            msgErro.setText("CPF informado não cadastrado.");
            return;
        }
        
        // limpa campos da pesquisa anterior
        limpaCampos();

        // preenche campos com valores da nova pesquisa
        nome.setText(this.cliente.getNome());
        SimpleDateFormat fd = new SimpleDateFormat("dd-MM-yyyy");
        dataNasc.setText(fd.format(this.cliente.getNascimento()));    
        endereco.setText(this.cliente.getEndereco());
        
        if("m".equals(this.cliente.getSexo())){
          masculino.setSelected(true);
        } else {
            feminino.setSelected(true);
        }

        // total de contas
        nrContas.setText(Integer.toString(contasCliente.size()));
        // total de saldo em todas contas
        BigDecimal saldoTotal = BigDecimal.ZERO;
        
        for (Conta conta : this.contasCliente) {
            saldoTotal = saldoTotal.add(conta.getSaldo());
        }
        saldo.setText(saldoTotal.toString());
        
        deletar.setDisable(false);
    }
    
    private void limpaCampos() {
        nome.setText("");
        dataNasc.setText(""); 
        endereco.setText("");
        feminino.setSelected(false);
        masculino.setSelected(false);
        nrContas.setText("");
        saldo.setText("");
    }
    
    @FXML
    public void setFeminino(ActionEvent event) {
        masculino.setSelected(false);
    }

    @FXML
    public void setMasculino(ActionEvent event) {
        feminino.setSelected(false);
    }
    
    @FXML
    public void deletarCliente(ActionEvent event) {
        
        contaClienteDao.deletaPorCliente(this.cliente);
        contaDao.deletaBulk(contasCliente);
        clienteDao.deleta(this.cliente.getCpf());
        
        limpaCampos();
        
        deletar.setDisable(true);
        sucesso.setVisible(true);
        sucesso.setText("Cliente excluído com sucesso.");
    }
    
    
}
