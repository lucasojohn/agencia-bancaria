package agencia.control;

import agencia.Conta;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class DepositoController implements Initializable {
    ContaDAO contaDao = new ContaDAO();

    @FXML
    private Button cancelar;
    @FXML
    private Text validacao;
    @FXML
    private Text sucesso;
    @FXML
    private TextField valor;
    @FXML
    private ChoiceBox conta;
    @FXML
    private Button salvar;

    @FXML
    public void salvar(ActionEvent event){
        sucesso.setText("");
        validacao.setText("");
        salvar.setDisable(true);
        cancelar.setDisable(true);
        
        try {
            Float.valueOf(valor.getText());
        }
        catch (NumberFormatException ex){
            validacao.setText("O saldo não é um decimal válido.");
            validacao.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        BigDecimal depositoDecimal = new BigDecimal(Float.toString(Float.parseFloat(valor.getText())));
        if (depositoDecimal.compareTo(new BigDecimal("0.0")) <= 0) {
            validacao.setText("O depósito deve ser maior que 0.");
            validacao.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        Conta c = contaDao.buscaId((Long) conta.getValue());
        
        BigDecimal saldoFinal = depositoDecimal.add(c.getSaldo());
        
        c.setSaldo(saldoFinal);
        
        contaDao.atualiza(c);
        
        limpaCampos();
        
        salvar.setDisable(false);
        cancelar.setDisable(false);
        
        sucesso.setText("Depósito realizado com sucesso.");
        sucesso.setVisible(true);
    }
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    private void limpaCampos() {
        conta.setValue(null);
        valor.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Conta> contas = contaDao.lista();
        
        for (Conta c : contas) {
            conta.getItems().add(c.getNumero());
        }
    }    
    

}
