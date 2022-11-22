package agencia.control;

import agencia.Conta;
import agencia.dao.ContaDAO;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class SaqueController implements Initializable {
    ContaDAO contaDao = new ContaDAO();

    @FXML
    private Button cancelar;

    @FXML
    private TextField conta;

    @FXML
    private TextField valor;

    @FXML
    private Label erroSaque;

    @FXML
    private Button salvar;

    @FXML
    private Text sucesso;
    
    @FXML
    public void salvar(ActionEvent event){
        sucesso.setText("");
        erroSaque.setText("");
        salvar.setDisable(true);
        cancelar.setDisable(true);
        
        if ("".equals(conta.getText()) || "".equals(valor.getText())) {
            erroSaque.setText("Campos obrigatórios não preenchidos.");
            erroSaque.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        try {
            Float.valueOf(valor.getText());
        }
        catch (NumberFormatException ex){
            erroSaque.setText("O saque não é um decimal válido.");
            erroSaque.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        BigDecimal saqueDecimal = new BigDecimal(Float.toString(Float.parseFloat(valor.getText())));
        if (saqueDecimal.compareTo(new BigDecimal("0.0")) <= 0) {
            erroSaque.setText("O saque deve ser maior que 0.");
            erroSaque.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        Conta c = contaDao.buscaId(Long.valueOf(conta.getText()));
        
        if (c == null) {
            erroSaque.setText("Conta inexistente.");
            erroSaque.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        if (saqueDecimal.compareTo(c.getSaldo()) > 0) {
            erroSaque.setText("Conta não possui este valor disponível para saque.");
            erroSaque.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        BigDecimal saldoFinal = c.getSaldo().subtract(saqueDecimal);
        
        c.setSaldo(saldoFinal);
        
        contaDao.atualiza(c);
        
        limpaCampos();
        
        salvar.setDisable(false);
        cancelar.setDisable(false);
        
        sucesso.setText("Saque realizado com sucesso.");
        sucesso.setVisible(true);
    }
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    private void limpaCampos() {
        conta.setText(null);
        valor.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //
    }    
    

}
