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


public class TransferenciaController implements Initializable {
    ContaDAO contaDao = new ContaDAO();

    @FXML
    private Label erroTransferencia;

    @FXML
    private TextField contaDestino;

    @FXML
    private Button cancelar;

    @FXML
    private TextField contaOrigem;

    @FXML
    private TextField valor;

    @FXML
    private Text sucesso;

    @FXML
    private Button salvar;
    
    @FXML
    public void salvar(ActionEvent event){
        sucesso.setText("");
        erroTransferencia.setText("");
        salvar.setDisable(true);
        cancelar.setDisable(true);
        
        if ("".equals(contaOrigem.getText()) || "".equals(valor.getText())
            || "".equals(contaOrigem.getText())) {
            
            erroTransferencia.setText("Campos obrigatórios não preenchidos.");
            erroTransferencia.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        try {
            Float.valueOf(valor.getText());
        }
        catch (NumberFormatException ex){
            erroTransferencia.setText("Valor de transferência não é decimal válido.");
            erroTransferencia.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        BigDecimal transferenciaDecimal = new BigDecimal(Float.toString(Float.parseFloat(valor.getText())));
        if (transferenciaDecimal.compareTo(new BigDecimal("0.0")) <= 0) {
            erroTransferencia.setText("A transferência deve ser maior que 0.");
            erroTransferencia.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        Conta cOrigem = contaDao.buscaId(Long.valueOf(contaOrigem.getText()));
        Conta cDestino = contaDao.buscaId(Long.valueOf(contaDestino.getText()));
        
        if (cOrigem == null) {
            erroTransferencia.setText("Conta origem inexistente.");
            erroTransferencia.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        if (cDestino == null) {
            erroTransferencia.setText("Conta destino inexistente.");
            erroTransferencia.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        if (transferenciaDecimal.compareTo(cOrigem.getSaldo()) > 0) {
            erroTransferencia.setText("Conta não possui este valor disponível para transferência.");
            erroTransferencia.setVisible(true);
            salvar.setDisable(false);
            cancelar.setDisable(false);
            return;
        }
        
        BigDecimal saldoFinalOrigem = cOrigem.getSaldo().subtract(transferenciaDecimal);
        BigDecimal saldoFinalDestino = cDestino.getSaldo().add(transferenciaDecimal);
        
        cOrigem.setSaldo(saldoFinalOrigem);
        cDestino.setSaldo(saldoFinalDestino);
        
        contaDao.atualiza(cOrigem);
        contaDao.atualiza(cDestino);
        
        limpaCampos();
        
        salvar.setDisable(false);
        cancelar.setDisable(false);
        
        sucesso.setText("Transferência realizada com sucesso.");
        sucesso.setVisible(true);
    }
    
    @FXML
    public void fechar(ActionEvent event){       
        MenuInicialController.fecharUmaTela(event);
    }
    
    private void limpaCampos() {
        contaOrigem.setText(null);
        contaDestino.setText(null);
        valor.setText(null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //
    }    
    

}
